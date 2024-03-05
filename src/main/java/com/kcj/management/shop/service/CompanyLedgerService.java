package com.kcj.management.shop.service;

import com.kcj.management.shop.repository.CompanyLedgerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyLedgerService {
    @Autowired
    private CompanyLedgerRepository companyLedgerRepository;
}
