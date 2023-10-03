package com.setxpro.rinha.domain.repositories;

import com.setxpro.rinha.domain.entities.Stack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StackRepository extends JpaRepository<Stack, UUID> {
}
