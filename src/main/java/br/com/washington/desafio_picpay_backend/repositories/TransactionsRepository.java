package br.com.washington.desafio_picpay_backend.repositories;

import br.com.washington.desafio_picpay_backend.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepository extends JpaRepository<Transactions, Long> {
}
