package com.deniszhukov.microservices.songservice.controller;

import com.deniszhukov.microservices.songservice.controller.data.SongData;
import com.deniszhukov.microservices.songservice.controller.response.SongResponse;
import com.deniszhukov.microservices.songservice.service.SongService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Validated
@RestController
@Log4j2
@RequestMapping("/songs")
public class SongController {
    private static final int CSV_RESTRICTION = 200;
    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping
    public ResponseEntity<SongResponse> createSongMeta(@RequestBody SongData songData) {
        SongResponse songResponse = songService.saveSongMeta(songData);
        log.info(songData.getResourceId());

        return new ResponseEntity<>(songResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongResponse> getResource(@PathVariable Long id) {
        SongResponse resourceData = songService.getSong(id);
        return new ResponseEntity<>(resourceData, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Map<String, List<Long>>> deleteResources(@RequestParam("id") List<Long> ids) {
        if (ids.size() > CSV_RESTRICTION) {
            throw new IllegalArgumentException("ID list size should be less than 200");
        }

        Map<String, List<Long>> deletedIds = songService.deleteSongByIds(ids);
        return new ResponseEntity<>(deletedIds, HttpStatus.OK);
    }



}
