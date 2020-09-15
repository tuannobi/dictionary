package com.tuan.dictionary.collection;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public interface CollectionRepository extends CrudRepository<Collection,Long> {

    @Modifying
    @Query("update Collection set name=?2,description=?3,updateTime=?4,isAccess=?5,imageNames=?6 where id=?1")
    int update(Long id,String name, String description, LocalDateTime updateTime, boolean isAccess,String imageNames);
    
    @Query("select c.imageNames from Collection c where id=?1")
    String getUrlImageById(Long id);
    
    @Modifying
    @Query("update Collection set name=?2,description=?3,updateTime=?4,isAccess=?5 where id=?1")
    int updateExcludeImage(Long id,String name, String description, LocalDateTime updateTime, boolean isAccess);
}
