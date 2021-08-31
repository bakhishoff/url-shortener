package com.example.urlshortener.model.dto;

import com.example.urlshortener.annotation.Url;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ulphat
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrlDto {

    @Url
    private String path;
}
