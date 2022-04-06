package com.sequra.disbursements.service;

import com.sequra.disbursements.dto.DisbursementDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DisbursementService {

    List<DisbursementDTO> listById(Integer id, Pageable pageable);
    List<DisbursementDTO> listAll(Pageable pageable);
}
