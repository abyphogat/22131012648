package com.example.urlshortener.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UrlResponseDto {
    private String shortLink;
    private String expiry;
}