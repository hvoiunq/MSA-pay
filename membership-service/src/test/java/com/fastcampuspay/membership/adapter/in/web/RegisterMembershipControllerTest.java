package com.fastcampuspay.membership.adapter.in.web;

import com.fastcampuspay.membership.adapter.in.web.dto.request.RegisterMembershipRequest;
import com.fastcampuspay.membership.adapter.in.web.dto.response.MembershipResponse;
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
class RegisterMembershipControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    private static final String MEMBERSHIP_URI ="/membership/register";

    private static final RegisterMembershipRequest request = new RegisterMembershipRequest("name", "address", "email", false);
    private static final MembershipResponse expect = new MembershipResponse(1L, request.getName(), request.getAddress(), request.getEmail(), true, request.isCorp());

    @Test
    void registerMembership() throws Exception {


        mockMvc.perform(
                        MockMvcRequestBuilders.post(MEMBERSHIP_URI)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(request))
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string(mapper.writeValueAsString(expect)));
    }
}
