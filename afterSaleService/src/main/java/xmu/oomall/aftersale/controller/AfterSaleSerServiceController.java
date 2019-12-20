package xmu.oomall.aftersale.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import xmu.oomall.aftersale.domain.AfterSaleService;

import xmu.oomall.aftersale.util.ResponseUtil;



import javax.servlet.http.HttpServletRequest;

import java.util.List;





/**

 * 售后API

 * @Author

 * @create 2019/12/5 17:38

 */



@RestController

@RequestMapping("/afterSaleService")

public class AfterSaleSerServiceController {



    @Autowired

    private xmu.oomall.aftersale.service.AfterSaleService afterSaleService;



    /**

     * 管理员查询某一个用户所有售后服务列表

     *

     * @return

     */

    @GetMapping("/admin/afterSaleServices")

    public Object adminFindAfterSalesServiceList(@RequestParam Integer userId,
                                                 @RequestParam(defaultValue = "1") Integer limit,
                                                 @RequestParam(defaultValue = "10") Integer page) {

        //用户服务网关已经拦截了非法请求，因此这里不需要再次验证

        List<AfterSaleService> afterSaleServices = afterSaleService.findAfterSales(userId);

        if(afterSaleServices !=null){

            return ResponseUtil.okList(afterSaleServices);

        }else {

            return ResponseUtil.ok(null);

        }

    }



    /**

     * 管理员通过id查询某一个售后服务具体信息

     * @param id

     * @return

     */

    @GetMapping("/admin/afterSaleServices/{id}")

    public Object adminFindAfterSaleServiceById(@PathVariable Integer id) {

        //用户服务网关已经拦截了非法请求，因此这里不需要再次验证是否为管理员

        //id不为空

        if(id!=null){

            //查询结果

            AfterSaleService afterSaleService = this.afterSaleService.findAfterSaleById(id);

            //查询结果不为空，返回成功状态码及数据

            if(afterSaleService !=null) {

                return ResponseUtil.ok(afterSaleService);

            }else{   //查询结果为空，返回参数不对


                return ResponseUtil.ok(null);

            }

        }else{              //id为空，返回参数不对？

            return ResponseUtil.fail(691, "获取售后服务失败");

        }



    }



    /**

     * 管理员根据id修改售后服务的信息（有requestBody版本，application/json）

     * @param id

     * @return

     */

    @PutMapping("/admin/afterSaleServices/{id}")

    public Object adminUpdateAfterSaleService(@PathVariable Integer id,

                                              @RequestBody AfterSaleService afterSaleService) {

        //id和afterSale都不为空，才能继续进行操作

        if(id!=null && afterSaleService !=null) {

            afterSaleService.setId(id);

            //afterSale1 是修改后的售后记录

            AfterSaleService afterSaleService1 = this.afterSaleService.adminUpdateAfterSale(afterSaleService);

            if (afterSaleService1 != null) {

                return ResponseUtil.ok(afterSaleService1);

            } else {

                //修改后的售后记录为空，返回更新数据失败

                return ResponseUtil.fail(693, "修改售后服务失败");

            }

        }else{              //id或afterSale为空，返回更新数据失败

            return ResponseUtil.fail(693, "修改售后服务失败");

        }

    }



    /**

     * 用户查询自己的售后服务列表（不返回已删除的售后记录），需要userId

     * @return

     */

    @GetMapping("/afterSaleServices")

    public Object userFindAfterSaleServiceList(HttpServletRequest request,

                                               @RequestParam(defaultValue = "10") Integer limit,

                                               @RequestParam(defaultValue = "1") Integer page
    ) {

        //集成用户服务，获取userId

        //Integer userId=getUserId(request);
        Integer userId = 1;

        if(userId!=null) {

            List<AfterSaleService> afterSaleServices = afterSaleService.findAfterSalesByUserId(userId);

            if (afterSaleServices != null) {

                return ResponseUtil.okList(afterSaleServices);

            } else {
                return ResponseUtil.ok(null);

            }

        }else{

            return ResponseUtil.fail(691,"获取售后服务失败");  //userId为空，返回参数不对

        }

    }



    /**

     * 用户查询某一个售后服务的具体信息，当这个售后被删除后，返回空列表

     * @param id

     * @return

     */

    @GetMapping("/afterSaleServices/{id}")

    public Object userFindAfterSaleService(@PathVariable Integer id

    ) {
        Integer userId = 1;

        //userId由用户服务提供的方法获得  httpServletRequest参数

        AfterSaleService afterSaleService = this.afterSaleService.findAfterSaleById(id);
        System.out.println(afterSaleService.toString());

        if(userId.equals(afterSaleService.getUserId())){       //访问的是自己的售后服务

            if(!afterSaleService.getBeDeleted()){        //没有被删除

                return ResponseUtil.ok(afterSaleService);

            }else{                                   //删除了

                return ResponseUtil.ok(null);

            }

        }else {

            return ResponseUtil.fail(694,"删除售后服务失败 ");       //无操作权限

        }

    }



    /**

     * 用户申请售后服务，需要提供

     * goodsType   只能是1，2，3，4

     * applyReason

     * type     只能是0，1

     * number

     * orderItemId

     * userId    这里应该不是写进afterSale中的，由用户服务提供的方法获得

     * @param afterSaleService

     * @return

     */

    @PostMapping("/afterSaleServices")

    public Object userApplyAfterSaleService(@RequestBody AfterSaleService afterSaleService) {

        //userId由用户服务提供的方法获得

        Integer userId = 1;

        if(userId!=null && afterSaleService !=null) {

            Integer type = afterSaleService.getType();

            if(type<0 || type>1){

                return ResponseUtil.badArgumentValue();       //参数值不对

            }

            Integer goodsType = afterSaleService.getGoodsType();

            if(goodsType<1 || goodsType >4){

                return ResponseUtil.fail(692,"申请售后服务失败");       //参数值不对

            }

            //设置userId

            afterSaleService.setUserId(userId);

            AfterSaleService afterSaleService1 = this.afterSaleService.insertAfterSale(afterSaleService);

            if(afterSaleService1 !=null){

                return ResponseUtil.ok(afterSaleService1);    //返回申请后的售后记录

            }else{

                return ResponseUtil.fail(692, "申请售后服务失败");     //订单超过七天，业务不支持

            }

        }else{

            return ResponseUtil.fail(692, "申请售后服务失败");     //参数不对

        }

    }



    /**

     * 用户修改售后服务的信息，在管理员审核之前，可以修改：

     * goodsType     商品类别

     * applyReason    申请理由

     * type          售后类型

     * number        申请数量

     * beApplied     是否取消

     * 在管理员审核之后，用户只可以修改是否取消售后，即：

     * beApplied=false

     * @param id

     * @param afterSaleService

     * @return

     */

    @PutMapping("/afterSaleServices/{id}")

    public Object userUpdateAfterSaleService(@PathVariable Integer id,

                                             @RequestBody AfterSaleService afterSaleService) {

        //应该也要验证用户身份，通过token获取userId

        if(id!=null && afterSaleService !=null) {

            afterSaleService.setId(id);

            //在此处将userId写进对象afterSale中

            AfterSaleService afterSaleService1 = this.afterSaleService.updateAfterSale(afterSaleService);

            //afterSale1 是修改后的售后记录

            if(afterSaleService1 !=null) {

                //比较afterSale 的userId 和afterSale1 的userId ，相同返回修改后的售后记录

                if(afterSaleService.getUserId().equals(afterSaleService1.getUserId())) {

                    return ResponseUtil.ok(afterSaleService1);

                }else{

                    //不同返回无操作权限

                    return ResponseUtil.fail(693, "修改售后服失败");

                }

            }else{

                return ResponseUtil.fail(693, "修改售后服失败"); //更新数据失败

            }

        }else{

            return ResponseUtil.fail(693, "修改售后服失败") ;     //更新数据失败

        }

    }



    /**

     * 用户删除某一个售后服务

     * 只有管理员审核之后才能删除？

     * @param id

     * @return

     */

    @DeleteMapping("/afterSaleServices/{id}")

    public Object userDeleteAfterSaleService(@PathVariable Integer id) {

        //是不是也要解析userId?
        if(id<0) {
            return ResponseUtil.badArgumentValue();
        }
        if(afterSaleService.deleteAfterSale(id)>0) {
            return ResponseUtil.ok(null);

        }else {

            return ResponseUtil.fail(694, "删除售后服务失败");

        }

    }

}