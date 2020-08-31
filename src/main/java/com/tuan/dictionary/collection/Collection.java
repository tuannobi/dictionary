package com.tuan.dictionary.collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tuan.dictionary.vocabulary.Vocabulary;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "collection")
public class Collection {
    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image_names")
    private String imageNames;

    @Column(name = "create_date")
    @Temporal(TemporalType.DATE)
    private Date createdate;

    @Column(name = "update_time")
    @Temporal(TemporalType.DATE)
    private Date updateTime;

    @JsonIgnore
    @OneToMany(mappedBy = "collection")
    private List<Vocabulary> vocabularies;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageNames() {
        return imageNames;
    }

    public void setImageNames(String imageNames) {
        this.imageNames = imageNames;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<Vocabulary> getVocabularies() {
        return vocabularies;
    }

    public void setVocabularies(List<Vocabulary> vocabularies) {
        this.vocabularies = vocabularies;
    }
}
