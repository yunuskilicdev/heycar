package com.kilic.yunus.heycar.service;

import com.kilic.yunus.heycar.model.Dealer;
import com.kilic.yunus.heycar.model.Listing;
import com.kilic.yunus.heycar.repository.DealerRepository;
import com.kilic.yunus.heycar.repository.ListingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DealerServiceTest {

    private final long validDealerId = 1L, invalidDealerId = 9999L;

    private Dealer dealer;

    private List<Listing> listings;

    private Listing listing;

    @Mock
    private DealerRepository dealerRepository;

    @Mock
    private ListingRepository listingRepository;

    @InjectMocks
    private DealerService dealerService;

    @BeforeEach
    void Setup() {
        dealer = new Dealer();
        dealer.setId(1L);

        listings = new ArrayList<>();

        listing = spy(new Listing());
        listing.setCode("1");
        listing.setMake("Toyota");
        listing.setModel("Corolla");
        listing.setKW(85L);
        listing.setColor("red");
        listing.setPrice(20000L);
        listing.setYear(2015L);
        listings.add(listing);


        dealer.setListings(new ArrayList<>());
        when(dealerRepository.findById(1L)).thenReturn(Optional.of(dealer));
    }

    @Test()
    public void withInvalidDealer_shouldThrowError() {
        assertThrows(HttpClientErrorException.class, () -> dealerService.addListing(invalidDealerId, new ArrayList<>()));
    }

    @Test()
    public void create_listing() {
        Dealer dealerActual = dealerService.addListing(validDealerId, listings);
        assertEquals(dealer, dealerActual);
        verify(listingRepository).save(eq(listing));
    }

    @Test()
    public void update_listing() {
        when(listingRepository.findByCodeAndDealer(listings.get(0).getCode(), dealer)).thenReturn(Optional.of(listing));
        Dealer dealerActual = dealerService.addListing(validDealerId, listings);
        assertEquals(dealer, dealerActual);
        verify(listing).setId(anyLong());
    }
}
