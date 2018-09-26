package com.zhjy.cultural.services.patrol.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.zhjy.cultural.services.patrol.biz.pojo.bean.RouteBean;

import java.util.List;


/**
 * Created by jialg on 2018/1/9.
 */
@Dao
public interface RouteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(RouteBean routeBean);

    @Query("SELECT * FROM routebean WHERE userId = :userId AND status = :status  ORDER BY dateTime ASC")
    LiveData<List<RouteBean>> findByStatus(String userId, String status);

    @Query("SELECT * FROM routebean WHERE userId = :userId AND dateTime = :dateTime ORDER BY dateTime ASC")
    LiveData<List<RouteBean>> findByDate(String userId, String dateTime);

    @Query("SELECT * FROM routebean WHERE userId = :userId  AND status = :status AND dateTime = :dateTime  ORDER BY dateTime ASC")
    LiveData<List<RouteBean>> findByStatusAndDate(String userId, String status, String dateTime);

}
