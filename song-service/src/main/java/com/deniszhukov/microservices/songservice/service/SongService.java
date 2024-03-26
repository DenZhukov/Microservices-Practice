package com.deniszhukov.microservices.songservice.service;

import com.deniszhukov.microservices.songservice.controller.data.SongData;
import com.deniszhukov.microservices.songservice.controller.response.SongResponse;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Map;

public interface SongService {
    SongResponse saveSongMeta (SongData songData);
    SongResponse getSong(Long id) throws EntityNotFoundException;
    Map<String, List<Long>> deleteSongByIds(List<Long> ids);
}
