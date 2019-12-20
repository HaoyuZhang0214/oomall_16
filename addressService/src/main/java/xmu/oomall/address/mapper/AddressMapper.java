package xmu.oomall.address.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import xmu.oomall.address.domain.AddressPo;

import java.util.List;

/**
 * @author zhy
 */
@Mapper
@Repository
public interface AddressMapper {

    /**
     * 获得地址
     *
     * @param userId
     * @param name
     * @return List<AddressPo>
     */
    public List<AddressPo> getAllUserAddress(Integer userId, String name);


    /**
     * 获得地址
     *
     * @param userId
     * @return List<AddressPo>
     */
    public List<AddressPo> getAllAddressById(Integer userId);

    /**
     * 添加地址
     * @param addressPo
     * @return Integer
     */
    public Integer addAddress(AddressPo addressPo);

    /**
     * 获得地址
     *
     * @param id
     * @return AddressPo
     */
    public AddressPo getAddressById(Integer id);


    /**
     * 修改
     * @param addressPo
     * @return Integer
     */
    public Integer editAddress(AddressPo addressPo);


    /**
     * 删除
     * @param id
     * @return Integer
     */
    public Integer deleteAddress( Integer id);

}
