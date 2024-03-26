package com.deniszhukov.microservices.songservice.repository;

import com.deniszhukov.microservices.songservice.repository.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository <Song, Long> {
    void deleteByIdIn(List<Long> ids);
}
