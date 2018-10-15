package com.engineer.lrogozinski.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "event_category")
public class EventCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Column(name = "name")
    private String  name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "EVENT_EVENT_CATEGORY", joinColumns = @JoinColumn(name = "event_category_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    @JsonBackReference
    private List<Event> events = new ArrayList<>();

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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> event) {
        this.events = event;
    }
}
