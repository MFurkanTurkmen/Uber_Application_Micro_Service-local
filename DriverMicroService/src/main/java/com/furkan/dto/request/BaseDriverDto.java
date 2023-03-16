package com.furkan.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BaseDriverDto {
    private Long id;
    private int exp;
}
