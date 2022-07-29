package com.cts.networth.controller;

import com.cts.networth.feign.AuthorizationClient;
import com.cts.networth.model.MutualFundDetail;
import com.cts.networth.model.PortfolioDetail;
import com.cts.networth.service.CalculateNetWorthServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CalculateNetWorthControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CalculateNetWorthServiceImpl service;
    @MockBean
    private AuthorizationClient authClient;


    @Test
    void test1() {
        assertThat(service).isNotNull();
    }
    @Test
    void test2() {
        assertThat(authClient).isNotNull();
    }
    @Test
    void getPortfolioDetailWithWrongToken() throws Exception {
        when(authClient.authorizeTheRequest("@uthoriz@tionToken123")).thenReturn(true);
        this.mockMvc.perform(get("/getPortfolioDetail/1").header("Authorization", "@WrongToken"))
                .andExpect(status().isUnauthorized());
    }


}