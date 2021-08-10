package com.kilic.yunus.heycar.controller;

import com.kilic.yunus.heycar.dto.ListingSearchDto;
import com.kilic.yunus.heycar.mapper.ListingMapper;
import com.kilic.yunus.heycar.model.Listing;
import com.kilic.yunus.heycar.resource.ListingResource;
import com.kilic.yunus.heycar.service.ListingService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Yunus Kilic
 */
@RestController
@RequestMapping("/listing")
public class ListingController {

    private final ListingService listingService;

    public ListingController(ListingService listingService) {
        this.listingService = listingService;
    }

    @GetMapping(value = "/search")
    public Page<ListingResource> test(ListingSearchDto listingSearchDto) {
        Page<Listing> search = listingService.search(listingSearchDto);
        return search.map(ListingMapper.INSTANCE::entityToResource);
    }
}
