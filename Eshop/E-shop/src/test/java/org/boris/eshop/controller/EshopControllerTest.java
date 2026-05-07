package org.boris.eshop.controller;

import org.boris.eshop.model.request.CustomerOrder;
import org.boris.eshop.service.EshopService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EshopController.class)
class EshopControllerTest {

    @MockitoBean
    EshopService eshopService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreatedOrder() throws Exception {
        String json = """
    {
        "customerEmail": "test@test.sk",
        "orderedItem": "Book",
        "orderedQuantity": 2
    }
    """;

        mockMvc.perform(post("/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(content().string("Order has been sent"));

        verify(eshopService).createOrder(any(CustomerOrder.class));
    }
}