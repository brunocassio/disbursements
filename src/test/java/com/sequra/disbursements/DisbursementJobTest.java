package com.sequra.disbursements;

import com.sequra.disbursements.domain.Order;
import com.sequra.disbursements.job.DisbursementJob;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

/**
 * Test case for the calculation of the Disbursements
 */
public class DisbursementJobTest {

    DisbursementJob job;

    @BeforeEach
    public void setUp() {
        job = new DisbursementJob();
    }

    @Test
    @DisplayName("the calculation must be 1%")
    public void testCalculateLessThan50() {
        List<Order> orders = getOrdersByAmount(10d);
        Order orderWithDisbursement = job.calculate(orders).get(0);
        assertEquals(9.9, orderWithDisbursement.getDisbursement());
    }

    @Test
    @DisplayName("the calculation must be 0.95%")
    public void testCalculateBetween50to300() {
        List<Order> orders = getOrdersByAmount(100d);
        Order orderWithDisbursement = job.calculate(orders).get(0);
        assertEquals(99.05, orderWithDisbursement.getDisbursement());
    }

    @Test
    @DisplayName("the calculation must be 0.85%")
    public void testCalculateMoreThan300() {
        List<Order> orders = getOrdersByAmount(1000d);
        Order orderWithDisbursement = job.calculate(orders).get(0);
        assertEquals(991.5, orderWithDisbursement.getDisbursement());
    }

    private List<Order> getOrdersByAmount(Double amount) {
        List<Order> list = new ArrayList<>();
        Order order = new Order();
        order.setAmount(amount);
        list.add(order);
        return list;
    }
}
