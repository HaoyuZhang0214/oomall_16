package xmu.oomall.cart.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import xmu.oomall.cart.domain.CartItem;
import xmu.oomall.cart.domain.CartItemPo;

import java.util.List;

@Service
public interface CartService {


    public List<CartItemPo> listAllCartItems(@RequestParam Integer userId);

    public CartItemPo addItem(@RequestBody CartItemPo cartItem);

    public CartItemPo fastAddItem(@RequestBody CartItemPo cartItem);

    public CartItemPo updateItem(@RequestParam Integer id, @RequestBody CartItemPo cartItem);

    public Integer deleteItem(@RequestParam Integer id);

    public CartItemPo cartItemInfo(@RequestParam Integer id);

//    public Boolean ifExist(@RequestParam Integer id, @RequestParam Integer userId);

//    public Boolean ifExistByUserId(@RequestParam Integer id);

}
