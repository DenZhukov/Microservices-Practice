package com.deniszhukov.microservices.resourceservice.service;

import com.deniszhukov.microservices.resourceservice.controller.response.ResourceResponse;
import org.apache.tika.exception.TikaException;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ResourceService {
    ResourceResponse uploadResource(MultipartFile mp3File) throws IOException, SQLException, TikaException, SAXException;

    ResourceResponse getResource(Long id);

    Map<String, List<Long>> deleteResourceByIds(List<Long> ids);
}
