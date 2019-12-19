package xmu.oomall.footprint.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xmu.oomall.footprint.domain.FootprintItem;
import xmu.oomall.footprint.domain.FootprintItemPo;
import xmu.oomall.footprint.service.FootPrintService;
import xmu.oomall.footprint.util.Login;
import xmu.oomall.footprint.util.ResponseUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/footprint")
public class FootPrintController {

    @Autowired
    FootPrintService footPrintService;

    /**
     * list实现类
     * 测试url：localhost:8080/footprint/footprints?userId=1&goodsId=2&page=1&limit=2&sort=birth_time&order=ASC
     * @param userId
     * @param goodsId
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/admin/footprints")
    public Object list(@RequestParam Integer userId,
                       @RequestParam Integer goodsId,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit) {
        List<FootprintItem> footprintItemList =footPrintService.findFootPrintItemByUserIdAndGoodsId(userId, goodsId, page, limit);
        Object reObj;
        //footprintItemList.clear();
        if(footprintItemList.isEmpty()) {
            reObj =ResponseUtil.fail(502,"系统内部错误");
        } else {
            reObj=ResponseUtil.ok(footprintItemList);
        }

        return reObj;//footPrintService.findFootPrintItemByUserIdAndGoodsId(userId, goodsId, page, limit, newSort, order);
    }

    /**
     * delete实现类
     * 测试url：localhost:8080/footprint/{3}?userId=1 body: {"id":"1"}
     * @param userId 用户ID
     * @param id   请求内容， { id: xxx }
     * @return
     */
    @DeleteMapping("/footprints/{id}")
    public Object delete(@PathVariable("id") String id,Integer userId) {
        Object reObj;
        System.out.println(Integer.parseInt(id));
        boolean ok=footPrintService.deleteFootPrintItemByUserIdAndId(userId,Integer.parseInt(id));
        if(ok) {
            reObj=ResponseUtil.ok();
        } else {
            reObj=ResponseUtil.fail();
        }
        return reObj;
    }

    /**
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/footprints")
    public Object list(HttpServletRequest request,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit) {
        Integer userId= Login.getUserId(request);
        if(userId==null)
        {
            return ResponseUtil.unlogin();
        }
        Object reObj;
        List<FootprintItem> footprintItemList =footPrintService.findFootPrintItemByUserId(userId, page, limit);
        if(footprintItemList.isEmpty()) {
            reObj=ResponseUtil.fail(502,"系统内部错误");
        } else {
            reObj=ResponseUtil.ok(footprintItemList);
        }
        return reObj;//footPrintService.findFootPrintItemByUserId(userId, page, limit);
    }


    /**
     * add实现类
     * 测试url：localhost:8080/footprint/footprints/1 body：{"id":"1","goodsId":"1"}
     * @param footprintItemPo
     * @return
     */
    @PostMapping("/footprints")
    public Object add(HttpServletRequest request, @RequestBody FootprintItemPo footprintItemPo) {
        Integer userId=Login.getUserId(request);
        footprintItemPo.setId(userId);
        Object reObj;
        FootprintItemPo newFootprintItemPo=footPrintService.addFootPrintItem(footprintItemPo);
        if (newFootprintItemPo.getId()==0) {
            reObj=ResponseUtil.fail(502,"数据库插入失败");
        } else {
            reObj=ResponseUtil.ok(newFootprintItemPo);
        }
        return reObj;
    }
}
