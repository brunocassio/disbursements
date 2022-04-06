package com.sequra.disbursements;

import com.sequra.disbursements.controller.DisbursementController;
import com.sequra.disbursements.dto.DisbursementDTO;
import com.sequra.disbursements.service.DisbursementService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test cases for DisbursementController
 */
@WebMvcTest(DisbursementController.class)
public class DisbursementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DisbursementService service;

    @Test
    @DisplayName("listByMerchantId: test if it return a list of DisbursementDTO and if performs a get request with status OK")
    public void listByMerchantIdTest() throws Exception {
        List<DisbursementDTO> list = new ArrayList<>();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.listById(1, pageable)).thenReturn(list);
        this.mockMvc.perform(get("/api/v1/disbursement/merchant")
                .param("id", "1")
                .param("page", "0"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    @DisplayName("listDisbursements: test if it return a list of DisbursementDTO and if performs a get request with status OK")
    public void listDisbursementsTest() throws Exception {
        List<DisbursementDTO> list = new ArrayList<>();
        Pageable pageable = PageRequest.of(0, 10);
        when(service.listAll(pageable)).thenReturn(list);
        this.mockMvc.perform(get("/api/v1/disbursement").param("page", "0")).andDo(print()).andExpect(status().isOk());
    }
}
