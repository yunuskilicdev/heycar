package com.kilic.yunus.heycar.mapper;

import com.kilic.yunus.heycar.dto.ListingCsvDto;
import com.kilic.yunus.heycar.dto.ListingDto;
import com.kilic.yunus.heycar.model.Listing;
import com.kilic.yunus.heycar.resource.ListingResource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * Yunus Kilic
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ListingMapper {
    ListingMapper INSTANCE = Mappers.getMapper(ListingMapper.class);

    Listing dtoToEntity(ListingDto dto);

    ListingResource entityToResource(Listing listing);

    @Mapping(source = "makeModel.make", target = "make")
    @Mapping(source = "makeModel.model", target = "model")
    Listing csvToListing(ListingCsvDto dto);
}
