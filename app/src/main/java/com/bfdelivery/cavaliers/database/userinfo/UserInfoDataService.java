package com.bfdelivery.cavaliers.database.userinfo;

import android.content.Context;

import com.bfdelivery.cavaliers.database.DBDataEntry;
import com.bfdelivery.cavaliers.database.base.BaseDataService;

import org.greenrobot.greendao.AbstractDao;

/**
 * Created by Panoo on 2017/8/23.
 */

public class UserInfoDataService extends BaseDataService<UserInfo> {

	private static DaoMaster _daoMaster;

	private DaoSession mDaoSession = null;
	private UserInfoDao mDataDao = null;

	public static final void init(Context appContext) {
		_daoMaster = new DaoMaster(new DaoMaster.DevOpenHelper(appContext, DBDataEntry.DB_USER, null).getWritableDatabase());
	}

	public static final UserInfoDataService getInstance() {
		return new UserInfoDataService(_daoMaster.newSession());
	}

	public UserInfoDataService(DaoSession daoSession) {
		mDaoSession = daoSession;
		mDataDao = mDaoSession.getUserInfoDao();
	}

	@Override
	public AbstractDao getDataDao() {
		return mDataDao;
	}

	public UserInfo saveUserInfo(UserInfo info) {
		UserInfo oldInfo = first();
		long currentTime = System.currentTimeMillis();
		if (oldInfo == null) {
			info.setCreateTime(currentTime);
		} else {
			deleteAll();
			info.setCreateTime(oldInfo.getCreateTime());
		}
		info.setUpdateTime(currentTime);
		insert(info);
		return info;
	}
}
