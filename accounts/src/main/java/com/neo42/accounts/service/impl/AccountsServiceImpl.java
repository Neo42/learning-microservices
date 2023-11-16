package com.neo42.accounts.service.impl;

import com.neo42.accounts.constants.AccountsConstants;
import com.neo42.accounts.dto.CustomerDto;
import com.neo42.accounts.entity.Accounts;
import com.neo42.accounts.entity.Customer;
import com.neo42.accounts.exception.CustomerAlreadyExistsException;
import com.neo42.accounts.mapper.CustomerMapper;
import com.neo42.accounts.repository.AccountsRepository;
import com.neo42.accounts.repository.CustomerRepository;
import com.neo42.accounts.service.IAccountsService;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
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
  public void createAccount(CustomerDto customerDto) {
    Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
    Optional<Customer> optionalCustomer =
        customerRepository.findByMobileNumber(customerDto.getMobileNumber());
    if (optionalCustomer.isPresent()) {
      throw new CustomerAlreadyExistsException(
          "Customer already registered with give mobile number: " + customerDto.getMobileNumber());
    }
    customer.setCreatedAt(LocalDateTime.now());
    customer.setCreatedBy("Anonymous");
    Customer savedCustomer = customerRepository.save(customer);
    accountsRepository.save(createNewAccount(savedCustomer));
  }

  private Accounts createNewAccount(Customer customer) {
    Accounts newAccount = new Accounts();
    newAccount.setCustomerId(customer.getCustomerId());
    Long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

    newAccount.setAccountNumber(randomAccNumber);
    newAccount.setAccountType(AccountsConstants.SAVINGS);
    newAccount.setBranchAddress(AccountsConstants.ADDRESS);
    newAccount.setCreatedAt(LocalDateTime.now());
    newAccount.setCreatedBy("Anonymous");
    return newAccount;
  }
}
