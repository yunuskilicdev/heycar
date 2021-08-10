package com.kilic.yunus.heycar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kilic.yunus.heycar.model.Dealer;
import com.kilic.yunus.heycar.model.Listing;
import com.kilic.yunus.heycar.repository.ListingRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ListingControllerTest {

    private final long validDealerId = 1L;
    @Autowired
    ListingRepository listingRepository;
    @Autowired
    ObjectMapper objectMapper;
    boolean setupCompleted = false;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void Setup() {
        if (setupCompleted) {
            return;
        }
        Dealer dealer = new Dealer();
        dealer.setId(validDealerId);

        Listing first = Listing.builder().code("1").make("Toyota").model("Corolla").dealer(dealer).color("red").kW(100L).price(10000L).year(2017L).build();
        Listing second = Listing.builder().code("2").make("Toyota").model("Corolla").dealer(dealer).color("blue").kW(100L).price(10000L).year(2017L).build();
        Listing third = Listing.builder().code("3").make("Toyota").model("Corolla").dealer(dealer).color("red").kW(100L).price(10000L).year(2018L).build();
        Listing forth = Listing.builder().code("4").make("Toyota").model("Corolla").dealer(dealer).color("blue").kW(100L).price(10000L).year(2018L).build();
        Listing fifth = Listing.builder().code("5").make("Opel").model("Astra").dealer(dealer).color("red").kW(100L).price(10000L).year(2017L).build();

        List<Listing> listings = new ArrayList<>();
        listings.add(first);
        listings.add(second);
        listings.add(third);
        listings.add(forth);
        listings.add(fifth);

        listingRepository.deleteAllByCodeInAndDealer(listings.stream().map(x -> x.getCode()).toList(), dealer);
        listingRepository.saveAll(listings);
        setupCompleted = true;
    }

    @SneakyThrows
    @Test
    public void search_red_cars() {
        mockMvc.perform(get("/listing/search").param("color", "red"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(3)));

    }

    @SneakyThrows
    @Test
    public void search_blue_cars(){
        mockMvc.perform(get("/listing/search").param("color", "blue"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(2)));

    }

    @SneakyThrows
    @Test
    public void search_opel_cars(){
        mockMvc.perform(get("/listing/search").param("make", "Opel"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(1)));

    }

    @SneakyThrows
    @Test
    public void search_corolla_cars(){
        mockMvc.perform(get("/listing/search").param("model", "Corolla"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(4)));

    }

    @SneakyThrows
    @Test
    public void search_twothousandseventeen_cars() {
        mockMvc.perform(get("/listing/search").param("year", "2017"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(3)));

    }
}
