package com.uca.capas.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.uca.capas.domain.CurrentSession;
import com.uca.capas.domain.Usuario;

public interface CurrentSessionService {
	
	public CurrentSession findOne(String ip_session) throws DataAccessException;
	
	public List<CurrentSession> findAll() throws DataAccessException;
	
	public void delete(String ip_session) throws DataAccessException;
	
	public void insert(CurrentSession currentSession) throws DataAccessException;
}
