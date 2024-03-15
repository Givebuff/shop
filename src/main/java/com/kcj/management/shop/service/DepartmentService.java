package com.kcj.management.shop.service;

import com.kcj.management.shop.exception.NotExistException;
import com.kcj.management.shop.model.order.Department;
import com.kcj.management.shop.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional
    public void saveDepartment(Department department) {
        if(departmentRepository.findBySectionAndDept(department.getSection(), department.getDept()).isPresent()) {
            throw new NotExistException(getClass().getSimpleName());
        } else {
            departmentRepository.save(department);
        }
    }

    public Department findById(Long id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if(optionalDepartment.isPresent()) {
            return optionalDepartment.get();
        } else {
            throw new NotExistException(getClass().getName());
        }
    }

    public List<String> findAllSection() {
        return departmentRepository.findDistinctSection();
    }

    public List<Department> findBySection(String section) {
        return departmentRepository.findBySection(section);
    }

    public Department findBySectionAndDepartment(String section, String dept) {
        Optional<Department> optionalDepartment = departmentRepository.findBySectionAndDept(section, dept);
        if(optionalDepartment.isPresent()) {
            return optionalDepartment.get();
        } else {
            throw new NotExistException(getClass().getSimpleName());
        }
    }
}
