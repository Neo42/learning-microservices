package com.neo42.accounts.service.impl;

import com.neo42.accounts.dto.CustomerDto;
import com.neo42.accounts.repository.AccountsRepository;
import com.neo42.accounts.repository.CustomerRepository;
import com.neo42.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {
  private AccountsRepository accountsRepository;
  private CustomerRepository customerRepository;

  /**
   * @param customerDto - CustomerDto Object
   */
  @Override
  public void createAccount(CustomerDto customerDto) {}
}
