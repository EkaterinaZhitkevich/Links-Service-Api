package com.example.links_converter_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class LongLinkRequestDto {
    @JsonProperty(value = "long_link")
    @NotBlank
    @Size(min = 4, max = 100)
    private String longLink;

}
