package com.example.urlshortener.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UrlRequestDto {

    @NotBlank
    private String url;

    private Integer validity; // in minutes

    @Pattern(regexp = "^[a-zA-Z0-9]{1,20}$", message = "Shortcode must be alphanumeric and up to 20 chars")
    private String shortcode;
}