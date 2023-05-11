package com.tutorial.crudspringbootmongoback.global.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private HttpStatus status;
    private String message;
}
