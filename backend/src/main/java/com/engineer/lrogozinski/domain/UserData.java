package com.engineer.lrogozinski.domain;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "user_data")
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "email")
    private String email;

    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "name")
    private String name;

    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "surname")
    private String surname;

    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "city")
    private String city;

    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "street")
    private String street;

    @NotNull
    @Column(name = "house_no")
    @Min(1)
    private Integer houseNo;

    @Column(name = "flat_no")
    @Min(1)
    private Integer flatNo;

    @Column(name = "average_vote")
    private Double averageVote;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<Event> eventList = new ArrayList<>();

    @Version
    @Column(name = "version")
    private Integer version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(Integer houseNo) {
        this.houseNo = houseNo;
    }

    public Integer getFlatNo() {
        return flatNo;
    }

    public void setFlatNo(Integer flatNo) {
        this.flatNo = flatNo;
    }

    public Double getAverageVote() {
        return averageVote;
    }

    public void setAverageVote(Double averageVote) {
        this.averageVote = averageVote;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Collection<Event> getEventList() {
        return eventList;
    }

    public void setEventList(Collection<Event> eventList) {
        this.eventList = eventList;
    }
}