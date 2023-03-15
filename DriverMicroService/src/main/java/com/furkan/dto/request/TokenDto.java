package com.furkan.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TokenDto {
    @NotNull
    @Size(min = 8)
    private String token;
}
