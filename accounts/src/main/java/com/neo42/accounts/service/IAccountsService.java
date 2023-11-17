package com.neo42.accounts.service;

import com.neo42.accounts.dto.CustomerDto;

public interface IAccountsService {
  /**
   * @param customerDto - CustomerDto Object
   */
  void createAccount(CustomerDto customerDto);

  /**
   * @param mobileNumber - Input mobile number
   * @return Accounts Details based on the input mobileNumber
   */
  CustomerDto fetchAccount(String mobileNumber);

  /**
   * @param customerDto - customerDto Object
   * @return boolean indicating if the update of Account details is successful or not
   */
  boolean updateAccount(CustomerDto customerDto);
}
