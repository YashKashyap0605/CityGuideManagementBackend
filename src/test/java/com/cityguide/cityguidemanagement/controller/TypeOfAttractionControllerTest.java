package com.cityguide.cityguidemanagement.controller;

//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import com.cityguide.cityguidemanagement.entity.TypeOfAttraction;
import com.cityguide.cityguidemanagement.service.TypeOfAttractionService;

public class TypeOfAttractionControllerTest {
	private MockMvc mockMvc;

    @Mock
    private TypeOfAttractionService typeOfAttractionService;

    @InjectMocks
    private TypeOfAttractionController typeOfAttractionController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(typeOfAttractionController).build();
    }

    @Test
    public void testAddTypeOfAttraction() throws Exception {
        // Prepare data
        TypeOfAttraction type = new TypeOfAttraction();
        type.setId(1);
        type.setName("Museum");

        when(typeOfAttractionService.addTypeOfAttraction(any(TypeOfAttraction.class))).thenReturn(type);

        // Perform the MVC request and assertions
        mockMvc.perform(post("/addtypeofattraction")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 1, \"name\": \"Museum\"}"))
                .andExpect(status().isOk());

        verify(typeOfAttractionService).addTypeOfAttraction(any(TypeOfAttraction.class));
    }

    @Test
    public void testDeleteTypeOfAttraction() throws Exception {
        // Perform the MVC request and assertions
        mockMvc.perform(delete("/deletetypeofattraction/{id}", 1))
                .andExpect(status().isOk());

        verify(typeOfAttractionService).deleteTypeOfAttraction(1);
    }

}
