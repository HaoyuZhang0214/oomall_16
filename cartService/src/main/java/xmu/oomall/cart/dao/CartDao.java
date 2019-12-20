package xmu.oomall.cart.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import xmu.oomall.cart.domain.CartItem;
import xmu.oomall.cart.domain.CartItemPo;
import xmu.oomall.cart.mapper.CartMapper;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public class CartDao {



    @Autowired
    private CartMapper cartMapper;

    public List<CartItemPo> listAllCartItems(@RequestParam Integer userId) {
        List<CartItemPo> cartItemPoList = cartMapper.listAllCartItems(userId);
        return cartItemPoList;
    }

    public CartItemPo addItem(@RequestBody CartItemPo cartItem) {
        CartItemPo cartItem1 = cartMapper.searchItem(cartItem.getUserId(),cartItem.getProductId());
        if(cartItem1!=null) {
            Integer num = cartItem1.getNumber();
            cartItem1.setNumber(num+cartItem.getNumber());
            cartItem1.setGmtModified(LocalDateTime.now());
            if(cartMapper.updateItem(cartItem1)>0)
                return cartItem1;
            else
                return null;
        }
        cartItem.setBeCheck(false);
        cartItem.setGmtCreate(LocalDateTime.now());
        cartItem.setGmtModified(LocalDateTime.now());
        if(cartMapper.addItem(cartItem)>0)
            return cartItem;
        else
            return null;
    }

//    public Boolean ifExist(@RequestBody CartItem cartItem) {
//        CartItem cartItem1 = new CartItem(cartMapper.searchItem(cartItem.getUserId(),cartItem.getProductId());
//        if(cartItem.getProductId() == cartItem1.getProductId() && cartItem.getUserId() == cartItem1.getUserId()) {
//            return true;
//        }
//        else {
//            return false;
//        }
//    }


//    public Boolean ifExist(@RequestParam Integer id, @RequestParam Integer userId) {
//        if(cartMapper.cartItemInfoById(id,userId)==null) {
//            return false;
//        }
//        else {
//            return true;
//        }
//    }

//    public Boolean ifExistByUserId(@RequestParam Integer userId) {
//        if(cartMapper.cartItemInfoByUserId(userId)==null) {
//            return false;
//        }
//        else {
//            return true;
//        }
//    }

    public CartItemPo fastAddItem(@RequestBody CartItemPo cartItem) {
        cartMapper.fastAddItem(cartItem);
        return cartItem;
    }

    public CartItemPo updateItem(@RequestBody CartItemPo cartItem) {
        CartItemPo cartItem1 = cartMapper.cartItemInfoById(cartItem.getId());
        if(cartItem1==null)
            return null;
        Integer num = cartItem1.getNumber();
        cartItem1.setNumber(cartItem.getNumber());
        cartItem1.setGmtModified(LocalDateTime.now());
        if(cartMapper.updateItem(cartItem1)>0)
            return cartItem1;
        else
            return null;
    }

    public Integer deleteItem(@RequestParam Integer id) {
        return cartMapper.deleteItem(id);
    }

    public CartItemPo cartItemInfo(@RequestParam Integer id) {
        return cartMapper.cartItemInfoById(id);
    }

}
