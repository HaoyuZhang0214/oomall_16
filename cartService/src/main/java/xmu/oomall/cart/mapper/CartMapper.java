package xmu.oomall.cart.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import xmu.oomall.cart.domain.CartItemPo;

import java.util.List;


/**
 * @author zhy
 */
@Mapper
@Repository
public interface CartMapper {


    /**
     * 购物车
     * @param userId
     * @return
     */
    public List<CartItemPo> listAllCartItems(@RequestParam Integer userId);

    /**
     * 添加购物车项
     * @param cartItem
     * @return
     */
    public Integer addItem(@RequestBody CartItemPo cartItem);


    /**
     * 快速购买
     * @param cartItem
     */
    public void fastAddItem(@RequestBody CartItemPo cartItem);


    /**
     * 修改购物车项
     * @param cartItem
     * @return
     */
    public Integer updateItem(@RequestBody CartItemPo cartItem);


    /**
     * 删除购物车项
     * @param id
     * @return
     */
    public Integer deleteItem(@RequestParam Integer id);

    /**
     * 获得购物车项
     * @param id
     * @return
     */
    public CartItemPo cartItemInfoById(@RequestParam Integer id);


    /**
     * 获得购物车箱
   * @param userId
     * @param productId
     * @return
     */
    public CartItemPo searchItem(@RequestParam Integer userId, @RequestParam Integer productId);

}
