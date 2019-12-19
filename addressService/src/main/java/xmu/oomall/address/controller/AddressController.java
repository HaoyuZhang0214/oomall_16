package xmu.oomall.address.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xmu.oomall.address.domain.Address;
import xmu.oomall.address.domain.AddressPo;
import xmu.oomall.address.service.AddressService;
import xmu.oomall.address.util.ResponseUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**

 * 用户地址服务

 */
@RestController
@RequestMapping("/addressService")
@Validated
public class AddressController {

    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    private AddressService addressService;

    /**

     * 管理员查询用户地址信息

     * @param userId
     * @param name
     * @param page
     * @param limit

     * @return 用户地址信息

     */

    @GetMapping("/admin/addresses")
    public Object userAddress(@RequestParam Integer userId,
                            @RequestParam String name,
                            @RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer limit
                            ) {

        if(userId<0)
            return ResponseUtil.badArgumentValue();
        List<Address> subList=null;
        List<Address> addressList= addressService.getAllUserAddress(userId,name);
        if(addressList==null)
            return ResponseUtil.ok(addressList);
        int page_count=addressList.size()/limit;
        int remain=addressList.size()%limit;
        if(remain>0)
            page_count++;
        if(page>page_count)
            return ResponseUtil.fail(402,"page值超限");
        if(remain==0) {
            subList=addressList.subList((page-1)*limit,page*limit);
        }
        else{
            if (page==page_count){
                subList=addressList.subList((page-1)*limit,addressList.size());
            }else{
                subList=addressList.subList((page-1)*limit,page*limit);
            }
        }
        return ResponseUtil.ok(subList);

    }


    /**

     * 用户查询所有地址信息

     *
     * @param page
     * @param limit

     * @return 用户地址信息

     */

    @GetMapping("/addresses")

    public Object listAllAddress(HttpServletRequest request,
                                 @RequestParam(defaultValue = "1") Integer page,
                                 @RequestParam(defaultValue = "10") Integer limit) {

        Integer userId = 1;

        List<Address> subList=null;
        List<Address> addressList= addressService.getAllAddressById(userId);
        if(addressList==null)
            return ResponseUtil.ok(addressList);
        int page_count=addressList.size()/limit;
        int remain=addressList.size()%limit;
        if(remain>0)
            page_count++;
        if(page>page_count)
            return ResponseUtil.fail(402,"page值超限");
        if(remain==0) {
            subList=addressList.subList((page-1)*limit,page*limit);
        }
        else{
            if (page==page_count){
                subList=addressList.subList((page-1)*limit,addressList.size());
            }else{
                subList=addressList.subList((page-1)*limit,page*limit);
            }
        }
        return ResponseUtil.ok(subList);


    }


    /**

     * 用户查询地址信息

     *
     * @param id

     * @return 用户地址信息

     */

    @GetMapping("/addresses/{id}")

    public Object getAddress(@PathVariable Integer id) {

        if(id<0)
            return ResponseUtil.badArgumentValue();
        Address address = addressService.getAddressById(id);
        return ResponseUtil.ok(address);

    }




    /**

     * 用户添加地址

     *
     * @paramBody addressPo

     * @return 新增的AddressPo

     */

    @PostMapping("/addresses")

    public Object addNewAddress(@RequestBody AddressPo addressPo) {

        AddressPo addressPo1 = addressService.addAddress(addressPo);
        if(addressPo1==null)
            return ResponseUtil.fail(721,"地址操作失败");
        else
            return ResponseUtil.ok(addressPo1);


    }



    /**

     * 用户修改地址

     *
     * @paramBody addressPo

     * @return 用户地址信息

     */

    @PostMapping("/addresses/{id}")

    public Object editAddress(@PathVariable Integer id, @RequestBody AddressPo addressPo) {

        AddressPo addressPo1 = addressService.editAddress(id,addressPo);
        if(addressPo1==null)
            return ResponseUtil.fail(721,"地址操作失败");
        else
            return ResponseUtil.ok(addressPo1);


    }



    /**

     * 用户删除地址

     *
     * @paramBody addressPo

     * @return 删除结果
     *
     */

    @DeleteMapping("/addresses/{id}")

    public Object deleteAddress(@PathVariable Integer id) {
        if(addressService.deleteAddress(id)>0) {
            return ResponseUtil.ok();
        }
        else {
            return ResponseUtil.fail(721,"地址操作失败");
        }
    }





}
