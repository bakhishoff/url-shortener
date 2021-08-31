package com.example.urlshortener.service;

import com.example.urlshortener.component.BackgroundChecker;
import com.example.urlshortener.component.UrlChecker;
import com.example.urlshortener.exception.NoDataFoundException;
import com.example.urlshortener.model.dto.UrlDto;
import com.example.urlshortener.model.entity.Url;
import com.example.urlshortener.model.mapper.UrlMapper;
import com.example.urlshortener.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

/**
 * @author Ulphat
 */
@Service
@Transactional
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {

    private final UrlMapper mapper;
    private final UrlRepository repository;
    private final CacheManager cacheManager;
    private final UrlChecker checker;

    @Value("${task.delay}")
    private long delay;

    @PostConstruct
    public void startChecker() {
        new BackgroundChecker(checker, repository, cacheManager, delay).start();
    }

    @Override
    public UrlDto create(UrlDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    @Cacheable(value = "urls", key = "#path")
    public String getByShortLink(String path) {
        return repository.findById(path)
                         .map(Url::getPath)
                         .orElseThrow(NoDataFoundException::new);
    }

}
