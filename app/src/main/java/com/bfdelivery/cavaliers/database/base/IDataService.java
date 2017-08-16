package com.bfdelivery.cavaliers.database.base;

import java.util.List;

/**
 * Created by Panoo on 2015/12/14.
 * <p/>
 * 同意的数据查询interface
 */
public interface IDataService<T, K, V> {

	/**
	 * 插入一个数据
	 *
	 * @param obj the data
	 */
	long insert(T obj);

	/**
	 * 返回数据库第一条数据
	 */
	T first();

	/**
	 * 更新一个数据
	 *
	 * @param obj the data
	 */
	void update(T obj);

	/**
	 * 删除一个数据
	 *
	 * @param obj the data
	 */
	void delete(T obj);

	/**
	 * 删除某一个数据
	 * @param key 数据的键
	 * @param val 数据的原始值
	 * */
	void delete(K key, V val);

	/**
	 * 删除某一个数据
	 * @param keys 数据的键数组
	 * @param vals 数据的值数组
	 * */
	void delete(K[] keys, V[] vals);

	/**
	 * 删除一组数据
	 * @param key 数据的键
	 * @param val 数据的原始值
	 * */
	void deleteAll(K key, V val);

	/**
	 * 删除一组数据
	 * @param keys 数据的键数组
	 * @param vals 数据的值数组
	 * */
	void deleteAll(K[] keys, V[] vals);

	/**
	 * 删除所有数据
	 */
	void deleteAll();

	/**
	 * 通过键值获得一个数据列表
	 *
	 * @param key 属性
	 * @param val 属性的值
	 * @return 数据列表
	 */
	List<T> get(K key, V val);

	/**
	 * 通过键值获得单独一个数据
	 *
	 * @param key 属性
	 * @param val 属性的值
	 * @return 符合当前属性和属性值的数据
	 */
	T getUnique(K key, V val);

	/**
	 * 通过键值查询是否含有此数据
	 *
	 * @param key 属性
	 * @param val 属性的值
	 * @return 是否含有当前符合当前属性和属性值的数据
	 */
	boolean has(K key, V val);

	/**
	 * 通过键值数组获得一个数据列表
	 *
	 * @param keys 属性数组
	 * @param vals 属性值数组
	 * @return 符合当前查询条件的数据列表
	 */
	List<T> get(K[] keys, V[] vals);

	/**
	 * 通过键值数组获得单独一个数据
	 *
	 * @param keys 属性数组
	 * @param vals 属性值数组
	 * @return 符合当前查询条件的数据
	 */
	T getUnique(K[] keys, V[] vals);

	/**
	 * 通过键值数组查询是否含有此数据
	 *
	 * @param keys 属性数组
	 * @param vals 属性值数组
	 * @return 是否有符合当前查询条件的数据
	 */
	boolean has(K[] keys, V[] vals);

	/**
	 * 加载所有数据
	 *
	 * @return 所有的数据列表
	 */
	List<T> loadAll();

	/**
	 * 查询当前的数据个数
	 *
	 * @return 当前数据个数
	 */
	long count();

	/**
	 * 排序搜索，目前仅支持单个属性的排序
	 *
	 * @param orderKey 排序的属性；
	 * @param isAsc    升序还是降序？ true 升序，false 降序
	 * @return 符合当前查询条件的数据列表
	 */
	List<T> loadAllByOrder(K orderKey, boolean isAsc);
}
