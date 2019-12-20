package xmu.oomall.collection.controller;/*
package和import需要根据项目导入
 */


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xmu.oomall.collection.domain.CollectItem;
import xmu.oomall.collection.domain.CollectItemPo;
import xmu.oomall.collection.service.CollectService;
import xmu.oomall.collection.util.ResponseUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;


/**
 * 用户收藏服务
 * @author zhy
 */
@RestController
@RequestMapping("/collectionService")
public class CollectController {

    private static final Logger logger = LoggerFactory.getLogger(CollectController.class);

    @Autowired
    private CollectService collectService ;

    private CollectItem collectItemPo;

    /**
     * 用户收藏列表
     *
     * @param request
     * @param page   分页页数
     * @param limit   分页大小
     * @return 用户收藏列表
     */
    @GetMapping ("/collections")
    public Object list(HttpServletRequest request,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit
                                  ){

        String id = request.getParameter("id");
        Integer userId = null;
        if (id != null && !"".equals(id)) {
            userId = Integer.valueOf(id);
        } else {
            logger.debug("未登录");
            return ResponseUtil.unlogin();
        }
        return collectService.listCollect(userId,page,limit);
    }

    /**
     * 用户收藏添加或删除
     * <p>
     * 如果商品没有收藏，则添加收藏；如果商品已经收藏，则删除收藏状态。
     *
     *
     * @param body   请求内容，{ type: xxx, valueId: xxx }
     * @return 操作结果
     */

    @PostMapping ("/collections")
    public Object add( @RequestBody CollectItemPo body) {
         body.setGmtCreate(LocalDateTime.now());
         body.setGmtModified(LocalDateTime.now());
         CollectItemPo collectItemPo = collectService.addCollect(body);
         if(collectItemPo==null) {
             logger.debug("收藏新增失败");
             return ResponseUtil.addFail();
         }
         else {
             return ResponseUtil.ok(collectItemPo);
         }
    }

    @DeleteMapping("/collections/{id}")
    public Object update( @PathVariable Integer id) {
        if(collectService.deleteCollectById(id)>0) {
            return ResponseUtil.ok();
        }
        else {
            logger.debug("收藏删除失败");
            return ResponseUtil.deleteFail();
        }
    }
}