package com.kcj.management.shop.repository;

import com.kcj.management.shop.model.order.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByPhoneNumberLike(String phoneNumber);

    List<Address> findByAddressLike(String Address);
}
