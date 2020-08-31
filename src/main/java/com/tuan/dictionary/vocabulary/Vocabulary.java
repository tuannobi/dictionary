package com.tuan.dictionary.vocabulary;

import com.tuan.dictionary.collection.Collection;
import com.tuan.dictionary.partofspeech.PartOfSpeech;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name="vocabulary")
public class Vocabulary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "english_word")
    private String englishWord;

    @Column(name = "pronunciation")
    private String pronunciation;

    @Column(name = "vietnamese_meaning")
    private String vietnameseMeaning;

    @Column(name = "image_names")
    private String imageNames;

    @Column(name = "example")
    private String example;

    @Column(name = "update_time")
    @Temporal(TemporalType.DATE)
    private Date updateTime;

    @Column(name = "abbreviation")
    private String abbreviation;

    @Column(name = "sound")
    private String sound;

    @Column(name = "english_meaning")
    private String englishMeaning;

    @ManyToOne
    @JoinColumn(name = "collection")
    private Collection collection;

    @ManyToOne
    @JoinColumn(name = "part_of_speech")
    private PartOfSpeech partOfSpeech;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }



    public String getVietnameseMeaning() {
        return vietnameseMeaning;
    }

    public void setVietnameseMeaning(String vietnameseMeaning) {
        this.vietnameseMeaning = vietnameseMeaning;
    }

    public String getImageNames() {
        return imageNames;
    }

    public void setImageNames(String imageNames) {
        this.imageNames = imageNames;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }


    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getEnglishMeaning() {
        return englishMeaning;
    }

    public void setEnglishMeaning(String englishMeaning) {
        this.englishMeaning = englishMeaning;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public PartOfSpeech getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(PartOfSpeech partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }
}
