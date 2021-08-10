package com.kilic.yunus.heycar.dto;

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
public class MakeModelDto {

    @NonNull
    private String make;
    @NonNull
    private String model;

    public MakeModelDto(String makeModel) {
        String[] split = makeModel.split("/");
        this.make = split[0];
        this.model = split[1];
    }
}
