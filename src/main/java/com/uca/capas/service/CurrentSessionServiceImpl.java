package com.uca.capas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uca.capas.domain.CurrentSession;
import com.uca.capas.domain.Usuario;
import com.uca.capas.repositories.CurrentSessionRepo;

@Service
public class CurrentSessionServiceImpl implements CurrentSessionService {

	@Autowired
	private CurrentSessionRepo currentSessionRepo;


	@Override
	public void delete(String ip_session) throws DataAccessException {
		currentSessionRepo.deleteById(ip_session);
		
	}

	@Override
	public List<CurrentSession> findAll() throws DataAccessException {
		return currentSessionRepo.findAll();
	}

	@Override
	@Transactional
	public void insert(CurrentSession currentSession) throws DataAccessException {
		currentSessionRepo.save(currentSession);
	}

	@Override
	public CurrentSession findOne(String ip_session) throws DataAccessException {
		return currentSessionRepo.getOne(ip_session);
	}

}
