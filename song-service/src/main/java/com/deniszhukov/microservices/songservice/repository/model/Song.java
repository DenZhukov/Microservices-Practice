package com.deniszhukov.microservices.songservice.repository.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Song {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="song_seq")
    @SequenceGenerator(name="song_seq", sequenceName="song_seq", allocationSize=1)
    private Long id;
    private String name;
    private String artist;
    private String album;
    private String length;
    private Long resourceId;
    private Integer year;
}
