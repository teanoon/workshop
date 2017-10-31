package com.example.research.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Website {

    @Id
    @GeneratedValue
    private Long id;
    private Integer rank;

    private String domain;
    private String title;
    private String description;
    private String keywords;
    private String icp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getIcp() {
        return icp;
    }

    public void setIcp(String icp) {
        this.icp = icp;
    }

    @Override
    public String toString() {
        return "Website{" +
                "id=" + id +
                ", rank=" + rank +
                ", domain='" + domain + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", keywords='" + keywords + '\'' +
                ", icp='" + icp + '\'' +
                '}';
    }

}
