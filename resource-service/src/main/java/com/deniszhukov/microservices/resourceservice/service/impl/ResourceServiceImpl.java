package com.deniszhukov.microservices.resourceservice.service.impl;

import com.deniszhukov.microservices.resourceservice.controller.response.ResourceResponse;
import com.deniszhukov.microservices.resourceservice.repository.ResourceRepository;
import com.deniszhukov.microservices.resourceservice.repository.model.Resource;
import com.deniszhukov.microservices.resourceservice.service.ResourceService;
import com.deniszhukov.microservices.resourceservice.service.SongIntegrationService;
import com.deniszhukov.microservices.resourceservice.service.dto.SongMetaData;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository resourceRepository;
    private final SongIntegrationService songIntegrationService;

    public ResourceServiceImpl(ResourceRepository resourceRepository, SongIntegrationService songIntegrationService) {
        this.resourceRepository = resourceRepository;
        this.songIntegrationService = songIntegrationService;
    }

    @Override
    public ResourceResponse uploadResource(MultipartFile mp3File) throws IOException, TikaException, SAXException {
        Resource resource = Resource.builder()
                .mp3File(mp3File.getBytes())
                .build();

        resourceRepository.save(resource);
        log.info("Resource [{}] was loaded", resource.getId());
        loadMetaData(mp3File, resource.getId());
        return ResourceResponse.builder()
                .id(resource.getId())
                .build();

    }

    private void loadMetaData(MultipartFile mp3File, Long resourceId) throws IOException, SAXException, TikaException {
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        ParseContext pcontext = new ParseContext();
        Mp3Parser Mp3Parser = new Mp3Parser();

        try (InputStream inputstream = mp3File.getInputStream()) {
            Mp3Parser.parse(inputstream, handler, metadata, pcontext);
        }

        SongMetaData songMetaData = SongMetaData.builder()
                .name(metadata.get("title"))
                .artist(metadata.get("Author"))
                .album(metadata.get("Album"))
                .year(metadata.get("year"))
                .length(metadata.get("xmpDM:duration"))
                .resourceId(resourceId)
                .build();
        log.info(songMetaData.getResourceId());
        songIntegrationService.saveSongMetaData(songMetaData);
    }

    @Override
    public ResourceResponse getResource(Long id) throws EntityNotFoundException {
        try {
            Resource resource = resourceRepository.getReferenceById(id);
            return ResourceResponse.builder()
                    .audio(resource.getMp3File())
                    .build();
        } catch (EntityNotFoundException e) {
            log.warn("EntityNotFoundException caught: " + e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional
    public Map<String, List<Long>> deleteResourceByIds(List<Long> ids) {
        resourceRepository.deleteByIdIn(ids);
        Map<String, List<Long>> responseIds = new HashMap<>();
        responseIds.put("ids", ids);
        return responseIds;
    }
}
