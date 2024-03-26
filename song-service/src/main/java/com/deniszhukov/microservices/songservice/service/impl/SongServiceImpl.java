package com.deniszhukov.microservices.songservice.service.impl;

import com.deniszhukov.microservices.songservice.controller.data.SongData;
import com.deniszhukov.microservices.songservice.controller.response.SongResponse;
import com.deniszhukov.microservices.songservice.handler.MetaDataNotExistException;
import com.deniszhukov.microservices.songservice.repository.SongRepository;
import com.deniszhukov.microservices.songservice.repository.model.Song;
import com.deniszhukov.microservices.songservice.service.SongService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

@Service
@Log4j2
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;

    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public SongResponse saveSongMeta(SongData songData) {
        if (isNull(songData)) {
            throw new MetaDataNotExistException("Song metadata missing validation error");
        }

        Song song = convertSong(songData);
        songRepository.save(song);
        return SongResponse.builder()
                .id(song.getId())
                .build();
    }

    private Song convertSong(SongData songData) {
        Song song = new Song();
        song.setName(song.getName());
        song.setAlbum(songData.getAlbum());
        song.setArtist(songData.getArtist());
        song.setLength(songData.getLength());
        song.setResourceId(songData.getResourceId());
        song.setYear(songData.getYear());
        return song;
    }

    @Override
    public SongResponse getSong(Long id) throws EntityNotFoundException {
        try {
            Song song = songRepository.getReferenceById(id);
            return SongResponse.builder()
                    .artist(song.getArtist())
                    .album(song.getAlbum())
                    .name(song.getName())
                    .year(song.getYear())
                    .resourceId(song.getResourceId())
                    .length(song.getLength())
                    .build();
        } catch (EntityNotFoundException e) {
            log.warn("EntityNotFoundException caught: " + e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional
    public Map<String, List<Long>> deleteSongByIds(List<Long> ids) {
        songRepository.deleteByIdIn(ids);
        Map<String, List<Long>> responseIds = new HashMap<>();
        responseIds.put("ids", ids);
        return responseIds;
    }
}
