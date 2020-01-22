package com.example.oficemanagement.repository;

import com.example.oficemanagement.model.AdminToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminTokenRepository extends JpaRepository<AdminToken, String> {
}
