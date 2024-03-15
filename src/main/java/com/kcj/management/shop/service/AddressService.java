package com.kcj.management.shop.service;

import com.kcj.management.shop.exception.NotExistException;
import com.kcj.management.shop.model.order.Address;
import com.kcj.management.shop.repository.AddressRepository;
import com.kcj.management.shop.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Transactional
    public void saveAddress(Address address) {
        addressRepository.save(address);
    }

    public Address findById(Long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);

        if(optionalAddress.isPresent()) {
            return optionalAddress.get();
        } else {
            throw new NotExistException(getClass().getSimpleName());
        }
    }

    public List<Address> findByPhoneNumber(String phoneNumber) {
        return addressRepository.findByPhoneNumberLike(StringUtil.likeDelimiterString(phoneNumber, 11));
    }

    public List<Address> findByAddress(String address) {
        return addressRepository.findByAddressLike(StringUtil.likeDelimiterString(address, 11));
    }

    public List<Address> findByDetail(String detail) {
        return addressRepository.findByDetailLike(StringUtil.likeDelimiterString(detail, 11));
    }
}
