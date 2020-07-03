package org.ecorp.aw.deparment.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the department database table.
 * 
 */
@Entity
@Table(name="department", schema = "humanresources")
@NamedQuery(name="Department.findAll", query="SELECT d FROM Department d")
public class Department implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DEPARTMENT_DEPARTMENTID_GENERATOR", sequenceName="HUMANRESOURCES.DEPARTMENT_DEPARTMENTID_SEQ", 
		schema = "humanresources", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DEPARTMENT_DEPARTMENTID_GENERATOR")
	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private Integer departmentid;

	@Column(nullable=false, columnDefinition = "")
	private String groupname;

	@Column(nullable=false)
	private LocalDateTime modifieddate;

	@Column(nullable=false)
	private String name;

	
	public Department() {
		//
	}

	public Integer getDepartmentid() {
		return this.departmentid;
	}

	public void setDepartmentid(Integer departmentid) {
		this.departmentid = departmentid;
	}

	public String getGroupname() {
		return this.groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public LocalDateTime getModifieddate() {
		return this.modifieddate;
	}

	public void setModifieddate(LocalDateTime modifieddate) {
		this.modifieddate = modifieddate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}