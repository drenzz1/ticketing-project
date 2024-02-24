package com.foo.repository;

import com.foo.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String username);
    @Transactional
    void deleteByUserName(String username);


}
