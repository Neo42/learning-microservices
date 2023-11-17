package com.neo42.accounts.repository;

import com.neo42.accounts.entity.Accounts;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {
  Optional<Accounts> findByCustomerId(Long customerId);
}
