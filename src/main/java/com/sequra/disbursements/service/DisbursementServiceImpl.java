package com.sequra.disbursements.service;

import com.sequra.disbursements.domain.Order;
import com.sequra.disbursements.dto.DisbursementDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DisbursementServiceImpl implements DisbursementService{

    @Autowired
    private OrderService orderService;

    @Override
    public List<DisbursementDTO> listById(Integer id, Pageable pageable) {
        List<Order> list  = orderService.listByMerchantId(id, pageable);
        return convert(list);
    }

    @Override
    public List<DisbursementDTO> listAll(Pageable pageable) {
        List<Order> orders = orderService.listOrdersWithDisbursements(pageable);
        return convert(orders);
    }

    private List<DisbursementDTO> convert(List<Order> orders) {
        DisbursementDTO dto;
        List<DisbursementDTO> list = new ArrayList<>();
        for (Order order : orders) {
            dto = new DisbursementDTO();
            dto.setMerchantName(order.getMerchant().getName());
            dto.setOrderId(order.getId().toString());
            dto.setValue(order.getDisbursement().toString());
            dto.setMerchantId(order.getMerchant().getId().toString());
            list.add(dto);
        }
        return list;
    }
}
