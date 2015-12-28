package server.entities;

import javax.persistence.*;
import java.io.Serializable;

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

//    //@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "app_user")
//    private Set<RoleEntity> roles;

    public UserEntity() {
        this.id = -1L;
    }

    public UserEntity(String name, String login, String password) {
        this();
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

    public boolean isCreated() {
        return id != -1;
    }
//    public Set<RoleEntity> roles(){
//        return roles;
//    }
}

