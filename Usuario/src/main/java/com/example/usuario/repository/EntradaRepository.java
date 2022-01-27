package com.example.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.usuario.model.Entrada;

@Repository
public interface EntradaRepository extends JpaRepository<Entrada,String>{
}




