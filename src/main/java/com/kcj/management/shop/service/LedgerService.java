package com.kcj.management.shop.service;

import com.kcj.management.shop.exception.NotExistException;
import com.kcj.management.shop.model.order.Department;
import com.kcj.management.shop.model.order.Ledger;
import com.kcj.management.shop.repository.LedgerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LedgerService {
    @Autowired
    private LedgerRepository ledgerRepository;

    @Transactional
    public void saveLedger(Ledger ledger) {
        ledgerRepository.save(ledger);
    }

    public Ledger findById(Long id) {
        Optional<Ledger> optionalLedger = ledgerRepository.findById(id);

        if(optionalLedger.isPresent()) {
            return optionalLedger.get();
        } else {
            throw new NotExistException(getClass().getSimpleName());
        }
    }

    public List<Ledger> findByDepartment(Department department) {
        return ledgerRepository.findByDepartment(department);
    }
}
