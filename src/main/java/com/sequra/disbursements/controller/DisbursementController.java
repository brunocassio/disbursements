package com.sequra.disbursements.controller;

import com.sequra.disbursements.dto.DisbursementDTO;
import com.sequra.disbursements.service.DisbursementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/disbursement")
public class DisbursementController {

    @Autowired
    private DisbursementService disbursementService;

    @GetMapping(value = "/merchant")
    public @ResponseBody
    ResponseEntity<List<DisbursementDTO>> listByMerchantId(@RequestParam("id") String id, @RequestParam("page") String page) {
        Pageable pageable = PageRequest.of(Integer.parseInt(page), 10);
        Integer merchantId = Integer.parseInt(id);
        List<DisbursementDTO> list = disbursementService.listById(merchantId, pageable);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping
    public @ResponseBody ResponseEntity<List<DisbursementDTO>> listDisbursements(@RequestParam("page") String page) {
        Pageable pageable = PageRequest.of(Integer.parseInt(page), 10, Sort.by("id"));
        List<DisbursementDTO> list = disbursementService.listAll(pageable);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
