package com.example.urlshortener.controller;

import com.example.urlshortener.annotation.Url;
import com.example.urlshortener.model.dto.UrlDto;
import com.example.urlshortener.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

/**
 * @author Ulphat
 */
@RestController
@RequiredArgsConstructor
public class UrlController {

    private final UrlService service;

    @PostMapping
    public UrlDto create(@RequestBody @Valid UrlDto dto) {
        return service.create(dto);
    }

    @GetMapping("/{path}")
    public void redirect(@PathVariable @Valid @Url String path, HttpServletResponse response) throws IOException {
        response.sendRedirect(service.getByShortLink(path));
    }
}
