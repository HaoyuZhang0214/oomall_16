package xmu.oomall.address.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import xmu.oomall.address.domain.AddressPo;

import java.util.List;

@Mapper
@Repository
public interface AddressMapper {

    public List<AddressPo> getAllUserAddress(@RequestParam Integer userId, @RequestParam String name);

    public List<AddressPo> getAllAddressById(@RequestParam Integer userId);

    public Integer addAddress(AddressPo addressPo);

    public AddressPo getAddressById(@RequestBody Integer id);

    public Integer editAddress(@RequestBody AddressPo addressPo);

    public Integer deleteAddress(@RequestParam Integer id);

}
