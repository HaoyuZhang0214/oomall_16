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

        Integer userId = 1;

        List<CartItemPo> cartItemList= cartService.listAllCartItems(userId);
        int page_count=cartItemList.size()/limit;
        int remain=cartItemList.size()%limit;
        if(remain>0)
            page_count++;
        if(page>page_count)
            return ResponseUtil.fail(402,"page值超限");
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
        if(cartItemList==null)
            return ResponseUtil.fail(730,"购物车记录不存在");
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

        List<CartItemPo> cartItemList= cartService.listAllCartItems(userId);
        if(cartItemList==null)
            return ResponseUtil.fail(730,"购物车记录不存在");
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
        if(cartItem1==null)
            return ResponseUtil.fail(731,"购物车操作失败");
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

        return cartService.fastAddItem(cartItem);

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

        if(id<0)
            return ResponseUtil.badArgumentValue();
        else {
            CartItemPo cartItem1 = cartService.updateItem(id,cartItem);
            if(cartItem1!=null)
                return ResponseUtil.ok(cartItem1);
            else
                return ResponseUtil.fail(731,"购物车操作失败");
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

        if(id<0)
            return ResponseUtil.badArgumentValue();
        if( cartService.deleteItem(id)>0) {
            return ResponseUtil.ok();
        }
        else {
            return ResponseUtil.fail(731,"购物车操作失败");
        }

    }



}
