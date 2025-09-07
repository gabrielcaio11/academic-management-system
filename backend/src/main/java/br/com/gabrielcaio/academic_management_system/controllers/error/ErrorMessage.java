package br.com.gabrielcaio.academic_management_system.controllers.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
    private Instant timestamp;
    private Integer status;
    private String error;
    private String path;
}


