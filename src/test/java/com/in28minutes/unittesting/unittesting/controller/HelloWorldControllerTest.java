package com.in28minutes.unittesting.unittesting.controller;

import com.in28minutes.unittesting.unittesting.business.ItemBusinessService;
import com.in28minutes.unittesting.unittesting.model.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HelloWorldController.class)
public class HelloWorldControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemBusinessService itemBusinessService;

    @Test
    public void helloWorld_basic() throws Exception {
        //call GET "/hello-world" application/Json
        RequestBuilder request = MockMvcRequestBuilders
                .get("/hello-world")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World"))
                .andReturn();

        //Verify "Hello World"
        assertEquals("Hello World", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void dummyItem_basic() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/dummy-item")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content()
                        .json("{\"id\":1,\"name\":\"Item\",\"price\":10,\"quantity\":100}"))
                .andReturn();
    }

    @Test
    public void itemFromBusinessService_basic() throws Exception {
        when(itemBusinessService.retrieveHardcodedItem())
                .thenReturn(new Item(2, "Phone", 2000, 30));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/item-from-business-service")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content()
                        .json("{\"id\":2,\"name\":\"Phone\",\"price\":2000,\"quantity\":30}"))
                .andReturn();
    }

    @Test
    public void retrieveAllItems() throws Exception {
        when(itemBusinessService.retrieveAllItems())
                .thenReturn(
                        Arrays
                                .asList(
                                        new Item(2, "Phone", 2000, 30),
                                        new Item(3, "Phone2", 3000, 40)));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/all-items-from-database")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content()
                        .json(
                                "[{id:2,name:Phone,price:2000,quantity:30}," +
                                        "{id:3,name:Phone2,price:3000,quantity:40}]"))
                .andReturn();
    }
}
