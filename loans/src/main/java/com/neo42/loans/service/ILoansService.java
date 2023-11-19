package com.neo42.loans.service;

import com.neo42.loans.dto.LoansDto;

public interface ILoansService {

  /**
   * @param mobileNumber - Mobile Number of the customer
   */
  void createLoan(String mobileNumber);

  /**
   * @param mobileNumber - Mobile number of the customer
   * @return Loan Details based on a given mobile number
   */
  LoansDto fetchLoan(String mobileNumber);

  /**
   * @param loansDto - LoansDTO object
   * @return boolean indicating if the update of the loan details is successful or not
   */
  boolean updateLoan(LoansDto loansDto);

  /**
   * @param mobileNumber - Mobile number of the customer
   * @return boolean indicating if the deletion of the loan details is successful or not
   */
  boolean deleteLoan(String mobileNumber);
}
