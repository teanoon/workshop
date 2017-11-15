package com.example.research.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Rank {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "website_id")
    private Website website;

    private Integer value;
    private Date created;

    @PrePersist
    public void setDateBeforeSave() {
        if (created == null) {
            created = new Date();
        }
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Website getWebsite() {
        return website;
    }

    public void setWebsite(Website website) {
        this.website = website;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}
