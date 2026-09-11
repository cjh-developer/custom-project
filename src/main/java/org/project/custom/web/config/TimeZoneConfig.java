package org.project.custom.web.config;

import jakarta.annotation.PostConstruct;
import org.project.custom.common.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.time.ZoneId;
import java.util.TimeZone;

@Configuration
public class TimeZoneConfig {

    private static final Logger logger = LoggerFactory.getLogger(TimeZoneConfig.class);

    @Value("${custom.server.time.zone:Asia/Seoul}")
    private String timeZone;

    @PostConstruct
    public void init(){
        ZoneId zoneId = ZoneId.of(timeZone);
        TimeZone.setDefault(TimeZone.getTimeZone(zoneId));
        DateUtil.setClock(Clock.system(zoneId));
        logger.info("[PROJECT_LOG] timeZoneConfig 서버 기준 시간 : {}", zoneId);
    }
}
