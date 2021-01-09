package com.rms.service;

import com.rms.client.SurchargeApiClient;
import com.rms.converter.RateConverter;
import com.rms.dto.RateRequest;
import com.rms.dto.RateResponse;
import com.rms.entity.RateEntity;
import com.rms.exception.RateNotFoundException;
import com.rms.repository.RateRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RateServiceImplTest {

    @InjectMocks
    private RateServiceImpl service;

    @Mock
    private SurchargeApiClient surchargeApiClient;

    @Mock
    private RateRepository rateRepository;

    @Spy
    private RateConverter rateConverter;

    @Test
    public void searchRate() {
        String result = "{\n" +
                "\"surchargeRate\": 100,\n" +
                "\"surchargeDescription\": \"VAT\"\n" +
                "}";
        Mockito.when(surchargeApiClient.getSurcharge()).thenReturn(result);
        Mockito.when(rateRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockRateEntity()));
        RateResponse rateResponse = service.searchRate(100L);
        Assert.assertNotNull(rateResponse);
        Assert.assertEquals(rateResponse.getSurchargeDescription(), "VAT");
    }

    @Test(expected = RateNotFoundException.class)
    public void searchRate_whenNoRateFoundInDB() {
        Mockito.when(rateRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        RateResponse rateResponse = service.searchRate(100L);
    }

    @Test
    public void addRate() {
        RateRequest rateRequest = new RateRequest();
        rateRequest.setAmount(100);
        rateRequest.setRateDescription("desc");
        rateRequest.setRateEffectiveDate(new Date());
        rateRequest.setRateExpirationDate(new Date());
        rateRequest.setRateId(100L);
        service.addRate(rateRequest);
        verify(rateRepository, times(1)).save(Mockito.isA(RateEntity.class));
    }

    @Test
    public void updateRate() {
        Mockito.when(rateRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockRateEntity()));
        RateRequest rateRequest = new RateRequest();
        rateRequest.setAmount(100);
        rateRequest.setRateDescription("desc");
        rateRequest.setRateEffectiveDate(new Date());
        rateRequest.setRateExpirationDate(new Date());
        rateRequest.setRateId(100L);
        service.updateRate(rateRequest);
        verify(rateRepository, times(1)).save(Mockito.isA(RateEntity.class));
    }

    @Test(expected = RateNotFoundException.class)
    public void updateRate_whenRateNotFoundDB() {
        Mockito.when(rateRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        RateRequest rateRequest = new RateRequest();
        rateRequest.setAmount(100);
        rateRequest.setRateDescription("desc");
        rateRequest.setRateEffectiveDate(new Date());
        rateRequest.setRateExpirationDate(new Date());
        rateRequest.setRateId(100L);
        service.updateRate(rateRequest);
        verify(rateRepository, times(0)).save(Mockito.isA(RateEntity.class));
    }

    @Test
    public void deleteRate() {
        Mockito.when(rateRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockRateEntity()));
        service.deleteRate(100L);
        verify(rateRepository, times(1)).delete(Mockito.isA(RateEntity.class));
    }

    @Test(expected = RateNotFoundException.class)
    public void deleteRate_whenRateNotFoundInDb() {
        Mockito.when(rateRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        service.deleteRate(100L);
        verify(rateRepository, times(0)).delete(Mockito.isA(RateEntity.class));
    }

    private RateEntity mockRateEntity() {
        RateEntity entity = new RateEntity();
        entity.setRateId(100L);
        entity.setAmount(200);
        entity.setRateEffectiveDate(new Date());
        entity.setRateExpirationDate(new Date());
        return entity;
    }
}