package com.myangels.furnitereecommerce.repository;


import com.myangels.furnitereecommerce.entity.Users;
import com.myangels.furnitereecommerce.model.dto.request.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    @Query(nativeQuery = true, value = "SELECT u.id as id, u.email as email, u.password,u.full_name as fullName FROM users u WHERE u.email = :email AND u.password = :password")
    Optional<UserInfo> findUserInfoByEmailAndPassword(String email, String password);
}
