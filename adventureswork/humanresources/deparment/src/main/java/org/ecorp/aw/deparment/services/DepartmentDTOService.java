package org.ecorp.aw.deparment.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.ecorp.aw.deparment.domain.DepartmentDTO;
import org.ecorp.aw.deparment.model.Department;
import org.ecorp.aw.deparment.persistence.repositories.DeparmentRepository;
import org.modelmapper.ModelMapper;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

@RequestScoped
public class DepartmentDTOService {

	@Inject
	private DeparmentRepository repository;

	@Inject
	private ModelMapper modelMapper;

	public Optional<DepartmentDTO> findById(Integer id) {

		Optional<Department> find = repository.findByIdOptional(id);
		if (find.isEmpty()) {
			return Optional.empty();
		}

		return Optional.of(convertoToDTO(find.get()));
	}

	public Optional<List<DepartmentDTO>> findList(final Integer startPosition, final Integer maxResult) {

		PanacheQuery<Department> pQuery = repository.findAll();
		List<Department> listRange = pQuery.range(startPosition, maxResult).list();

		if (listRange.isEmpty()) {
			return Optional.empty();
		}

		List<DepartmentDTO> collect = listRange.stream().map(department -> convertoToDTO(department))
				.collect(Collectors.toList());

		return Optional.of(collect);
	}

	@Transactional
	public Optional<DepartmentDTO> create(DepartmentDTO departmentdto) {

		departmentdto.setModifieddate(LocalDateTime.now());
		Department entity = convertoToEntity(departmentdto);
		repository.persist(entity);
		return Optional.of(convertoToDTO(entity));
	}

	private Department convertoToEntity(DepartmentDTO departmentdto) {
		return modelMapper.map(departmentdto, Department.class);
	}

	private DepartmentDTO convertoToDTO(Department department) {
		return modelMapper.map(department, DepartmentDTO.class);
	}

	@Transactional
	public Optional<DepartmentDTO> update(Integer id, @Valid DepartmentDTO departmentdto) {

		Department toEntity = convertoToEntity(departmentdto);
		Optional<Department> changed = repository.update(id, toEntity);
		if (changed.isEmpty()) {
			return Optional.empty();
		}

		return Optional.of(convertoToDTO(changed.get()));
	}

	@Transactional
	public boolean delete(Integer id) {
		return repository.deleteById(id);
		
	}

}
