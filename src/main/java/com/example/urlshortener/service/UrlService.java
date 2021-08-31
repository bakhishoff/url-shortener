package com.example.urlshortener.service;

import com.example.urlshortener.model.dto.UrlDto;

/**
 * @author Ulphat
 */
public interface UrlService {
    UrlDto create(UrlDto dto);

    String getByShortLink(String path);
}
