package server.entities;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = -8706689714326132798L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "login", unique = true)
	private String login;

	@Column(name = "password")
	private String password;

	@OneToMany(cascade = { PERSIST, MERGE, REFRESH }, fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleEntity> roles;

	public UserEntity() {
	}

	public UserEntity(String name, String login, String password) {
		this.name = name;
		this.login = login;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<RoleEntity> roles() {
		if (roles == null)
			roles = new HashSet<RoleEntity>();
		return roles;
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", name=" + name + ", login=" + login
				+ ", roles=" + roles + "]";
	}
}
