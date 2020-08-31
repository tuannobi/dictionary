package com.tuan.dictionary.partofspeech;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tuan.dictionary.vocabulary.Vocabulary;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "part_of_speech")
public class PartOfSpeech {
    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "partOfSpeech")
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

    public List<Vocabulary> getVocabularies() {
        return vocabularies;
    }

    public void setVocabularies(List<Vocabulary> vocabularies) {
        this.vocabularies = vocabularies;
    }
}
