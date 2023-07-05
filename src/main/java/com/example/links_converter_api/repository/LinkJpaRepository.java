package com.example.links_converter_api.repository;

import com.example.links_converter_api.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LinkJpaRepository extends JpaRepository<Link, Long> {
    Link findByLongLink(String longLink);
    Link findByShortLink(String shortLink);
}
