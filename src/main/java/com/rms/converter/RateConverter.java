package com.rms.converter;

import com.rms.dto.RateResponse;
import com.rms.dto.SurchargeResponse;
import com.rms.entity.RateEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class RateConverter {

    /**
     * map results from surcharge api and rate table to rate dto
     * @param surcharge
     * @param entity
     * @return
     */
    public RateResponse mapRate(SurchargeResponse surcharge, RateEntity entity) {
        RateResponse rateResponse = new RateResponse();
        BeanUtils.copyProperties(entity, rateResponse);
        BeanUtils.copyProperties(surcharge, rateResponse);
        return rateResponse;
    }
}
