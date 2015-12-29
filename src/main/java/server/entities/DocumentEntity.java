package server.entities;

import javax.persistence.*;

@Entity
@Table(name = "documents")
public class DocumentEntity extends ObjectEntity {

    @Column(name = "document_type_id")
    private Long typeId;

    @Column(name = "ident")
    private String ident;

    public DocumentEntity() {

    }

}
