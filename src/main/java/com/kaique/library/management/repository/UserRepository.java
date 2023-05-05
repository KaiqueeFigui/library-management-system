package com.kaique.library.management.repository;

import com.kaique.library.management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    @Query("select (count(u) > 0) from User u where u.role.description = :description")
    boolean existsByRoleDescription(@Param("description") String description);

    Optional<User> findByEmailAndPassword(String email, String password);


}
