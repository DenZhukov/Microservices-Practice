package com.deniszhukov.microservices.resourceservice.service;

import com.deniszhukov.microservices.resourceservice.service.dto.SongMetaData;

public interface SongIntegrationService {
    void saveSongMetaData(SongMetaData songMetaData);
}
