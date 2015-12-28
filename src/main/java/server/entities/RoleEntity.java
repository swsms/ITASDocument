package server.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "role")
public class RoleEntity implements Serializable {
    private static final long serialVersionUID = -8706689714326132798L;

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    public RoleEntity() {
        this.setId(-1L);
        this.setName("default name");
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

}
