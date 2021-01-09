package com.rms.service;

import com.rms.dto.RateRequest;
import com.rms.dto.RateResponse;

public interface RateService {

    RateResponse searchRate(Long rateId);

    void addRate(RateRequest rateRequest);

    void updateRate(RateRequest rateRequest);

    void deleteRate(Long rateId);
}
