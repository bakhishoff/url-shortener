package com.example.urlshortener.repository;

import com.example.urlshortener.model.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ulphat
 */
@Repository
public interface UrlRepository extends JpaRepository<Url, String> {
}
