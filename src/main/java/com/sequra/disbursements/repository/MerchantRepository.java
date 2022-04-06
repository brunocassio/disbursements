package com.sequra.disbursements.repository;

import com.sequra.disbursements.domain.Merchant;
import org.springframework.data.repository.CrudRepository;

public interface MerchantRepository extends CrudRepository<Merchant, Integer> {
}
