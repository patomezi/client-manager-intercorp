package com.intercorp.clientmanager.dao;

import com.intercorp.clientmanager.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientsDao extends JpaRepository<Cliente, Long> { }
