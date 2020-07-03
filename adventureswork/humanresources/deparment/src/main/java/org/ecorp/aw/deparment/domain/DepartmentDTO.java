package org.ecorp.aw.deparment.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

public class DepartmentDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer departmentid;
    

    @NotBlank
    @NotNull
    @NotEmpty
    private String groupname;

    private LocalDateTime modifieddate;

    @NotBlank
    @NotNull
    @NotEmpty
    private String name;

    public DepartmentDTO(final Integer departmentid, final String groupname, final LocalDateTime modifieddate,
            final String name) {
        super();
        this.departmentid = departmentid;
        this.groupname = groupname;
        this.modifieddate = modifieddate;
        this.name = name;
    }

    public DepartmentDTO() {
        // Auto-generated constructor stub
    }

    public Integer getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(Integer departmentid) {
        this.departmentid = departmentid;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public LocalDateTime getModifieddate() {
        return modifieddate;
    }

    public void setModifieddate(LocalDateTime modifieddate) {
        this.modifieddate = modifieddate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}