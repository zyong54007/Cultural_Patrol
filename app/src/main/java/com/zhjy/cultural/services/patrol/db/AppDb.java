package com.zhjy.cultural.services.patrol.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.zhjy.cultural.services.patrol.biz.pojo.bean.CultureUser;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.RouteBean;


/**
 * Created by jialg on 2018/1/8.
 */
@Database(entities = {CultureUser.class, RouteBean.class},
        version = 3, exportSchema = true)
public abstract class AppDb  extends RoomDatabase {

    abstract public CultureUserDao cultureUserDao();

    abstract public RouteDao routeDao();

}
