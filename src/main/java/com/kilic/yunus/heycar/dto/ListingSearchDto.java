package com.kilic.yunus.heycar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Yunus Kilic
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListingSearchDto {
    private String make;
    private String model;
    private long year;
    private String color;
    private Integer offset;
    private Integer limit;
}
