# RMS-SYS

Rate Management system(RMS) in Logistic domain.

## Tools


```bash
- spring boot 2.3.2.RELEASE
- MYSQL5.7 
- Feign client & hystrix
- junit & mockito
- spring secuirty
```
## APP Config
```
swagger-url = http://localhost:8080/swagger-ui.html
username = rmsuser
password = rmspwd
```
## DB

```
DB-name = rms-db
DB-username = root
DB-password = root
----------------
--script
-- Rate table
CREATE TABLE `rate` (
  `rate_id` bigint(20) NOT NULL,
  `amount` int(11) NOT NULL,
  `rate_description` varchar(255) DEFAULT NULL,
  `rate_effective_date` datetime NOT NULL,
  `rate_expiration_date` datetime NOT NULL,
  PRIMARY KEY (`rate_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
```