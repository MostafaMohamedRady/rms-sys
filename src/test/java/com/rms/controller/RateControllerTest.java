package com.rms.controller;

import com.rms.dto.RateRequest;
import com.rms.dto.RateResponse;
import com.rms.exception.RateNotFoundException;
import com.rms.service.RateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RateControllerTest {

    @InjectMocks
    private RateController rateController;

    @Mock
    private RateService rateService;

    private MockMvc mvc;

    @Test
    public void searchRate() {
        Mockito.when(rateService.searchRate(Mockito.anyLong())).thenReturn(mockRateReponse());
        ResponseEntity<Object> responseEntity = rateController.searchRate(100L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test(expected = RateNotFoundException.class)
    public void searchRate_whenException() {
        Mockito.when(rateService.searchRate(Mockito.anyLong())).thenThrow(RateNotFoundException.class);
        rateController.searchRate(100L);
    }

    @Test
    public void addRate() {
        RateRequest rateRequest = new RateRequest();
        rateRequest.setAmount(100);
        rateRequest.setRateDescription("desc");
        rateRequest.setRateEffectiveDate(new Date());
        rateRequest.setRateExpirationDate(new Date());
        rateRequest.setRateId(100L);
        ResponseEntity<Object> responseEntity = rateController.addRate(rateRequest);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Successfully store rate details in DB.", responseEntity.getBody());
    }

    @Test
    public void testUpdateRate() {
        RateRequest rateRequest = new RateRequest();
        rateRequest.setAmount(100);
        rateRequest.setRateDescription("desc");
        rateRequest.setRateEffectiveDate(new Date());
        rateRequest.setRateExpirationDate(new Date());
        rateRequest.setRateId(100L);
        ResponseEntity<Object> responseEntity = rateController.updateRate(rateRequest);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Successfully update rate details in DB.", responseEntity.getBody());
    }

    @Test
    public void deleteRate() {
        ResponseEntity<Object> responseEntity = rateController.deleteRate(100L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    private RateResponse mockRateReponse() {
        RateResponse rateResponse = new RateResponse();
        rateResponse.setAmount(1);
        rateResponse.setRateDescription("setRateDescription");
        rateResponse.setRateEffectiveDate(new Date());
        rateResponse.setRateExpirationDate(new Date());
        rateResponse.setRateId(100L);
        rateResponse.setSurchargeDescription("setSurchargeDescription");
        rateResponse.setSurchargeRate(100L);
        return rateResponse;
    }
}