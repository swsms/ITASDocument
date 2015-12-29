package server.entities;

import javax.persistence.*;

@Entity
@Table(name = "documents")
public class DocumentEntity extends ObjectEntity {

    @ManyToOne
    @JoinColumn(name = "document_type_id")
    private DocumentTypeEntity type;

    @Column(name = "ident")
    private String ident;

    public DocumentEntity() {

    }

}
