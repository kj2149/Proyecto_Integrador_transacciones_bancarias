package com.example.SistemaTransaccionesBancarias.repository;

import com.example.SistemaTransaccionesBancarias.model.Pocket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository

public interface PocketRepository extends JpaRepository<Pocket, Long>{
}
