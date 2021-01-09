package com.rms.repository;

import com.rms.entity.RateEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RateRepositoryTest {

    @Autowired
    private RateRepository rateRepository;

    @Test
    public void testSaveRate() {
        RateEntity entity = new RateEntity();
        entity.setRateId(100L);
        RateEntity save = rateRepository.save(entity);
        Assert.assertNotNull(save);
    }

    @Test
    public void testFindRate() {
        RateEntity entity = new RateEntity();
        entity.setRateId(200L);
        RateEntity save = rateRepository.save(entity);
        Optional<RateEntity> byId = rateRepository.findById(200L);
        Assert.assertNotNull(byId);
    }

    @Test
    public void testDeleteRate() {
        RateEntity entity = new RateEntity();
        entity.setRateId(200L);
        RateEntity save = rateRepository.save(entity);
        rateRepository.deleteById(200L);
        Assert.assertTrue(true);
    }
}