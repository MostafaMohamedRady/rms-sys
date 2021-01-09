package com.rms.client;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "SurchargeApiClient", url = "${surcharge.url}", fallback = SurchargeApiClientFallback.class)
public interface SurchargeApiClient {

    @GetMapping("/surcharge")
    @Headers("Content-Type: text/plain")
    String getSurcharge();
}
