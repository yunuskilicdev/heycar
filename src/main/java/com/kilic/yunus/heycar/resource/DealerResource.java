package com.kilic.yunus.heycar.resource;

import lombok.Data;

import java.util.List;

/**
 * Yunus Kilic
 */
@Data
public class DealerResource {

    private long id;
    private List<ListingResource> listings;
}
