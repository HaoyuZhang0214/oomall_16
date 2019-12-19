package xmu.oomall.address.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import xmu.oomall.address.domain.Address;
import xmu.oomall.address.domain.AddressPo;
import xmu.oomall.address.mapper.AddressMapper;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class AddressDao {

    @Autowired
    AddressMapper addressMapper;


    public List<Address> getAllUserAddress(@RequestParam Integer userId, @RequestParam String name) {
        List<AddressPo> addressPoList = addressMapper.getAllUserAddress(userId, name);
        List<Address> addresses = null;
        for(AddressPo addressPo: addressPoList) {
            Address address = (Address) addressPo;
            addresses.add(address);
        }
        return addresses;
    }

    public List<Address> getAllAddressById(@RequestParam Integer userId) {

        List<AddressPo> addressPoList = addressMapper.getAllAddressById(userId);
        List<Address> addresses = null;
        for(AddressPo addressPo: addressPoList) {
            Address address = (Address) addressPo;
            addresses.add(address);
        }
        return addresses;
    }

    public AddressPo addAddress( AddressPo addressPo){
        addressPo.setBeDeleted(false);
        addressPo.setGmtCreate(LocalDateTime.now());
        addressPo.setGmtModified(LocalDateTime.now());
        if(addressMapper.addAddress(addressPo)>0)
            return addressPo;
        else
            return null;
    }

    public Address getAddressById(@RequestParam Integer id) {

        AddressPo addressPo = addressMapper.getAddressById(id);
        Address address = (Address) addressPo;
        return address;
    }

    public AddressPo editAddress(@RequestParam Integer id, @RequestBody AddressPo addressPo) {
        addressPo.setId(id);
        addressPo.setGmtModified(LocalDateTime.now());
        if(addressMapper.editAddress(addressPo)>0) {
            return addressPo;
        }
        else {
            return null;
        }
    }

    public Integer deleteAddress(@RequestParam Integer id) {
        return addressMapper.deleteAddress(id);
    }

}
