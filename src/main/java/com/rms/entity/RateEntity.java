package com.rms.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "RATE")
public class RateEntity {

    @Id
    private Long rateId;
    @Column(name = "RATE_DESCRIPTION")
    private String rateDescription;
    @Column(name = "RATE_EFFECTIVE_DATE", nullable = false)
    private Date rateEffectiveDate;
    @Column(name = "RATE_EXPIRATION_DATE", nullable = false)
    private Date rateExpirationDate;
    @Column(name = "AMOUNT", nullable = false)
    private Integer amount;
}
