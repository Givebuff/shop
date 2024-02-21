package com.kcj.management.shop.service;

import com.kcj.management.shop.model.order.Order;
import com.kcj.management.shop.repository.OrderRepository;
import com.kcj.management.shop.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public void saveOrder(Order order){
        orderRepository.save(order);
    }

    public Order findById(Long id){
        Optional<Order> optionalOrder = orderRepository.findById(id);

        if(optionalOrder.isPresent()){
            return optionalOrder.get();
        } else {
            throw new RuntimeException("error : " + id + " order id not exist");
        }
    }

    public int getTotalPrice(Long id){
        return findById(id).getTotalPrice();
    }

    public List<Order> searchOrderTime(
            int py, int pM, int pd, int ph, int pm, int ps,
            int ay, int aM, int ad, int ah, int am, int as
    ) {
        return orderRepository.findByOrderDateBetween(
                DateUtil.intToLocalDateTime(py,pM,pd,ph,pm,ps),
                DateUtil.intToLocalDateTime(ay,aM,ad,ah,am,as));
    }

    public List<Order> searchPaymentDate(
            int py, int pM, int pd, int ph, int pm, int ps,
            int ay, int aM, int ad, int ah, int am, int as
    ) {
        return orderRepository.findByPaymentDateBetween(
                DateUtil.intToLocalDateTime(py,pM,pd,ph,pm,ps),
                DateUtil.intToLocalDateTime(ay,aM,ad,ah,am,as));
    }
}
