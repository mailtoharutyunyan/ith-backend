package am.ith.service.error;

import lombok.Data;

@Data
public class ErrorFieldResponseDto {
    private String field;
    private String message;
}
