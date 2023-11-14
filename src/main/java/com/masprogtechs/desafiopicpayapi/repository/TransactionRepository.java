package com.masprogtechs.desafiopicpayapi.repository;

import com.masprogtechs.desafiopicpayapi.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
