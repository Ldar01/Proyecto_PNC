package com.uca.capas.repositories;

import com.uca.capas.domain.EstudianteMateria;
import com.uca.capas.domain.EstudianteMateriaV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EstudianteMateriaVRepo extends JpaRepository<EstudianteMateriaV, Integer> {
	
	@Query(value = "SELECT a.id_materia_cursada,b.nombre_materia,a.anio,a.ciclo,a.nota, CASE WHEN a.nota >= 6 then 'Aprobada' else 'Reprobada' end as resultado" +
			" from materias_cursadas a,materias b where a.id_estudiante = ?1 and a.id_materia = b.id_materia", nativeQuery = true)
	List<EstudianteMateriaV> findById_estudiante(int id_estudiante);
	
	
}
