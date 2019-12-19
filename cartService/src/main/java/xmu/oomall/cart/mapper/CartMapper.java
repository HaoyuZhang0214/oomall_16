package xmu.oomall.cart.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import xmu.oomall.cart.domain.CartItemPo;

import java.util.List;

@Mapper
@Repository
public interface CartMapper {


    public List<CartItemPo> listAllCartItems(@RequestParam Integer userId);

    public Integer addItem(@RequestBody CartItemPo cartItem);

    public void fastAddItem(@RequestBody CartItemPo cartItem);

    public Integer updateItem(@RequestBody CartItemPo cartItem);

    public Integer deleteItem(@RequestParam Integer id);

    public CartItemPo cartItemInfoById(@RequestParam Integer id);

//    public List<CartItem> cartItemInfoByUserId(@RequestParam Integer userId);

    public CartItemPo searchItem(@RequestParam Integer userId, @RequestParam Integer productId);

}
