package com.example.dossier.dossier.modelsDossier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "mv_ul_leader", schema = "imp_kfm_ul_old")
public class MvUlLeader {
    @Id
    private UUID id;
    private String bin_org;
    private Date reg_date;
    private String iin;
    @Column(name = "lastname")
    private String last_name;
    @Column(name = "firstname")
    private String first_name;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "position_id")
    private Integer position_id;
    @Column(name = "appointment_date")
    private Date appointment_date;
    @Column(name = "removal_date")
    private Date removal_date;
    @Column(name = "is_curr")
    private boolean is_curr;
    @Column(name = "ul_status")
    private String ul_status;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getBin_org() {
        return bin_org;
    }

    public void setBin_org(String bin_org) {
        this.bin_org = bin_org;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    public String getIin() {
        return iin;
    }

    public void setIin(String iin) {
        this.iin = iin;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Integer getPosition_id() {
        return position_id;
    }

    public void setPosition_id(Integer position_id) {
        this.position_id = position_id;
    }

    public Date getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(Date appointment_date) {
        this.appointment_date = appointment_date;
    }

    public Date getRemoval_date() {
        return removal_date;
    }

    public void setRemoval_date(Date removal_date) {
        this.removal_date = removal_date;
    }

    public boolean isIs_curr() {
        return is_curr;
    }

    public void setIs_curr(boolean is_curr) {
        this.is_curr = is_curr;
    }

    public String getUl_status() {
        return ul_status;
    }

    public void setUl_status(String ul_status) {
        this.ul_status = ul_status;
    }
}
