package com.engineer.lrogozinski.domain;

import javax.persistence.*;

@Entity
@Table(name = "vote")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne
    /*@JoinColumn(name = "id", insertable = false, updatable = false)*/
    private Event event;

    @ManyToOne
/*
    @JoinColumn(name = "id", insertable = false, updatable = false)
*/
    private UserData userData;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }
}
