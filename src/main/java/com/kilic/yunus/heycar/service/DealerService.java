package com.kilic.yunus.heycar.service;

import com.kilic.yunus.heycar.exception.ErrorMessage;
import com.kilic.yunus.heycar.model.Dealer;
import com.kilic.yunus.heycar.model.Listing;
import com.kilic.yunus.heycar.repository.DealerRepository;
import com.kilic.yunus.heycar.repository.ListingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

/**
 * Yunus Kilic
 */
@Service
public class DealerService {

    private final DealerRepository dealerRepository;

    private final ListingRepository listingRepository;

    private final CsvService csvService;

    public DealerService(DealerRepository dealerRepository, ListingRepository listingRepository, CsvService csvService) {
        this.dealerRepository = dealerRepository;
        this.listingRepository = listingRepository;
        this.csvService = csvService;
    }

    public Dealer addListing(Long dealerId, List<Listing> listings) {
        Optional<Dealer> dealerById = dealerRepository.findById(dealerId);
        if (!dealerById.isPresent()) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, ErrorMessage.DEALER_NOT_EXISTED_MESSAGE);
        }
        Dealer dealer = dealerById.get();
        listings.stream().forEach(listing -> {
            Optional<Listing> byCodeAndDealer = listingRepository.findByCodeAndDealer(listing.getCode(), dealer);
            if (byCodeAndDealer.isPresent()) {
                listing.setId(byCodeAndDealer.get().getId());
            }
            listing.setDealer(dealer);
            listingRepository.save(listing);
        });
        return dealer;
    }

    public Dealer addListingCsv(MultipartFile file, Long dealerId) {
        List<Listing> listings = csvService.parseCsvFile(file);
        return addListing(dealerId, listings);
    }

    @PostConstruct
    public void addDealers() {
        dealerRepository.save(new Dealer());
        dealerRepository.save(new Dealer());
    }
}
