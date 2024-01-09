package com.kcj.management.shop.service;

import com.kcj.management.shop.detail.StaffDetail;
import com.kcj.management.shop.model.staff.Staff;
import com.kcj.management.shop.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class StaffService implements UserDetailsService {
    @Autowired
    private StaffRepository staffRepository;

    private Staff findByUsername(String username){
        return staffRepository.findByName(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Staff staff = findByUsername(username);
        if(staff == null){
            throw new UsernameNotFoundException("Login Fail");
        }
        return new StaffDetail(staff);
    }
}
