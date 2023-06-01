package com.bitbucket.test.infrastructure.repository;

import com.bitbucket.test.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
