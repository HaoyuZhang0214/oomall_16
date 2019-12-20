package xmu.oomall.cart.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xmu.oomall.cart.domain.CartItem;
import xmu.oomall.cart.domain.CartItemPo;
import xmu.oomall.cart.service.CartService;
import xmu.oomall.cart.util.ResponseUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**

 * 用户购物车服务

 */

@RestController
@RequestMapping("/cartService")
@Validated
public class CartController {

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private CartService cartService;

    /**

     * 用户购物车信息

     *

     * @param page
     * @param limit

     * @return 用户购物车信息

     */

    @GetMapping("/cartItems")
    public Object cartIndex(HttpServletRequest request,
                            @RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer limit) {

        String id = request.getParameter("id");
        Integer userId = null;
        if (id != null && !"".equals(id)) {
            userId = Integer.valueOf(id);
        } else {
            logger.debug("未登录");
            return ResponseUtil.unlogin();
        }
        List<CartItemPo> cartItemList= cartService.listAllCartItems(userId);
        int page_count=cartItemList.size()/limit;
        int remain=cartItemList.size()%limit;
        if(remain>0)
            page_count++;
        if(page>page_count) {
            logger.debug("参数不合法");
            return ResponseUtil.illegalParameter();
        }
        if(remain==0) {
            cartItemList=cartItemList.subList((page-1)*limit,page*limit);
        }
        else{
            if (page==page_count){
                cartItemList=cartItemList.subList((page-1)*limit,cartItemList.size());
            }else{
                cartItemList=cartItemList.subList((page-1)*limit,page*limit);
            }
        }
        if(cartItemList==null) {
            logger.debug("购物车明细不存在");
            return ResponseUtil.getFail();
        }
        else
            return ResponseUtil.ok(cartItemList);

    }


    /**

     * 通过用户id查询cart

     * 内部接口：提供给订单模块

     * @param userId

     * @return 用户购物车项信息

     */

    @GetMapping("/cartItem/{userId}")

    public Object getUserCart(@PathVariable Integer userId) {

        if(userId<0) {
            logger.debug("参数不合法");
            return ResponseUtil.illegalParameter();
        }
        List<CartItemPo> cartItemList= cartService.listAllCartItems(userId);
        if(cartItemList==null) {
            logger.debug("购物车明细不存在");
            return ResponseUtil.getFail();
        }
        else
            return ResponseUtil.ok(cartItemList);

    }








    /**

     * 用户单个购物车项信息

     *

     * @param id
     * @param page
     * @param limit

     * @return 用户单个购物车项信息

     */

//    @GetMapping("/cartItems/{id}")
//    public Object cartItemInfo(@PathVariable Integer id,
//                                 @RequestParam(defaultValue = "1") Integer page,
//                                 @RequestParam(defaultValue = "10") Integer limit) {
//
//        if(id<0)
//            return ResponseUtil.badArgumentValue();
//        CartItemPo cartItemPo = cartService.cartItemInfo(id);
//        return ResponseUtil.ok(cartItemPo);
//
//    }

    /**

     * 加入商品到购物车

     * <p>

     * 如果已经存在购物车货品，则增加数量；

     * 否则添加新的购物车货品项。

     *

     * @param cartItem   购物车商品信息， { userId: xxx, productId: xxx, number: xxx }

     * @return 加入购物车的车项信息

     */
    @PostMapping("/cartItems")
    public Object addItem(@RequestBody CartItemPo cartItem) {

        CartItemPo cartItem1 = cartService.addItem(cartItem);
        if(cartItem1==null) {
            logger.debug("购物车明细新增失败");
            return ResponseUtil.addFail();
        }
        else
            return ResponseUtil.ok(cartItem1);
    }


    /**

     * 快速购买

     * <p>

     *

     * @param cartItem   购物车商品信息， { userId: xxx, productId: xxx, number: xxx }

     * @return 快速购买的车项信息

     */
    @PostMapping("/fastAddCartItems")
    public Object fastAddCartItems(@RequestBody CartItemPo cartItem) {

        return ResponseUtil.ok(cartService.fastAddItem(cartItem));

    }


    /**

     * 修改某个购物车项信息

     *

     * @param id

     * @param cartItem  {number:xxx}

     * @return 修改结果

     */

    @PutMapping("/cartItems/{id}")

    public Object updateItem(@PathVariable Integer id,@RequestBody CartItemPo cartItem) {

        if(id<0) {
            logger.debug("参数不合法");
            return ResponseUtil.illegalParameter();
        }
        else {
            CartItemPo cartItem1 = cartService.updateItem(id,cartItem);
            if(cartItem1!=null)
                return ResponseUtil.ok(cartItem1);
            else {
                logger.debug("购物车明细修改失败");
                return ResponseUtil.updateFail();
            }
        }

    }



    /**

     * 购物车商品删除

     *

     * @param id

     * @return 购物车信息


     */

    @DeleteMapping("/cartItems/{id}")

    public Object deleteItem(@PathVariable Integer id) {

        if(id<0) {
            logger.debug("参数不合法");
            return ResponseUtil.illegalParameter();
        }
        if( cartService.deleteItem(id)>0) {
            return ResponseUtil.ok();
        }
        else {
            logger.debug("购物车明细删除失败");
            return ResponseUtil.deleteFail();
        }

    }



}
