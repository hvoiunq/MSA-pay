package com.fastcampuspay.banking.adapter.in.web;

import com.fastcampuspay.banking.adapter.in.web.dto.request.RegisterBankAccountRequest;
import com.fastcampuspay.banking.adapter.in.web.dto.response.BankAccountResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
class RegisterBankAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    private static final String REGISTER_BANK_ACCOUNT_ENDPOINT = "/banking/account/register";
    private static final RegisterBankAccountRequest request = new RegisterBankAccountRequest(1L, "국민은행", "1111-2222", true);
    private static final BankAccountResponse expect = new BankAccountResponse(1L, request.membershipId(), request.bankName(), request.bankAccountNumber(), request.isValid());

    @Test
    void registerBankAccountTest() throws Exception {


        mockMvc.perform(
                        MockMvcRequestBuilders.post(REGISTER_BANK_ACCOUNT_ENDPOINT)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(request))
                )
                .andExpect(MockMvcResultMatchers.status().isCreated());
//                .andExpect(MockMvcResultMatchers.content().string(mapper.writeValueAsString(expect)));

    }

}
