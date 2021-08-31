package com.example.urlshortener.model.mapper;

import com.example.urlshortener.model.dto.UrlDto;
import com.example.urlshortener.model.entity.Url;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * @author Ulphat
 */
@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UrlMapper {

    String TABLE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";

    Url toEntity(UrlDto dto);

    @Mapping(target = "path", source = "id")
    UrlDto toDto(Url entity);
}
