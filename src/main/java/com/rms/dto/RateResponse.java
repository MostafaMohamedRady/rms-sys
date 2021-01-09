package com.rms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@ApiModel(value = "RateResponse", description = "Search api response all the fields of the Rate table and surcharge details in JSON format")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RateResponse {

    private Long rateId;
    private String rateDescription;
    private Date rateEffectiveDate;
    private Date rateExpirationDate;
    private Integer amount;
    private Integer surchargeRate;
    private String surchargeDescription;
    private Integer charges;
}
