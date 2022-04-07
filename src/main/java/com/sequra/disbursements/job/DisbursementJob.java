package com.sequra.disbursements.job;

import com.sequra.disbursements.domain.Order;
import com.sequra.disbursements.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is responsible for calculating the disbursement
 * @Scheduled anotation contains the cron expression which means (seconds, minutes, hour, year, month, day of the week)
 */
@Component
public class DisbursementJob {

    private static final Logger logger = Logger.getLogger(DisbursementJob.class.getName());

    private static final Double FEE_OF_1_PERCENT = 0.01;
    private static final Double FEE_OF_095_PERCENT = 0.0095;
    private static final Double FEE_OF_085_PERCENT = 0.0085;

    @Autowired
    private OrderRepository orderRepository;

    @Scheduled(cron = "0 0 0 * * MON")
    public void run() {
        List<Order> completedOrders = orderRepository.fetchCompletedOrdersWithoutPagination();
        logger.log(Level.INFO, "Calculating {0} orders ", completedOrders.size());
        List<Order> updatedOrders = calculate(completedOrders);
        orderRepository.saveAll(updatedOrders);
        logger.log(Level.INFO, "The calculation of {0} orders has been finished!", completedOrders.size());
    }

    public List<Order> calculate(List<Order> completedOrders) {
        List<Order> updatedOrders = new ArrayList<>();
        for (Order order : completedOrders) {

            if (order.getAmount() < 50) {
                Double fee = order.getAmount() * FEE_OF_1_PERCENT;
                Double disbursement = order.getAmount() - fee;
                BigDecimal bd = new BigDecimal(disbursement).setScale(2, RoundingMode.HALF_UP);
                order.setDisbursement(bd.doubleValue());

            } else if (order.getAmount() >= 50 && order.getAmount() <= 300) {
                Double fee = order.getAmount() * FEE_OF_095_PERCENT;
                Double disbursement = order.getAmount() - fee;
                BigDecimal bd = new BigDecimal(disbursement).setScale(2, RoundingMode.HALF_UP);
                order.setDisbursement(bd.doubleValue());

            } else {
                Double fee = order.getAmount() * FEE_OF_085_PERCENT;
                Double disbursement = order.getAmount() - fee;
                BigDecimal bd = new BigDecimal(disbursement).setScale(2, RoundingMode.HALF_UP);
                order.setDisbursement(bd.doubleValue());
            }
            updatedOrders.add(order);
        }
        return updatedOrders;
    }
}
