package com.neo42.accounts.controller;

import com.neo42.accounts.constants.AccountsConstants;
import com.neo42.accounts.dto.CustomerDto;
import com.neo42.accounts.dto.ErrorResponseDto;
import com.neo42.accounts.dto.ResponseDto;
import com.neo42.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
    name = "REST API for Accounts in EazyBank",
    description = "REST API in EazyBank to create, fetch, update & delete accounts details")
@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class AccountsController {
  private IAccountsService iAccountsService;

  @Operation(
      summary = "Create a bank account",
      description =
          "Create a bank account with name, email, mobile number & account related information")
  @ApiResponse(responseCode = "201", description = "HTTP response status: CREATED")
  @PostMapping("/create")
  public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
    iAccountsService.createAccount(customerDto);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_2O1));
  }

  @Operation(
      summary = "Fetch details for an bank account",
      description = "Fetch Customer & Account details based on a mobile number")
  @ApiResponse(responseCode = "200", description = "HTTP response status: OK")
  @GetMapping("/fetch")
  public ResponseEntity<CustomerDto> fetchAccountDetails(
      @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
          String mobileNumber) {
    CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);
    return ResponseEntity.status(HttpStatus.OK).body(customerDto);
  }

  @Operation(
      summary = "Update for an bank account",
      description = "Update Customer & Account details based on an account number")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "HTTP response status: OK"),
    @ApiResponse(
        responseCode = "500",
        description = "HTTP response status: Internal Server Error",
        content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
  })
  @PutMapping("/update")
  public ResponseEntity<ResponseDto> updateAccountDetails(
      @Valid @RequestBody CustomerDto customerDto) {
    boolean isUpdated = iAccountsService.updateAccount(customerDto);
    if (!isUpdated) {
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
          .body(
              new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
    } else {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
    }
  }

  @Operation(
      summary = "Delete an bank account",
      description = "Delete Customer & Account details based on a mobile number")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "HTTP response status: OK"),
    @ApiResponse(
        responseCode = "500",
        description = "HTTP response status: Internal Server Error",
        content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
  })
  @DeleteMapping("/delete")
  public ResponseEntity<ResponseDto> deleteAccountDetails(
      @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
          String mobileNumber) {
    boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
    if (!isDeleted) {
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
          .body(
              new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
    } else {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
    }
  }
}
