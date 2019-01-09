package com.engineer.lrogozinski.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "event")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "place")
    private String place;

    @NotNull
    @Column(name = "date")
    private Date date;

    @NotNull
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "avaliable_quantity")
    private Integer avaliableQuantity;

    @NotNull
    @Column(name = "price")
    private Integer price;

    @Column(name = "average_vote")
    private Double averageVote;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    private List<Vote> votes = new ArrayList<>();

    @ManyToOne
    EventCategory eventCategory;

    @ManyToOne
    @JoinColumn(name = "user_id")
    // @JsonBackReference
    private UserData user;

    @Version
    @Column(name = "version")
    private Integer version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAvaliableQuantity() {
        return avaliableQuantity;
    }

    public void setAvaliableQuantity(Integer avaliableQuantity) {
        this.avaliableQuantity = avaliableQuantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {

        this.user = user;
    }

    public Double getAverageVote() {
        return averageVote;
    }

    public void setAverageVote(Double averageVote) {
        this.averageVote = averageVote;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
        eventCategory.addEvent(this);
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
