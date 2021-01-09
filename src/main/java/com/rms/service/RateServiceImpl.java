package com.rms.service;

import com.rms.client.SurchargeApiClient;
import com.rms.converter.RateConverter;
import com.rms.dto.RateRequest;
import com.rms.dto.RateResponse;
import com.rms.dto.SurchargeResponse;
import com.rms.entity.RateEntity;
import com.rms.exception.RateNotFoundException;
import com.rms.repository.RateRepository;
import com.rms.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class RateServiceImpl implements RateService {

    private static final Logger log = LoggerFactory.getLogger(RateServiceImpl.class);

    @Autowired
    private SurchargeApiClient surchargeApiClient;

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private RateConverter rateConverter;

    /**
     * call surcharge api to get rate vat
     * then fetch the rate details from db
     * throw exception if no rate found
     * @return
     * @param rateId
     */
    @Override
    public RateResponse searchRate(Long rateId) {
        Optional<RateEntity> optional = Optional.ofNullable(rateRepository.findById(rateId).orElseThrow(RateNotFoundException::new));
        RateEntity entity = optional.get();
        log.info("Rate Db result :- {}", optional);

        String result = surchargeApiClient.getSurcharge();
        log.info("surchargeApiClient response :- {}", result);
        SurchargeResponse surcharge = CommonUtil.convertJsonToObject(result, SurchargeResponse.class);
        return rateConverter.mapRate(surcharge, entity);
    }

    /**
     * add new rate details to db
     * @param rateRequest
     */
    @Override
    public void addRate(RateRequest rateRequest) {
        RateEntity entity = new RateEntity();
        BeanUtils.copyProperties(rateRequest, entity);
        rateRepository.save(entity);
    }

    /**
     * update rate details in db
     * throw rate not found exception if not exist
     * @param rateRequest
     */
    @Override
    public void updateRate(RateRequest rateRequest) {
        Optional<RateEntity> optional = Optional.ofNullable(rateRepository.findById(rateRequest.getRateId()).orElseThrow(RateNotFoundException::new));
        RateEntity entity = optional.get();
        BeanUtils.copyProperties(rateRequest, entity, "rateId");
        rateRepository.save(entity);
    }

    /**
     * delete rate record from db
     * throw rate not found exception if rate not exist
     * @param rateId
     */
    @Override
    public void deleteRate(Long rateId) {
        Optional<RateEntity> optional = Optional.ofNullable(rateRepository.findById(rateId).orElseThrow(RateNotFoundException::new));
        RateEntity entity = optional.get();
        rateRepository.delete(entity);
    }
}
