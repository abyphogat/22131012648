package com.example.urlshortener.service;

import com.example.urlshortener.dto.UrlRequestDto;
import com.example.urlshortener.dto.UrlResponseDto;

public interface UrlService {
    UrlResponseDto createShortUrl(UrlRequestDto dto);
    String redirectToUrl(String shortcode);
}