package com.uca.capas.repositories;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uca.capas.domain.Estudiante;
import com.uca.capas.domain.EstudianteMateria;
import com.uca.capas.domain.EstudianteMateriaV;
import com.uca.capas.domain.Usuario;

public interface EstudianteMateriaRepo extends JpaRepository<EstudianteMateria, Integer> {
	
	@Query(value = "SELECT id_materia_cursada,anio,ciclo,nota, CASE WHEN nota > 6 then 'Aprobada' else 'Reprobada' end as resultado" + 
			" from materias_cursadas where materias_cursadas.id_estudiante = ?1", nativeQuery = true)
	List<EstudianteMateriaV> findById_estudiante(int id_estudiante);
	
	
}
