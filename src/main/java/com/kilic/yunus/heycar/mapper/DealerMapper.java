package com.kilic.yunus.heycar.mapper;

import com.kilic.yunus.heycar.model.Dealer;
import com.kilic.yunus.heycar.resource.DealerResource;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * Yunus Kilic
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DealerMapper {
    DealerMapper INSTANCE = Mappers.getMapper(DealerMapper.class);

    DealerResource entityToResource(Dealer dealer);
}
