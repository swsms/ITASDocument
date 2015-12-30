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

    public DocumentTypeEntity getType() {
        return type;
    }

    public void setType(DocumentTypeEntity type) {
        this.type = type;
    }

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

}
