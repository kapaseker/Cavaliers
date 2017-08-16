package com.bfdelivery.cavaliers.database.base;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;


/**
 * Created by Panoo on 2015/12/14.
 * <p/>
 * 基本的数据查询对象，基于GreenDao，官网地址：<a href="http://greendao-orm.com/">green dao</a>
 */
public abstract class BaseDataService<T> implements IDataService<T, Property, Object> {

	/**
	 * insert a new record
	 * @param obj
	 * */
	@Override
	public long insert(T obj) {
		return getDataDao().insert(obj);
	}

	/**
	 * return first record
	 */
	@Override
	public T first() {
		QueryBuilder<T> queryBuilder = getDataDao().queryBuilder();
		return queryUnique(queryBuilder);
	}

	/**
	 * update a existing record , make sure it is existing
	 * @param obj
	 * */
	@Override
	public void update(T obj) {
		getDataDao().update(obj);
	}

	/**
	 * delete a existing record , make sure it is existing
	 * @param obj
	 * */
	@Override
	public void delete(T obj) {
		getDataDao().delete(obj);
	}

	@Override
	public void delete(Property[] keys, Object[] vals) {
		T result = getUnique(keys, vals);
		if (result != null) {
			delete(result);
		}
	}

	@Override
	public void delete(Property key, Object val) {
		T obj = getUnique(key,val);
		if(obj != null){
			delete(obj);
		}
	}

	@Override
	public void deleteAll(Property key, Object val) {
		List<T> results = get(key,val);
		getDataDao().deleteInTx(results);
	}

	@Override
	public void deleteAll(Property[] keys, Object[] vals) {
		List<T> results = get(keys, vals);
		getDataDao().deleteInTx(results);
	}

	/**
	 * delete all records
	 * */
	@Override
	public void deleteAll() {
		getDataDao().deleteAll();
	}


	/**
	 * query a records list
	 * @param key the name of column
	 * @param val the value of specific column
	 * @return the result list
	 * */
	@Override
	public List<T> get(Property key, Object val) {

		QueryBuilder<T> queryBuilder = getQueryBuilder();
		return query(queryBuilder.where(key.eq(val)));

	}

	/**
	 * query a records list
	 * @param keys the names of column
	 * @param vals the values of specific column
	 * @return the result list
	 * */
	@Override
	public List<T> get(Property[] keys, Object[] vals) {

		checkKeyVal(keys, vals);

		QueryBuilder<T> queryBuilder = getQueryBuilder();

		int len = keys.length;

		for (int i = 0; i < len; ++i) {
			queryBuilder.where(keys[i].eq(vals[i]));
		}

		return query(queryBuilder);
	}

	/**
	 * 按照查询条件进行单个的查询
	 * @param queryBuilder 查询条件
	 * @return 查询结果
	 * */
	public T queryUnique(QueryBuilder<T> queryBuilder) {
		return queryBuilder.limit(1).build().unique();
	}

	/**
	 * 按照查询条件进行多个结果查询
	 * @param queryBuilder 查询条件
	 * @return 查询结果
	 * */
	public List<T> query(QueryBuilder<T> queryBuilder) {
		return queryBuilder.list();
	}

	/**
	 * query a unique record
	 * @param keys the names of column
	 * @param vals the values of specific column
	 * @return the result list
	 * */
	@Override
	public T getUnique(Property[] keys, Object[] vals) {

		checkKeyVal(keys, vals);

		QueryBuilder<T> queryBuilder = getQueryBuilder();

		int len = keys.length;

		for (int i = 0; i < len; ++i) {
			queryBuilder.where(keys[i].eq(vals[i]));
		}

		return queryUnique(queryBuilder);
	}

	/**
	 * check has a specific record
	 * @param key the names of column
	 * @param val the values of specific column
	 * @return true means has , false means not
	 * */
	@Override
	public boolean has(Property[] key, Object[] val) {
		return getUnique(key, val) != null;
	}

	/**
	 * query a unique record
	 * @param key the name of column
	 * @param val the value of specific column
	 * @return the result
	 * */
	@Override
	public T getUnique(Property key, Object val) {
		QueryBuilder<T> queryBuilder = getQueryBuilder();
		return queryUnique(queryBuilder.where(key.eq(val)));
	}

	/**
	 * check has a specific record
	 * @param key the name of column
	 * @param val the value of specific column
	 * @return true means has , false means not
	 * */
	@Override
	public boolean has(Property key, Object val) {
		return getUnique(key, val) != null;
	}

	/**
	 * get all records in table
	 * @return record list
	 * */
	@Override
	public List<T> loadAll() {
		return getDataDao().loadAll();
	}

	/**
	 * return the count of current records
	 * @return count of current records
	 * */
	@Override
	public long count() {
		return getDataDao().count();
	}

	/**
	 * get all records in table order by a specific property
	 * @param orderKey the property
	 * @param isAsc asc or desc
	 * @return record list
	 * */
	@Override
	public List<T> loadAllByOrder(Property orderKey, boolean isAsc) {
		QueryBuilder queryBuilder = getQueryBuilder();

		if (orderKey != null) {
			if (isAsc) {
				queryBuilder.orderAsc(orderKey);
			} else {
				queryBuilder.orderDesc(orderKey);
			}
		}

		return query(queryBuilder);
	}


	/**
	 * 检查查询条件是否符合规则
	 */
	private void checkKeyVal(Property[] keys, Object[] vals) {
		if (keys == null || vals == null) {
			throw new RuntimeException("the key or val is null");
		}

		if (keys.length != vals.length) {
			throw new RuntimeException("the key's length is not equal val's length");
		}
	}

	/**
	 * 获取一个查询条件
	 *
	 * @return 返回一个查询构建条件
	 */
	public QueryBuilder<T> getQueryBuilder() {
		QueryBuilder<T> queryBuilder = getDataDao().queryBuilder();
		return queryBuilder;
	}

	/**
	 * 获得Dao数据结构
	 * 继承类需要继承此函数获得Dao数据结构
	 *
	 * @return 一个Dao数据结构
	 */
	public abstract AbstractDao getDataDao();
}
