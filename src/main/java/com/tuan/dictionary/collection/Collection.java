package com.tuan.dictionary.collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tuan.dictionary.vocabulary.Vocabulary;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @Column(name = "images")
    private String imageNames;

    @Column(name = "create_date")
//    @Temporal(TemporalType.DATE)
    private LocalDateTime createDate;

    @Column(name = "update_time")
//    @Temporal(TemporalType.DATE)
    private LocalDateTime updateTime;





}
