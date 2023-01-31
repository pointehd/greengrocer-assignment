package com.assignment.greengrocer.greengrocer;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.assignment.greengrocer.common.model.exception.ApiErrorException;
import com.assignment.greengrocer.greengrocer.external.GreengrocerType;
import com.assignment.greengrocer.greengrocer.model.PriceResponse;
import com.assignment.greengrocer.greengrocer.service.GreengrocerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(GreengrocerController.class)
class GreengrocerControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GreengrocerService greengrocerService;

    @DisplayName("청과물 목록 조회 정상 요청")
    @Test
    void getSuccessTest() throws Exception {
        // when
        // then
        mvc.perform(get("/greengrocer"))
            .andExpect(status().is2xxSuccessful());
    }

    @DisplayName("과일 목록 조회 정상 요청")
    @Test
    void getProductFruitTest() throws Exception {
        // given
        given(greengrocerService.getProduct(GreengrocerType.FRUIT)).willReturn(anyList());

        // when
        // then
        mvc.perform(get("/greengrocer/FRUIT"))
            .andExpect(status().is2xxSuccessful());
        then(greengrocerService).should().getProduct(GreengrocerType.FRUIT);
    }

    @DisplayName("과일 목록 조회 에러 요청")
    @Test
    void getProductErrorTest() throws Exception {
        // when
        // then
        mvc.perform(get("/greengrocer/test"))
            .andExpect(status().is4xxClientError());
    }

    @Test
    void getProductPriceTest() throws Exception {
        // given
        String item = "사과";
        given(greengrocerService.getPrice(GreengrocerType.FRUIT, item)).willReturn(
            new PriceResponse(item, 1000L));

        // when
        // then
        mvc.perform(get("/greengrocer/FRUIT/" + item))
            .andExpect(status().is2xxSuccessful());
        then(greengrocerService).should().getPrice(GreengrocerType.FRUIT, item);
    }

    @Test
    void getProductPriceErrorTest() throws Exception {
        // given
        String item = "코끼리";
        given(greengrocerService.getPrice(GreengrocerType.FRUIT, item)).willThrow(
            new ApiErrorException("오류발생"));

        // when
        // then
        mvc.perform(get("/greengrocer/FRUIT/" + item))
            .andExpect(status().is5xxServerError());
    }

}