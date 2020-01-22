package com.example.oficemanagement.repository;

import com.example.oficemanagement.model.Information;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformationRepository extends JpaRepository<Information, String> {
}
