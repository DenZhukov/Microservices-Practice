package com.deniszhukov.microservices.resourceservice.service.impl;

import com.deniszhukov.microservices.resourceservice.service.SongIntegrationService;
import com.deniszhukov.microservices.resourceservice.service.dto.SongMetaData;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Log4j2
public class SongIntegrationServiceImpl implements SongIntegrationService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void saveSongMetaData(SongMetaData songMetaData) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<SongMetaData> entity = new HttpEntity<>(songMetaData, headers);
        ResponseEntity<String> response = restTemplate.exchange("http://song-service/songs", HttpMethod.POST, entity, String.class);
        log.info(response.getBody());
    }
}
