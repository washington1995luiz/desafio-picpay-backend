package br.com.washington.desafio_picpay_backend.repositories;

import br.com.washington.desafio_picpay_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByDocument(String document);
}
