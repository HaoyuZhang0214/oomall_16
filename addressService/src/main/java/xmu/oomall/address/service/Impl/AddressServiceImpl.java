package xmu.oomall.address.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import xmu.oomall.address.dao.AddressDao;
import xmu.oomall.address.domain.Address;
import xmu.oomall.address.domain.AddressPo;
import xmu.oomall.address.service.AddressService;

import java.util.List;


@Service
public class AddressServiceImpl implements AddressService {


    @Autowired
    AddressDao addressDao;

    public List<Address> getAllUserAddress(@RequestParam Integer userId, @RequestParam String name) {

        return addressDao.getAllUserAddress(userId,name);

    }

    public List<Address> getAllAddressById(@RequestParam Integer userId) {
        return addressDao.getAllAddressById(userId);
    }

    public AddressPo addAddress(AddressPo addressPo){
        return addressDao.addAddress(addressPo);
    }

    public Address getAddressById(@RequestParam Integer id) {
        return addressDao.getAddressById(id);
    }

    public AddressPo editAddress(@RequestParam Integer id, @RequestBody AddressPo addressPo) {
        return addressDao.editAddress(id,addressPo);
    }

    public Integer deleteAddress(@RequestParam Integer id) {
        return addressDao.deleteAddress(id);
    }

}
