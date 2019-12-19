package xmu.oomall.collection.controller;/*
package和import需要根据项目导入
 */


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
 */
@RestController
@RequestMapping("/collectionService")
public class CollectController {
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

        Integer userId = 1;
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
   //@ApiOperation(value = "添加收藏 ")
    public Object add( @RequestBody CollectItemPo body) {
         body.setGmtCreate(LocalDateTime.now());
         body.setGmtModified(LocalDateTime.now());
         return ResponseUtil.ok(collectService.addCollect(body));
    }

    @DeleteMapping("/collections/{id}")
    public Object update( @PathVariable Integer id) {
        if(collectService.deleteCollectById(id)>0)
            return ResponseUtil.ok();
        else
            return ResponseUtil.fail();
    }
}