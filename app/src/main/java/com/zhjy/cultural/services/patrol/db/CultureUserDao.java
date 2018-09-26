package com.zhjy.cultural.services.patrol.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.CultureUser;
import java.util.List;


@Dao
public interface CultureUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CultureUser cultureUser);

    @Query("SELECT * FROM cultureuser WHERE id = :id")
    LiveData<CultureUser> findById(String id);

    @Query("SELECT * FROM cultureuser WHERE mobile = :mobile")
    LiveData<CultureUser> findByMobile(String mobile);

    @Query("SELECT * FROM cultureuser")
    LiveData<List<CultureUser>> findAll();

    @Delete
    void deleteAll(CultureUser... cultureUser);
}

