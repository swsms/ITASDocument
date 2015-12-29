package server.entities;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "document_types")
public class DocumentTypeEntity extends ObjectEntity {

    public DocumentTypeEntity() {

    }
}
