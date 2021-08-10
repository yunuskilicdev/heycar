package com.kilic.yunus.heycar.controller;

import com.kilic.yunus.heycar.dto.ListingDto;
import com.kilic.yunus.heycar.mapper.DealerMapper;
import com.kilic.yunus.heycar.mapper.ListingMapper;
import com.kilic.yunus.heycar.model.Dealer;
import com.kilic.yunus.heycar.model.Listing;
import com.kilic.yunus.heycar.resource.DealerResource;
import com.kilic.yunus.heycar.service.DealerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
 * Yunus Kilic
 */
@Validated
@RestController
@RequestMapping("/dealer")
public class DealerController {
    private final DealerService dealerService;

    public DealerController(DealerService dealerService) {
        this.dealerService = dealerService;
    }

    @PostMapping(value = "/vehicle_listings/{dealerId}")
    public ResponseEntity<DealerResource> addListing(@RequestBody List<@Valid ListingDto> dtoList, @PathVariable Long dealerId) {
        List<Listing> listings = dtoList.stream().map(ListingMapper.INSTANCE::dtoToEntity).toList();
        Dealer dealer = dealerService.addListing(dealerId, listings);
        return new ResponseEntity<>(DealerMapper.INSTANCE.entityToResource(dealer), HttpStatus.OK);
    }

    @PostMapping(value = "/upload_csv/{dealerId}")
    public ResponseEntity<DealerResource> addListingCsv(@RequestParam("file") MultipartFile file, @PathVariable Long dealerId) {
        Dealer dealer = dealerService.addListingCsv(file, dealerId);
        return new ResponseEntity<>(DealerMapper.INSTANCE.entityToResource(dealer), HttpStatus.OK);
    }
}
