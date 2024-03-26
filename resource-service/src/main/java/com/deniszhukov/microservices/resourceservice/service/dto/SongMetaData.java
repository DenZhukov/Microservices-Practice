package com.deniszhukov.microservices.resourceservice.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SongMetaData {
    private String name;
    private String artist;
    private String album;
    private String length;
    private Long resourceId;
    private String year;
}
