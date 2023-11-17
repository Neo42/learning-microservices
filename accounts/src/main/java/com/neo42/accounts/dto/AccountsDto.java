package com.neo42.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {

  @NotEmpty(message = "Email can not be a null or empty")
  @Pattern(
      regexp = "(^$|[0-9]{10})",
      message = "AccountNumber must be 10 digits") // 10 digit number
  private Long accountNumber;

  @NotEmpty(message = "AccountType can not be a null or empty")
  private String accountType;

  @NotEmpty(message = "branchAddress can not be a null or empty")
  private String branchAddress;
}
