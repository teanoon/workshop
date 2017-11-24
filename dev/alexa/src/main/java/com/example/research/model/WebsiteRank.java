package com.example.research.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class WebsiteRank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date created;
    private Long rank;
    private String url;

    @PrePersist
    public void initialize() {
       if (created == null) {
           created = new Date();
       }
    }

    public WebsiteRank() {
    }

    public WebsiteRank(Long rank, String url) {
        this.rank = rank;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


//    @Embeddable
//    public static class EmbeddedWebsiteId implements Serializable {
//
//
//
//
//        public Long getId() {
//            return id;
//        }
//
//        public void setId(Long id) {
//            this.id = id;
//        }
//
//        public Date getCreated() {
//            return created;
//        }
//
//        public void setCreated(Date created) {
//            this.created = created;
//        }
//
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(id, created);
//        }
//
//        @Override
//        public boolean equals(Object obj) {
//            if (this == obj) return true;
//            if (obj == null) return false;
//            if (getClass() != obj.getClass()) {
//                return false;
//            }
//            EmbeddedWebsiteId other = (EmbeddedWebsiteId) obj;
//            return Objects.equals(id, other.id) && created.equals(other.created);
//        }
//    }

}
