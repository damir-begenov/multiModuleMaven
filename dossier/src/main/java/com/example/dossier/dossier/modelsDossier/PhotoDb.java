package com.example.dossier.dossier.modelsDossier;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "photo", schema = "import_fl")

public class PhotoDb {

    private String iin;
    @Id

    private String document_type_id;

    private byte[] photo;
    private String date;

    public String getIin() {
        return iin;
    }

    public void setIin(String iin) {
        this.iin = iin;
    }

    public String getDocument_type_id() {
        return document_type_id;
    }

    public void setDocument_type_id(String document_type_id) {
        this.document_type_id = document_type_id;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "photoDb{" +
                "iin='" + iin + '\'' +
                ", document_type_id='" + document_type_id + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
