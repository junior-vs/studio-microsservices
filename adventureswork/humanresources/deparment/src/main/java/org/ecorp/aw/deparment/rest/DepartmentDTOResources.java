/**
 * 
 */
package org.ecorp.aw.deparment.rest;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.ecorp.aw.deparment.domain.DepartmentDTO;
import org.ecorp.aw.deparment.services.DepartmentDTOService;

/**
 * @author junior
 *
 */
@Path("/department")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class DepartmentDTOResources {

	@Inject
	private DepartmentDTOService service;

	/**
	 * @param departmentdto
	 * @return
	 */
	@POST
	public Response create(@Valid final DepartmentDTO departmentdto) {

		Optional<DepartmentDTO> created = service.create(departmentdto);

		URI location = UriBuilder.fromResource(getClass()).path(String.valueOf(created.get().getDepartmentid()))
				.build();
		return Response.created(location).build();
	}

	/**
	 * @param id
	 * @return
	 */
	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final Integer id) {

		Optional<DepartmentDTO> departmentdto = service.findById(id);
		if (departmentdto.isEmpty()) {
			return Response.status(NOT_FOUND).build();
		}
		return Response.ok(departmentdto).build();
	}

	/**
	 * @param startPosition
	 * @param maxResult
	 * @return
	 */
	@GET
	public Response listAll(@QueryParam("start") @DefaultValue("0") final Integer startPosition,
			@QueryParam("max") @DefaultValue("9") final Integer maxResult) {

		final Optional<List<DepartmentDTO>> listFinder = service.findList(startPosition, maxResult);

		if (listFinder.isEmpty()) {
			return Response.status(NOT_FOUND).build();
		}
		return Response.ok(listFinder.get()).build();
	}

	/**
	 * @param id
	 * @param departmentdto
	 * @return
	 */
	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") Integer id, @Valid final DepartmentDTO departmentdto) {
		Optional<DepartmentDTO> changed = service.update(id, departmentdto);
		if (changed.isEmpty()) {
			Response.status(NOT_FOUND).build();
		}

		return Response.noContent().build();
	}

	/**
	 * @param id
	 * @return
	 */
	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final Integer id) {
		if (service.delete(id)) {
			return Response.noContent().build();
		}
		return Response.status(NOT_FOUND).build();

	}

}
