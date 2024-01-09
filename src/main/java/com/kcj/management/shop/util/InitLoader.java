package com.kcj.management.shop.util;

import com.kcj.management.shop.model.staff.Staff;
import com.kcj.management.shop.model.staff.StaffRole;
import com.kcj.management.shop.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitLoader implements CommandLineRunner {
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${shop.security.staff.password.suffix}")
    private String suffix;

    @Override
    public void run(String... args) throws Exception {
        for(StaffRole role :StaffRole.values()){
            createUser(role.name().toLowerCase(), role.name().toLowerCase() + suffix, role);
        }
    }

    private void createUser(String userName, String password, StaffRole role){
        if(staffRepository.findByName(userName) == null) {
            Staff staff = new Staff();
            staff.setName(userName);
            staff.setPassword(passwordEncoder.encode(password));
            staff.setStaffRole(role);

            staffRepository.save(staff);
        }
    }
}
