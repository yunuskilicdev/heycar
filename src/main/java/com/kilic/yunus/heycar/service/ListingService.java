package com.kilic.yunus.heycar.service;

import com.kilic.yunus.heycar.dto.ListingSearchDto;
import com.kilic.yunus.heycar.model.Listing;
import com.kilic.yunus.heycar.repository.ListingRepository;
import com.kilic.yunus.heycar.specification.ListingSpecification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Yunus Kilic
 */
@Service
public class ListingService {

    private final ListingRepository listingRepository;
    @Value("${default.offset}")
    Integer defaultOffset;
    @Value("${default.limit}")
    Integer defaultLimit;

    public ListingService(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }

    public Page<Listing> search(ListingSearchDto listingSearchDto) {
        Specification<Listing> specification = ListingSpecification.search(listingSearchDto);
        Integer offset = Optional.ofNullable(listingSearchDto.getOffset()).orElse(defaultOffset);
        Integer limit = Optional.ofNullable(listingSearchDto.getLimit()).orElse(defaultLimit);
        return listingRepository.findAll(specification, PageRequest.of(offset, limit));
    }
}
