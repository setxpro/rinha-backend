package com.setxpro.rinha.domain.repositories;

import com.setxpro.rinha.domain.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {
   // List<Person> findAllPersonsWhereNameLike();

    @Query("SELECT p FROM person p WHERE LOWER(p.nickname) LIKE LOWER(CONCAT('%', :termo, '%')) OR LOWER(p.name) LIKE LOWER(CONCAT('%', :termo, '%')) OR LOWER(:termo) IN (SELECT LOWER(stackElement) FROM p.stack stackElement)")
    List<Person> findByUserParams(@Param("termo") String termo);

    boolean existsByName(String name);

    boolean existsByNickname(String nickname);
}
