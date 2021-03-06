package com.tuan.dictionary.collection;

import com.tuan.dictionary.purchasedetail.PurchaseDetail;
import com.tuan.dictionary.vocabulary.Vocabulary;
import com.tuan.validate.group.CollectionCreate;
import com.tuan.validate.group.CollectionUpdate;
import com.tuan.validate.group.CollectionUpdateExcludeImage;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "collection")
public class Collection {

//	@NotBlank(groups = {CollectionUpdate.class,CollectionUpdateExcludeImage.class})
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(groups = {CollectionCreate.class,CollectionUpdate.class,CollectionUpdateExcludeImage.class})
    @Column(name = "name")
    private String name;

    @NotBlank(groups = {CollectionCreate.class,CollectionUpdate.class,CollectionUpdateExcludeImage.class})
    @Column(name = "description")
    private String description;

    @NotBlank(groups= {CollectionCreate.class,CollectionUpdate.class})
    @Column(name = "images")
    private String imageNames;

    @Column(name = "create_date")
//    @Temporal(TemporalType.DATE)
    private LocalDateTime createDate;

    @Column(name = "update_time")
//    @Temporal(TemporalType.DATE)
    private LocalDateTime updateTime;

    @Column(name = "is_access")
    private boolean isAccess;

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

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isAccess() {
        return isAccess;
    }

    public void setAccess(boolean access) {
        isAccess = access;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
