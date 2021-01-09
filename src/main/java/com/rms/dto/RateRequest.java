package com.rms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel(value = "RateRequest", description = "Object containing rate details")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RateRequest {

    @ApiModelProperty(value = "primary key for rate details", required = true)
    @NotNull(message = "RateId is required")
    private Long rateId;

    @ApiModelProperty(value = "Rate Description")
    private String rateDescription;

    @ApiModelProperty(value = "Rate effective date", required = true)
    @NotNull(message = "Rate effective date is required")
    private Date rateEffectiveDate;

    @ApiModelProperty(value = "Rate expiration date", required = true)
    @NotNull(message = "Rate expiration date is required")
    private Date rateExpirationDate;

    @ApiModelProperty(value = "Rate amount", required = true)
    @NotNull(message = "amount is required")
    private Integer amount;
}
