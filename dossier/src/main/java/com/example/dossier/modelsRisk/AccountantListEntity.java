package com.example.dossier.modelsRisk;

import javax.annotation.Nullable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "imp_notary.accountant_list")
public class AccountantListEntity {

    @Nullable

    @Column(name = "bin")
    private String bin;
    @Nullable

    @Column(name = "prof")
    private String prof;
    @Nullable
    @Id

    @Column(name = "iin")
    private String iin;
    @Nullable

    @Column(name = "lname")
    private String lname;
    @Nullable

    @Column(name = "fname")
    private String fname;


    @Nullable
    public String getBin() {
        return bin;
    }

    public void setBin(@Nullable String bin) {
        this.bin = bin;
    }

    @Nullable
    public String getProf() {
        return prof;
    }

    public void setProf(@Nullable String prof) {
        this.prof = prof;
    }

    @Nullable
    public String getIin() {
        return iin;
    }

    public void setIin(@Nullable String iin) {
        this.iin = iin;
    }

    @Nullable
    public String getLname() {
        return lname;
    }

    public void setLname(@Nullable String lname) {
        this.lname = lname;
    }

    @Nullable
    public String getFname() {
        return fname;
    }

    public void setFname(@Nullable String fname) {
        this.fname = fname;
    }
}