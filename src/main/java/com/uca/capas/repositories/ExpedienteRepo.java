package com.uca.capas.repositories;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uca.capas.domain.Estudiante;
import com.uca.capas.domain.EstudianteMateria;
import com.uca.capas.domain.EstudianteMateriaV;
import com.uca.capas.domain.Expediente;
import com.uca.capas.domain.Usuario;

public interface ExpedienteRepo extends JpaRepository<Expediente, Integer> {
	
	@Query(value = "SELECT b.primer_nombre as nombre, b.primer_apellido as apellido, COUNT(CASE WHEN nota >= 6 THEN 1 end) as materias_ap,"
			+ "COUNT(CASE WHEN nota < 6 THEN 1 end) as materias_rep,SUM(cast(a.nota as decimal))/COUNT(a.id_materia_cursada) as promedio"+
			" from materias_cursadas a ,estudiantes b where b.primer_nombre = ?1 or b.primer_apellido = ?2"
			+ "GROUP BY  b.primer_nombre, b.primer_apellido", nativeQuery = true)
	List<Expediente> findByPrimer_nombreOrPrimer_apellido(String primer_nombre,String primer_apellido);
		
}
