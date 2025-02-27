package com.app.payload.carDto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BrandDto {

    @JsonProperty("brandName")
    private String brandName;

    public String getBrand() {
        return brandName;
    }

    public void setBrand(String brandName) {
        this.brandName = brandName;
    }
}
