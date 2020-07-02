package com.uca.capas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uca.capas.domain.CurrentSession;

public interface CurrentSessionRepo extends JpaRepository<CurrentSession, String> {

}
