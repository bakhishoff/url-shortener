package com.example.urlshortener.component;

import com.example.urlshortener.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

/**
 * @author Ulphat
 */
@Slf4j
@RequiredArgsConstructor
public class BackgroundChecker extends Thread {

    private final UrlChecker urlChecker;
    private final UrlRepository repository;
    private final CacheManager cacheManager;
    private final long delay;

    private boolean working = false;

    @SneakyThrows
    public void run() {
        log.info("registered work");
        while (true) {
            Thread.sleep(delay);
            if (working) {
                continue;
            }
            check();
        }
    }

    private void check() {
        working = true;
        log.info("start check");
        repository.findAll()
                  .parallelStream()
                  .filter(url -> !urlChecker.check(url.getPath()))
                  .forEach(entity -> {
                      log.info("found invalid path");
                      repository.delete(entity);
                      Cache cache = cacheManager.getCache("urls");
                      if (cache != null) {
                          log.info("there is cache");
                          if (cache.evictIfPresent(entity.getId())) {
                              log.info("removed invalid path from cache");
                          }
                      }
                  });
        log.info("end check");
        working = false;
    }
}
