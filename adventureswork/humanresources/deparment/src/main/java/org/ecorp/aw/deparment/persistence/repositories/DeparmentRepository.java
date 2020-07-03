package org.ecorp.aw.deparment.persistence.repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;

import org.ecorp.aw.deparment.model.Department;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@RequestScoped
public class DeparmentRepository implements PanacheRepositoryBase<Department, Integer> {

	@Transactional
	public Optional<Department> update(Integer id, Department toEntity) {
		// 

		Optional<Department> finded = findByIdOptional(id);
		if(finded.isEmpty())
			return Optional.empty();
		
		finded.get().setGroupname(toEntity.getGroupname());
		finded.get().setName(toEntity.getName());
		finded.get().setModifieddate(LocalDateTime.now());
		
		return finded;
		
	}

}