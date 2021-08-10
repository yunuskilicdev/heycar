package com.kilic.yunus.heycar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kilic.yunus.heycar.dto.ListingDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import javax.validation.ConstraintViolationException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DealerControllerTest {

    private final long validDealerId = 1L, invalidDealerId = 9999L;
    ListingDto validSkodaRed, validSkodaBlue;

    String invalidListingDto = "[{\"code\":\"1\",\"make\":\"Skoda\",\"model\":\"Octavia\",\"year\":2015,\"price\":20000,\"kw\":86}]";
    MockMultipartFile validCsv, inValidCsv;

    @Autowired
    private MockMvc mockMvc;


    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    void Setup() throws IOException {
        validSkodaRed = new ListingDto("1", "Skoda", "Octavia", 86L, 2015L, "red", 20000L);
        validSkodaBlue = new ListingDto("1", "Skoda", "Octavia", 86L, 2015L, "blue", 20000L);

        validCsv = new MockMultipartFile("file", "valid.csv", "text/csv",
                new ClassPathResource("valid.csv").getInputStream());

        inValidCsv = new MockMultipartFile("file", "invalid.csv", "text/csv",
                new ClassPathResource("invalid.csv").getInputStream());
    }

    @Test
    void successful_valid_dealer() throws Exception {

        ListingDto[] listingDtos = {validSkodaRed};
        mockMvc.perform(post("/dealer/vehicle_listings/{dealerId}", validDealerId).content(asJsonString(listingDtos)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void error_invalid_dealer() throws Exception {

        ListingDto[] listingDtos = {validSkodaRed, validSkodaBlue};
        mockMvc.perform(post("/dealer/vehicle_listings/{dealerId}", invalidDealerId).content(asJsonString(listingDtos)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void invalid_body_throw_constraint_exception() throws Exception {
        mockMvc.perform(post("/dealer/vehicle_listings/{dealerId}", validDealerId).content(invalidListingDto).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ConstraintViolationException));
    }

    @Test
    void successful_csv() throws Exception {
        mockMvc.perform(multipart("/dealer/upload_csv/{dealerId}", validDealerId).file(validCsv))
                .andExpect(status().isOk());
    }

    @Test
    void fail_csv() throws Exception {
        mockMvc.perform(multipart("/dealer/upload_csv/{dealerId}", validDealerId).file(inValidCsv))
                .andExpect(status().isBadRequest());
    }

}
