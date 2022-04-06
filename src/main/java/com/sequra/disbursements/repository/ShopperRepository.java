package com.sequra.disbursements.repository;

import com.sequra.disbursements.domain.Shopper;
import org.springframework.data.repository.CrudRepository;

public interface ShopperRepository extends CrudRepository<Shopper, Integer> {
}
