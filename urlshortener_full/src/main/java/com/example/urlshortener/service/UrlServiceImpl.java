package com.example.urlshortener.service;

import com.example.urlshortener.dto.UrlRequestDto;
import com.example.urlshortener.dto.UrlResponseDto;
import com.example.urlshortener.entity.UrlMapping;
import com.example.urlshortener.middleware.LoggerService;
import com.example.urlshortener.repository.UrlMappingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {

    private final UrlMappingRepository repository;
    private final LoggerService loggerService;

    @Override
    public UrlResponseDto createShortUrl(UrlRequestDto dto) {
        String shortcode = dto.getShortcode() != null ? dto.getShortcode() : UUID.randomUUID().toString().substring(0, 6);
        if (repository.existsByShortCode(shortcode)) {
            loggerService.log("backend", "error", "handler", "Shortcode already exists");
            throw new RuntimeException("Shortcode already exists");
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiry = now.plusMinutes(dto.getValidity() != null ? dto.getValidity() : 30);

        UrlMapping url = UrlMapping.builder()
                .shortCode(shortcode)
                .originalUrl(dto.getUrl())
                .createdAt(now)
                .expiryAt(expiry)
                .build();
        repository.save(url);

        return UrlResponseDto.builder()
                .shortLink("http://localhost:8080/" + shortcode)
                .expiry(expiry.toString())
                .build();
    }

    @Override
    public String redirectToUrl(String shortcode) {
        UrlMapping url = repository.findByShortCode(shortcode)
                .orElseThrow(() -> new RuntimeException("Shortcode not found"));

        if (LocalDateTime.now().isAfter(url.getExpiryAt())) {
            throw new RuntimeException("Link expired");
        }

        url.setClickCount(url.getClickCount() + 1);
        repository.save(url);

        return url.getOriginalUrl();
    }
}