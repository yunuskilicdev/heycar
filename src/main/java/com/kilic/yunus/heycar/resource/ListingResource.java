package com.kilic.yunus.heycar.resource;

import lombok.Builder;
import lombok.Data;

/**
 * Yunus Kilic
 */
@Data
@Builder
public class ListingResource {

    private long id;
    private String code;
    private String make;
    private String model;
    private long kW;
    private long year;
    private String color;
    private long price;
}
