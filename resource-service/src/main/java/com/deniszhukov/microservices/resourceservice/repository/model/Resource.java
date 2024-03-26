package com.deniszhukov.microservices.resourceservice.repository.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "resource")
public class Resource {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="resource_seq")
    @SequenceGenerator(name="resource_seq", sequenceName="resource_seq", allocationSize=1)
    private Long id;
    @Column(name = "mp3File", columnDefinition="bytea")
    private byte[] mp3File;
}
