package com.neo42.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Customer", description = "Schema that contains Customer and Accounts information")
public class CustomerDto {

  @Schema(description = "Name of the customer", example = "John Doe")
  @NotEmpty(message = "Name can not be a null or empty")
  @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
  private String name;

  @Schema(description = "Email Address of the customer", example = "hi@john.me")
  @NotEmpty(message = "Email can not be a null or empty")
  @Email(message = "Email address should have a valid format")
  private String email;

  @Schema(description = "Mobile number of the customer (10 digits)", example = "0123456789")
  @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
  private String mobileNumber;

  @Schema(description = "Account details of the customer")
  private AccountsDto accountsDto;
}
