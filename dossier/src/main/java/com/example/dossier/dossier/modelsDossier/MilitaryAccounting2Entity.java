package com.example.dossier.dossier.modelsDossier;


import javax.annotation.Nullable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "imp_kfm_fl.military_accounting")
public class MilitaryAccounting2Entity {
    @Id
    @Nullable
    private Long id;

    @Column(name = "bin")
    @Nullable
    private String bin;


    @Column(name = "date_start")
    @Nullable
    private String dateStart;

    @Column(name = "iin")
    @Nullable
    private String iin;



    @Column(name = "date_end")
    @Nullable
    private String dateEnd;

    @Nullable
    public Long getId() {
        return id;
    }

    public void setId(@Nullable Long id) {
        this.id = id;
    }

    @Nullable
    public String getBin() {
        return bin;
    }

    public void setBin(@Nullable String bin) {
        this.bin = bin;
    }


    @Nullable
    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(@Nullable String dateStart) {
        this.dateStart = dateStart;
    }

    @Nullable
    public String getIin() {
        return iin;
    }

    public void setIin(@Nullable String iin) {
        this.iin = iin;
    }

    @Nullable
    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(@Nullable String dateEnd) {
        this.dateEnd = dateEnd;
    }
}