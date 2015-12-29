package server.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "objects")
@Inheritance(strategy = InheritanceType.JOINED)
public class ObjectEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_created")
    @Temporal(value = TemporalType.DATE)
    private Date dateCreated;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity creator;


    public ObjectEntity() {

    }

    public ObjectEntity(String name, Date dateCreated, UserEntity creator) {
        this.name = name;
        this.dateCreated = dateCreated;
        this.creator = creator;
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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public UserEntity getCreator() {
        return creator;
    }

    public void setCreator(UserEntity userEntity) {
        this.creator = userEntity;
    }


}