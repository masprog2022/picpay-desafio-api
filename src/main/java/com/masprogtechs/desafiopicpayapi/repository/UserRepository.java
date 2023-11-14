package com.masprogtechs.desafiopicpayapi.repository;

import com.masprogtechs.desafiopicpayapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
