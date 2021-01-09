package com.rms.service;

import com.rms.dto.RateRequest;
import com.rms.dto.RateResponse;

public interface RateService {

    RateResponse searchRate();

    void addRate(RateRequest rateRequest);

    void updateRate(RateRequest rateRequest);

    void deleteRate(Long rateId);
}
