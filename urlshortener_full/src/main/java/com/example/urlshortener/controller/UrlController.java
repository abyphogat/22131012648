package com.example.urlshortener.controller;

import com.example.urlshortener.dto.UrlRequestDto;
import com.example.urlshortener.dto.UrlResponseDto;
import com.example.urlshortener.service.UrlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @PostMapping("/shorturls")
    public ResponseEntity<UrlResponseDto> create(@Valid @RequestBody UrlRequestDto request) {
        UrlResponseDto response = urlService.createShortUrl(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{shortcode}")
    public ResponseEntity<?> redirect(@PathVariable String shortcode) {
        String target = urlService.redirectToUrl(shortcode);
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(target)).build();
    }
}