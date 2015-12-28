package server.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "system_object")
public class ObjectEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_created")
    @Temporal(value = TemporalType.DATE)
    private Date dateCreated;

//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
    @Column(name = "user_id")
    private long creater;



    public ObjectEntity() {

    }

    public ObjectEntity(String name, Date dateCreated, UserEntity creater) {
        this.name = name;
        this.dateCreated = dateCreated;
//        this.creater = creater;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}