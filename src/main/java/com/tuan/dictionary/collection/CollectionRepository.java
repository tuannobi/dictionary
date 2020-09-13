package com.tuan.dictionary.collection;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public interface CollectionRepository extends CrudRepository<Collection,Long> {

    @Modifying
    @Query("update Collection set name=?2,description=?3,updateTime=?4,isAccess=?5 where id=?1")
    int updateCollection(Long id,String name, String description, LocalDateTime updateTime, boolean isAccess);
}
