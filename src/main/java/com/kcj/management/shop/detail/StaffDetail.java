package com.kcj.management.shop.detail;

import com.kcj.management.shop.model.staff.Staff;
import com.kcj.management.shop.model.staff.StaffRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

public class StaffDetail implements UserDetails {
    private Staff staff;

    public StaffDetail(Staff staff){
        this.staff = staff;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new HashSet<>();

        collection.add(new SimpleGrantedAuthority(StaffRole.getRole(staff.getStaffRole())));

        return collection;
    }

    @Override
    public String getPassword() {
        return staff.getPassword();
    }

    @Override
    public String getUsername() {
        return staff.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
