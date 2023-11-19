package com.neo42.loans.repository;

import com.neo42.loans.entity.Loans;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoansRepository extends JpaRepository<Loans, Long> {
  Optional<Loans> findByMobileNumber(String mobileNumber);

  Optional<Loans> findByLoanNumber(String loanNumber);
}
