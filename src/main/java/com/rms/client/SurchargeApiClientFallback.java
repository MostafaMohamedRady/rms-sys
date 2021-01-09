package com.rms.client;

import com.rms.dto.SurchargeResponse;
import com.rms.util.CommonUtil;
import org.springframework.stereotype.Component;

@Component
public class SurchargeApiClientFallback implements SurchargeApiClient{

    /**
     * handle surcharge api down/fall back
     * @return
     */
    public String getSurcharge(){
        SurchargeResponse surchargeResponse = new SurchargeResponse();
        surchargeResponse.setSurchargeRate(101);
        surchargeResponse.setSurchargeDescription("Hystrix fallback response");
        return CommonUtil.convertObjectToJsonString(surchargeResponse);
    }
}
