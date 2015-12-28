package server.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class RoleEntity implements Serializable {
	private static final long serialVersionUID = -8706689714326132798L;

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String name;

	public RoleEntity() {
	}

	public RoleEntity(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "RoleEntity [id=" + id + ", name=" + name + "]";
	}

}
