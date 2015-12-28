package server.entities;

import javax.persistence.*;
import java.util.Date;

public class ObjectEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_created")
    @Temporal(value = TemporalType.DATE)
    private Date dateCreated;

    public ObjectEntity() { }


}