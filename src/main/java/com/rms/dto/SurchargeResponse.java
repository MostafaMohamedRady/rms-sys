package com.rms.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(value = "SurchargeResponse", description = "Response from surcharge api")
@Data
public class SurchargeResponse {
    private Integer surchargeRate;
    private String surchargeDescription;
}
