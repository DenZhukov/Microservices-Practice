package com.deniszhukov.microservices.songservice.controller.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SongData {
    private Long id;
    private String name;
    private String artist;
    private String album;
    private String length;
    private Long resourceId;
    private Integer year;
}
