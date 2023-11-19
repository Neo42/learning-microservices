package com.neo42.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Schema(name = "ErrorResponse", description = "Schema that contains error response information")
@Data
@AllArgsConstructor
public class ErrorResponseDto {
  @Schema(description = "API path invoked by client", example = "uri=/api/fetch")
  private String apiPath;

  @Schema(description = "HTTP response error code", example = "INTERNAL_SERVER_ERROR")
  private HttpStatus errorCode;

  @Schema(
      description = "HTTP response error message",
      example = "Loan not found with the give input mobile number: 0123456789")
  private String errorMessage;

  @Schema(
      description = "Timestamp responding when HTTP response error happened",
      example = "2023-11-18T01:21:07.116662")
  private LocalDateTime errorTime;
}
