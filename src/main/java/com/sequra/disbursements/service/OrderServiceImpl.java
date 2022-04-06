package com.sequra.disbursements.service;

import com.sequra.disbursements.domain.Order;
import com.sequra.disbursements.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> listByMerchantId(Integer id, Pageable pageable) {
        return orderRepository.listByMerchantId(id, pageable);
    }

    @Override
    public List<Order> listOrdersWithDisbursements(Pageable pageable) {
        return orderRepository.fetchOrdersWithDisbursement(pageable);
    }
}
