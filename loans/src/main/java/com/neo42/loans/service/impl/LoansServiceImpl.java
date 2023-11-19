package com.neo42.loans.service.impl;

import com.neo42.loans.constants.LoansConstants;
import com.neo42.loans.dto.LoansDto;
import com.neo42.loans.entity.Loans;
import com.neo42.loans.exception.LoanAlreadyExistsException;
import com.neo42.loans.exception.ResourceNotFoundException;
import com.neo42.loans.mapper.LoansMapper;
import com.neo42.loans.repository.LoansRepository;
import com.neo42.loans.service.ILoansService;
import java.util.Optional;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {
  private LoansRepository loansRepository;

  /**
   * @param mobileNumber - Mobile Number of the customer
   */
  @Override
  public void createLoan(String mobileNumber) {
    Optional<Loans> optionalLoans = loansRepository.findByMobileNumber(mobileNumber);
    if (optionalLoans.isPresent()) {
      throw new LoanAlreadyExistsException(
          "Loan already registered with give mobile number: " + mobileNumber);
    }
    loansRepository.save(createNewLoan(mobileNumber));
  }

  /**
   * @param mobileNumber - Mobile Number of the customer
   * @return the new loan details
   */
  private Loans createNewLoan(String mobileNumber) {
    Loans newLoan = new Loans();
    long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
    newLoan.setLoanNumber(Long.toString(randomLoanNumber));
    newLoan.setMobileNumber(mobileNumber);
    newLoan.setLoanType(LoansConstants.HOME_LOAN);
    newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
    newLoan.setAmountPaid(0);
    newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
    return newLoan;
  }

  /**
   * @param mobileNumber - Mobile number of the customer
   * @return Loan Details based on a given mobile number
   */
  @Override
  public LoansDto fetchLoan(String mobileNumber) {
    Loans loans =
        loansRepository
            .findByMobileNumber(mobileNumber)
            .orElseThrow(
                () -> new ResourceNotFoundException("Loans", "mobileNumber", mobileNumber));
    LoansDto loansDto = LoansMapper.mapToLoansDto(loans, new LoansDto());
    return loansDto;
  }

  /**
   * @param loansDto - LoansDTO object
   * @return boolean indicating if the update of the loan details is successful or not
   */
  @Override
  public boolean updateLoan(LoansDto loansDto) {
    Loans loans =
        loansRepository
            .findByLoanNumber(loansDto.getLoanNumber())
            .orElseThrow(
                () ->
                    new ResourceNotFoundException("Loan", "LoanNumber", loansDto.getLoanNumber()));
    LoansMapper.mapToLoans(loansDto, loans);
    loansRepository.save(loans);
    return true;
  }

  /**
   * @param mobileNumber - Mobile number of the customer
   * @return boolean indicating if the deletion of the loan details is successful or not
   */
  @Override
  public boolean deleteLoan(String mobileNumber) {
    Loans loans =
        loansRepository
            .findByMobileNumber(mobileNumber)
            .orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber));
    loansRepository.deleteById(loans.getLoanId());
    return true;
  }
}
