package xmu.oomall.address.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xmu.oomall.address.domain.Address;
import xmu.oomall.address.domain.AddressPo;
import xmu.oomall.address.service.AddressService;
import xmu.oomall.address.util.ResponseUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**

 * 用户地址服务

 */
@RestController
@RequestMapping("/addressService")
//@Validated
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

        if(userId<0||page<0||limit<0) {
            logger.debug("参数不合法");
            return ResponseUtil.illegalParameter();
        }
        List<Address> subList= new ArrayList<Address>();
        List<Address> addressList= addressService.getAllUserAddress(userId,name);
        if(addressList.size()==0) {
            logger.debug("该地址是无效地址");
            return ResponseUtil.invalid();
        }
        int page_count=addressList.size()/limit;
        int remain=addressList.size()%limit;
        if(remain>0)
            page_count++;
        if(page>page_count) {
            logger.debug("参数不合法");
            return ResponseUtil.illegalParameter();
        }
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

        String id = request.getParameter("id");
        Integer userId = null;
        if (id != null && !"".equals(id)) {
            userId = Integer.valueOf(id);
        } else {
            //出错
//            return ResponseUtil.fail(581,"id丢失");
            logger.debug("未登录");
            return ResponseUtil.unlogin();
        }
        if(page<0||limit<0) {
            logger.debug("参数不合法");
            return ResponseUtil.illegalParameter();
        }
//            return ResponseUtil.fail();
        List<Address> subList= new ArrayList<Address>();
        List<Address> addressList= addressService.getAllAddressById(userId);
        if(addressList.size()==0) {
            logger.debug("该地址是无效地址");
            return ResponseUtil.invalid();
        }
        int page_count=addressList.size()/limit;
        int remain=addressList.size()%limit;
        if(remain>0)
            page_count++;
        if(page>page_count) {
            logger.debug("参数不合法");
            return ResponseUtil.illegalParameter();
        }
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

        if(id<0) {
            logger.debug("参数不合法");
            return ResponseUtil.illegalParameter();
        }
        Address address = addressService.getAddressById(id);
        if(address==null) {
            logger.debug("该地址是无效地址");
            return ResponseUtil.invalid();
        }
        else
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
        if(addressPo1==null) {
            logger.debug("地址新增失败");
            return ResponseUtil.addFail();
        }
        else
            return ResponseUtil.ok(addressPo1);
    }



    /**

     * 用户修改地址

     *
     * @paramBody addressPo

     * @return 用户地址信息

     */

    @PutMapping("/addresses/{id}")

    public Object editAddress(@PathVariable Integer id, @RequestBody AddressPo addressPo) {

        if(id<0) {
            logger.debug("参数不合法");
            return ResponseUtil.illegalParameter();
        }

        AddressPo addressPo1 = addressService.editAddress(id,addressPo);
        if(addressPo1==null) {
            logger.debug(("地址修改失败"));
            return ResponseUtil.updateFail();
        }
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

        if(id<0) {
            logger.debug("参数不合法");
            return ResponseUtil.illegalParameter();
        }
        if(addressService.deleteAddress(id)>0) {
            return ResponseUtil.ok();
        }
        else {
            logger.debug("地址删除失败");
            return ResponseUtil.deleteFail();
        }
    }





}
