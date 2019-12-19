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

    public List<AddressPo> getAllUserAddress(Integer userId, String name);

    public List<AddressPo> getAllAddressById(Integer userId);

    public Integer addAddress(AddressPo addressPo);

    public AddressPo getAddressById(Integer id);

    public Integer editAddress(AddressPo addressPo);

    public Integer deleteAddress( Integer id);

}
