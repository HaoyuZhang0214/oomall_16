package xmu.oomall.cart.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import xmu.oomall.cart.dao.CartDao;
import xmu.oomall.cart.domain.CartItem;
import xmu.oomall.cart.domain.CartItemPo;
import xmu.oomall.cart.mapper.CartMapper;
import xmu.oomall.cart.service.CartService;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class CartServiceImpl implements CartService {



    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private CartDao cartDao;

    @Override
    public List<CartItemPo> listAllCartItems(@RequestParam Integer userId) {
        return cartDao.listAllCartItems(userId);
    }

    @Override
    public CartItemPo addItem(@RequestBody CartItemPo cartItem) {

//        if(cartDao.ifExist(cartItem)) {
//            return cartDao.addNumber(cartItem.getNumber());
//        }
//        else {
            return cartDao.addItem(cartItem);
//        }
    }

    @Override
    public CartItemPo fastAddItem(@RequestBody CartItemPo cartItem) {

        cartItem.setBeCheck(false);
        cartItem.setGmtCreate(LocalDateTime.now());
        cartItem.setGmtModified(LocalDateTime.now());
        return cartItem;
    }

    @Override
    public CartItemPo updateItem(@RequestParam Integer id, @RequestBody CartItemPo cartItem) {
        cartItem.setId(id);
        return cartDao.updateItem(cartItem);
    }

    @Override
    public Integer deleteItem(@RequestParam Integer id) {
            return cartDao.deleteItem(id);
    }


    @Override
    public CartItemPo cartItemInfo(@RequestParam Integer id) {
        return cartDao.cartItemInfo(id);
    }

//    @Override
//    public Boolean ifExist(@RequestParam Integer id, @RequestParam Integer userId) {
//        if(cartDao.ifExist(id,userId)==true)
//            return true;
//        else
//            return false;
//    }

//    @Override
//    public Boolean ifExistByUserId(@RequestParam Integer userId) {
//        if(cartDao.ifExistByUserId(userId)==true)
//            return true;
//        else
//            return false;
//    }
}
