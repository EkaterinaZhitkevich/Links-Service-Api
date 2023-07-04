package com.example.links_converter_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class ShortLinkResponseDto {
    @JsonProperty(value = "short_link")
    @NotBlank
    @Size(min = 3, max = 10)
    private String shortLink;
}
