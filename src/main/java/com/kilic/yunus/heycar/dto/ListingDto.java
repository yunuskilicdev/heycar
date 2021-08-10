package com.kilic.yunus.heycar.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * Yunus Kilic
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListingDto {
    @NotEmpty(message = "Code cannot be empty.")
    private String code;
    @NotEmpty(message = "Code cannot be empty.")
    private String make;
    @NotEmpty(message = "Code cannot be empty.")
    private String model;
    @Min(1L)
    private Long kW;
    @Min(2000L)
    private Long year;
    @NotEmpty(message = "Code cannot be empty.")
    private String color;
    @Min(2000L)
    private Long price;
}
