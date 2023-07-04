package com.example.links_converter_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
@Table(name = "links")
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Link {

    //TODO add column last select or update entity time

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name =  "long_link")
    private String longLink;

    @Column(name = "short_Link")
    private String shortLink;


    @Column(name = "date_time")
    @CreationTimestamp
    private ZonedDateTime dateTime;

    @Column(name = "last_select")
    private ZonedDateTime lastSelect;

}
