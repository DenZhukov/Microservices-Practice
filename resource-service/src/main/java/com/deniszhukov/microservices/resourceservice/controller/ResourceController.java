package com.deniszhukov.microservices.resourceservice.controller;

import com.deniszhukov.microservices.resourceservice.controller.response.ResourceResponse;
import com.deniszhukov.microservices.resourceservice.handler.InvalidFileTypeException;
import com.deniszhukov.microservices.resourceservice.service.ResourceService;
import org.apache.tika.exception.TikaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

@Validated
@RestController
@RequestMapping("/resources")
public class ResourceController {

    private static final String AUDIO = "audio/mpeg";
    private static final int CSV_RESTRICTION = 200;

    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @PostMapping
    public ResponseEntity<ResourceResponse> uploadResource(@RequestBody MultipartFile mp3File) throws IOException, SQLException, TikaException, SAXException {
        if (isNull(mp3File) || isNull(mp3File.getContentType()) || !AUDIO.equals(mp3File.getContentType())) {
            throw new InvalidFileTypeException("Validation failed or request body is invalid MP3");
        }

        ResourceResponse data = resourceService.uploadResource(mp3File);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "audio/mpeg")
    public ResponseEntity<byte[]> getResource(@PathVariable Long id) {
        ResourceResponse resourceData = resourceService.getResource(id);
        return new ResponseEntity<>(resourceData.getAudio(), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Map<String, List<Long>>> deleteResources(@RequestParam("id") List<Long> ids) {
        if (ids.size() > CSV_RESTRICTION) {
            throw new IllegalArgumentException("ID list size should be less than 200");
        }

        Map<String, List<Long>> deletedIds = resourceService.deleteResourceByIds(ids);
        return new ResponseEntity<>(deletedIds, HttpStatus.OK);
    }
}
