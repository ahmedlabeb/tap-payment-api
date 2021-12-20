package com.tappayment.api.boundry;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tappayment.api.boundry.helper.CustomerDto;
import com.tappayment.api.boundry.helper.FeesDto;
import com.tappayment.api.boundry.helper.TopupRequestDto;
import com.tappayment.api.boundry.helper.TopupResponseDto;
import com.tappayment.api.control.WalletService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.math.BigDecimal;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class WalletApiTest {
    @MockBean
    WalletService walletService;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    private final ObjectMapper mapper = new ObjectMapper();

    @InjectMocks
    WalletApi walletApi;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void testTopupWithoutBody() throws Exception {
        mockMvc.perform(post("/api/topup")
                        .content(mapper.writeValueAsString(buildTopupRequestDtoWithoutFields()))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testTopupWithBodyMissingMandatoryField() throws Exception {
        mockMvc.perform(post("/api/topup")
                        .content(mapper.writeValueAsString(buildTopupRequestDtoWithMissingMandatoryField()))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testTopupSuccessful() throws Exception {
        lenient().when(walletService.topupCustomerWallet(any(TopupRequestDto.class))).thenReturn(new TopupResponseDto());
        mockMvc.perform(post("/api/topup")
                        .content(mapper.writeValueAsString(buildTopupRequestDto()))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    private TopupRequestDto buildTopupRequestDtoWithoutFields() {
        return new TopupRequestDto();
    }

    private TopupRequestDto buildTopupRequestDtoWithMissingMandatoryField() {
        return TopupRequestDto.builder().topupAmount(BigDecimal.valueOf(10.2))
                .topupCurrency("AED")
                .feesDto(FeesDto.builder().amount(BigDecimal.valueOf(1.2)).currency("AED").build())
                .customerDto(CustomerDto.builder().walletId("123").id("1").build())
                .build();
    }
    private TopupRequestDto buildTopupRequestDto() {
        return TopupRequestDto.builder().topupAmount(BigDecimal.valueOf(10.2))
                .topupCurrency("AED")
                .chargeId("1")
                .feesDto(FeesDto.builder().amount(BigDecimal.valueOf(1.2)).currency("AED").build())
                .customerDto(CustomerDto.builder().walletId("123").id("1").build())
                .build();
    }
}
