package com.neo42.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name = "Accounts", description = "Schema that contains Accounts information")
public class AccountsDto {

  @Schema(description = "Account number of the account (10 digits)", example = "1234567890")
  @NotEmpty(message = "Email can not be a null or empty")
  @Pattern(
      regexp = "(^$|[0-9]{10})",
      message = "AccountNumber must be 10 digits") // 10 digit number
  private Long accountNumber;

  @Schema(description = "Account type of the account", example = "Savings")
  @NotEmpty(message = "AccountType can not be a null or empty")
  private String accountType;

  @Schema(
      description = "Branch address that manages the account",
      example = "123 Main Street, New York")
  @NotEmpty(message = "branchAddress can not be a null or empty")
  private String branchAddress;
}
