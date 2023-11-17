package com.neo42.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(name = "Response", description = "Schema that contains successful response information")
@Data
@AllArgsConstructor
public class ResponseDto {
  @Schema(description = "HTTP response status code", example = "200")
  private String statusCode;

  @Schema(description = "HTTP response message", example = "Request processed successfully")
  private String statusMsg;
}
