package com.neo42.accounts.service;

import com.neo42.accounts.dto.CustomerDto;

public interface IAccountsService {
  /**
   * @param customerDto - CustomerDto Object
   */
  void createAccount(CustomerDto customerDto);
}
