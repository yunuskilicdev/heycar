package com.kilic.yunus.heycar.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Yunus Kilic
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"code", "make/model", "power-in-ps", "year", "color", "price"})
public class ListingCsvDto {

    @NonNull
    private String code;
    @NonNull
    @JsonProperty(value = "make/model")
    private MakeModelDto makeModel;
    @NonNull
    @JsonProperty(value = "power-in-ps")
    private Long kW;
    @NonNull
    private Long year;
    @NonNull
    private String color;
    @NonNull
    private Long price;
}