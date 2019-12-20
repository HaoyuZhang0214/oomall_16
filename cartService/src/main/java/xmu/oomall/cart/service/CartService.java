package xmu.oomall.cart.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import xmu.oomall.cart.domain.CartItem;
import xmu.oomall.cart.domain.CartItemPo;

import java.util.List;


/**
 * @author zhy
 */
@Service
public interface CartService {


    /**
     *
     * @param userId
     * @return
     */
    public List<CartItemPo> listAllCartItems(@RequestParam Integer userId);

    /**
     *
     * @param cartItem
     * @return
     */
    public CartItemPo addItem(@RequestBody CartItemPo cartItem);

    /**
     *
     * @param cartItem
     * @return
     */
    public CartItemPo fastAddItem(@RequestBody CartItemPo cartItem);

    /**
     *
     * @param id
     * @param cartItem
     * @return
     */
    public CartItemPo updateItem(@RequestParam Integer id, @RequestBody CartItemPo cartItem);

    /**
     *
     * @param id
     * @return
     */
    public Integer deleteItem(@RequestParam Integer id);

    /**
     *
     * @param id
     * @return
     */
    public CartItemPo cartItemInfo(@RequestParam Integer id);


}
