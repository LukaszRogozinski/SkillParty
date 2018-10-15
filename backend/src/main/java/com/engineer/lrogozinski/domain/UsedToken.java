package com.engineer.lrogozinski.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "used_token")
public class UsedToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "token")
    private String token;

    @Column(name = "date")
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UsedToken(String token, Date date){
        this.token = token;
        this.date = date;
    }
    public UsedToken(){

    }
}
