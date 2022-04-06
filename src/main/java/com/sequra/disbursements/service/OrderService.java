package com.sequra.disbursements.service;

import com.sequra.disbursements.domain.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

     List<Order> listByMerchantId(Integer id, Pageable pageable);
     List<Order> listOrdersWithDisbursements(Pageable pageable);
}
