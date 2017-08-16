package com.bfdelivery.cavaliers.database.location;

import android.content.Context;

import com.bfdelivery.cavaliers.database.DBDataEntry;
import com.bfdelivery.cavaliers.database.base.BaseDataService;

import org.greenrobot.greendao.AbstractDao;

/**
 * 定位数据服务信息
 */

public class LocationDataService extends BaseDataService<LocationData> {

	private static DaoMaster _daoMaster;

	private DaoSession mDaoSession = null;
	private LocationDataDao mDataDao = null;

	public static final void init(Context appContext) {
		_daoMaster = new DaoMaster(new DaoMaster.DevOpenHelper(appContext, DBDataEntry.DB_LOCATION, null).getWritableDatabase());
	}

	public static final LocationDataService getInstance() {
		return new LocationDataService(_daoMaster.newSession());
	}

	public LocationDataService(DaoSession daoSession) {
		mDaoSession = daoSession;
		mDataDao = mDaoSession.getLocationDataDao();
	}

	@Override
	public AbstractDao getDataDao() {
		return mDataDao;
	}
}
