package com.deniszhukov.microservices.resourceservice.repository;

import com.deniszhukov.microservices.resourceservice.repository.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
    void deleteByIdIn(List<Long> ids);
}
