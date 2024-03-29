package com.kcj.management.shop.service;

import com.kcj.management.shop.exception.IdNotFoundException;
import com.kcj.management.shop.model.dto.order.OrderDTO;
import com.kcj.management.shop.model.dto.order.OrderSettle;
import com.kcj.management.shop.model.order.*;
import com.kcj.management.shop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemService orderItemService;

    public static int TABLE_COUNT;

    @Value("${shop.calc.table.count}")
    public void setTableCount(int value) {
        TABLE_COUNT = value;
    }

    @Transactional
    public void saveOrder(Order order){
        orderRepository.save(order);
    }

    public Order findById(Long id){
        Optional<Order> optionalOrder = orderRepository.findById(id);

        if(optionalOrder.isPresent()){
            return optionalOrder.get();
        } else {
            throw new IdNotFoundException(getClass().getName());
        }
    }

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public List<Order> findByPayType(PayType payType) {
        return orderRepository.findByPayType(payType);
    }

    public List<Order> findByWorkStatus(WorkStatus workStatus) {
        return orderRepository.findByWorkStatus(workStatus);
    }

    public List<Order> findByOrderType(OrderType orderType) {
        return orderRepository.findByOrderType(orderType);
    }
    public Order findByHtmlId(String htmlId) {return orderRepository.findByHtmlId(htmlId);}
    public List<Order> findByPayTypeAndWorkStatus(PayType payType, WorkStatus workStatus) {
        return orderRepository.findByPayTypeAndWorkStatus(payType, workStatus);
    }

    @Transactional
    public void addOrderItem(Long orderId, OrderItem orderItem) {
        findById(orderId).addOrderItem(orderItem);
    }

    @Transactional
    public void removeOrderItem(Long orderId, OrderItem orderItem) {
        findById(orderId).removeOrderItem(orderItem);
    }


    public List<Order> findByDepartment(Department department) {
        return orderRepository.findByDepartment(department);
    }

    public List<Order> departmentPayment(Department department) {
        return orderRepository.findByDepartmentAndLedger(department, null);
    }

    public List<Order> departmentCompletePayment(Department department){
        return orderRepository.findByDepartmentAndLedgerIsNotNull(department);
    }

    @Transactional
    public void orderCookComplete(Long id) {
        Order order = findById(id);
        for(OrderItem item :order.getOrderItems()){
            orderItemService.orderItemCookComplete(item.getId());
        }
    }

    public List<Order> findByAddress(Address address) {
        return orderRepository.findByAddress(address);
    }

    public List<Order> todayOrderList() {
        return orderRepository.todayOrderList();
    }

    public List<OrderDTO> orderTodayDTOS() {return orderRepository.todayOrderTodayDTOList();}

    public List<OrderDTO> kitchenOrderList() {
        return orderRepository.kitchenOrderList();
    }
    public List<OrderSettle> orderSettlesYear(int year) {
        return orderRepository.settleOrders(year);
    }

    public boolean[] todayUsedHoleTable() { return orderRepository.todayUsedHoleTables(); }
    public Order getHoleTableOrder(int tableNum){ return orderRepository.findByOrderTypeAndTableNumAndCompleteIsFalse(OrderType.HOLE, tableNum); }
}
