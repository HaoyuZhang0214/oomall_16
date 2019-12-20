package xmu.oomall.address.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import xmu.oomall.address.domain.Address;
import xmu.oomall.address.domain.AddressPo;

import java.util.List;

/**
 * @author zhy
 */
@Service
public interface AddressService {


    /**
     * 获得用户地址
     * @param userId
     * @param name
     * @return List<Address>
     */
    public List<Address> getAllUserAddress(@RequestParam Integer userId, @RequestParam String name);


    /**
     * 获得地址
     * @param userId
     * @return List<Address>
     */

    public List<Address> getAllAddressById(@RequestParam Integer userId);


    /**
     * 添加地址
     * @param addressPo
     * @return AddressPo
     */
    public AddressPo addAddress(@RequestBody AddressPo addressPo);


    /**
     * 获得地址
     * @param id
     * @return Address
     */
    public Address getAddressById(@RequestParam Integer id);


    /**
     * 修改地址
     * @param id
     * @param addressPo
     * @return AddressPo
     */
    public AddressPo editAddress(@RequestParam Integer id, @RequestBody AddressPo addressPo);

    /**
     * 删除地址
     * @param id
     * @return Integer
     */
    public Integer deleteAddress(@RequestParam Integer id);
}
