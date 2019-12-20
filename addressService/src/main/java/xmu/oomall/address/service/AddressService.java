package xmu.oomall.address.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import xmu.oomall.address.domain.Address;
import xmu.oomall.address.domain.AddressPo;

import java.util.List;

@Service
public interface AddressService {



    public List<Address> getAllUserAddress(@RequestParam Integer userId, @RequestParam String name);

    public List<Address> getAllAddressById(@RequestParam Integer userId);

    public AddressPo addAddress(@RequestBody AddressPo addressPo);

    public Address getAddressById(@RequestParam Integer id);

    public AddressPo editAddress(@RequestParam Integer id, @RequestBody AddressPo addressPo);

    public Integer deleteAddress(@RequestParam Integer id);
}
