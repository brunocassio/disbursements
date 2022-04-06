package com.sequra.disbursements.repository;

import com.sequra.disbursements.domain.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends PagingAndSortingRepository<Order, Integer> {

    @Query("from Order o where o.completedAt is not null and o.disbursement is null")
    List<Order> fetchCompletedOrdersWithoutPagination();

    @Query("from Order o where o.disbursement is not null")
    List<Order> fetchOrdersWithDisbursement(Pageable pageable);

    @Query("from Order o inner join o.merchant m where m.id = o.merchant.id and m.id = :id and o.disbursement is not null")
    List<Order> listByMerchantId(@Param("id") Integer id, Pageable pageable);
}
