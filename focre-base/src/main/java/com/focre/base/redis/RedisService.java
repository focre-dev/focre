package com.focre.base.redis;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public interface RedisService {
	
	/**
	 * 删除缓存<br>
	 * 根据key精确匹配删除
	 * @param key
	 */
	void del(String... key);
	
	/**
	 * @Title: opsForListPushAll
	 * @author ye21st ye21st@gmail.com
	 * @date 2020年01月31日18:28:20
	 * @Description: 添加一个集合
	 * @param key:listkey
	 * @param values: list集合
	 */
	
	void opsForListPushAll(String key, List<Object> values);
	
	/**
	 * @Title: opsForListRemoveAll
	 * @author ye21st ye21st@gmail.com
	 * @date 2020年01月31日18:28:32
	 * @Description: 删除集合中的全部数据
	 * @param key
	 *        ： list key
	 */
	
	void opsForListRemoveAll(String key);
	
	/**
	 * @Title: opsForListKey
	 * @author ye21st ye21st@gmail.com
	 * @date 2020年01月31日18:28:39
	 * @Description: 根据key获取一个集合
	 * @param key:key
	 * @return List<Object>
	 */
	
	List<Object> opsForListKey(String key);
	
	/**
	 * @Title: opsForListRemove
	 * @author ye21st ye21st@gmail.com
	 * @date 2020年01月31日18:28:47
	 * @Description: 删除集合中的一个元素
	 * @param key:key
	 * @param value
	 */
	
	void opsForListRemove(String key, Object value);
	
	/**
	 * @Title: opsForListLeftPush
	 * @author ye21st ye21st@gmail.com
	 * @date 2020年01月31日18:28:53
	 * @Description: 在集合前面添加元素
	 * @param key:key
	 * @param value:值
	 * @throws
	 */
	void opsForListLeftPush(String key, Object value);
	
	/**
	 * @Title: opsForListRightPush
	 * @author ye21st ye21st@gmail.com
	 * @date 2020年01月31日18:29:10
	 * @Description: 在集合后面添加元素
	 * @param key:key
	 * @param value:值
	 * @throws
	 */
	void opsForListRightPush(String key, Object value);
	
	/**
	 * @Title: opsForHashEntries
	 * @author ye21st ye21st@gmail.com
	 * @date 2020年01月31日18:29:16
	 * @Description: 获取 key下的 所有MAp
	 * @param key:key
	 * @return Map<Object,Object>
	 */
	Map<Object, Object> opsForHashEntries(String key);
	
	/**
	 * @Title: opsForHashGet
	 * @author ye21st ye21st@gmail.com
	 * @date 2020年01月31日18:29:23
	 * @Description: 通过key、hashKey 获取一个值
	 * @param key:key
	 * @param hashKey:mapkey
	 * @return Object
	 * @throws
	 */
	Object opsForHashGet(String key, String hashKey);
	
	/**
	 * @Title: opsForHashPut
	 * @author ye21st ye21st@gmail.com
	 * @date 2020年01月31日18:29:31
	 * @Description: 往Map中添加可以 value
	 * @param key:key
	 * @param hashKey:mapkey
	 * @param value:值
	 * @return void
	 */
	void opsForHashPut(String key, String hashKey, Object value);
	
	/**
	 * @Title: opsForHashPutAll
	 * @author ye21st ye21st@gmail.com
	 * @date 2020年01月31日18:29:38
	 * @Description: 批量添加 Map的键值对有则覆盖,没有则添加
	 * @param key:key
	 * @param map:map集合
	 * @return void
	 */
	void opsForHashPutAll(String key, Map<String, Object> map);
	
	/**
	 * @Title: opsForHashPutIfAbsent
	 * @author ye21st ye21st@gmail.com
	 * @date 2020年01月31日18:29:44
	 * @Description: 添加 Map键值对, 不存在的时候才添加
	 * @param key:key
	 * @param hashKey:mapkey
	 * @param value:值
	 * @return void
	 */
	void opsForHashPutIfAbsent(String key, String hashKey, Object value);
	
	/**
	 * @Title: delete
	 * @author ye21st ye21st@gmail.com
	 * @date 2020年01月31日18:29:50
	 * @Description: 删除指定map中的key
	 * @param key：key
	 * @param hashKeys;mapkey集合
	 * @return void
	 */
	void opsForHashDelete(String key, String... hashKeys);
	
	/**
	 * @Title: delete
	 * @author ye21st ye21st@gmail.com
	 * @date 2020年01月31日18:29:56
	 * @Description: 根据key删除全部Map数据
	 * @param key：key
	 * @param hashKeys;mapkey集合
	 * @return void
	 */
	void opsForHashDeleteAll(String key);
	
	/**
	 * 存放str 类型
	 * 
	 * @param key
	 * @param obj
	 */
	void setStr(String key, String obj);
	
	void setStr(String key, String str, Long timeout);
	
	void sendMessage(String key, String str);
	
	/**
	 * 设置24小时 共享指数
	 * @param key
	 * @param value
	 */
	void setPointIndex(String key, double value);
	
	Object get(String key);
	
	void set(String key, Object obj, Long timeout, TimeUnit unit);
	
	void set(String key, Object obj);
	
	Long incr(String key);
	
	Long incr(String key, long increment);
	
	void expire(String redisKey, long timeSecond, TimeUnit seconds);
	
	void expireAt(String key, Date expire);
	
	/**
	 * @Title: lock
	 * @author ye21st ye21st@gmail.com
	 * @date 2020年01月31日18:30:02
	 * @Description: 加锁(默认超时时间 60 * 1000)
	 * @param lockKey：加锁key
	 * @return Long: 到期时间戳
	 */
	Long lock(String lockKey);
	
	/**
	 * @Title: unlock
	 * @author ye21st ye21st@gmail.com
	 * @date 2020年01月31日18:30:07
	 * @Description: 解锁
	 * @param lockKey：解锁key
	 * @param lockTimeout:超时时间戳
	 */
	void unlock(String lockKey, long lockTimeout);
	
	/**
	 * @Title: getRedisCurrtTime
	 * @author ye21st ye21st@gmail.com
	 * @date 2020年01月31日18:30:14
	 * @Description: 获取redis时间，避免多服务的时间不一致
	 * @return long
	 */
	long getRedisCurrtTime();
	
	/**
	 * @Title: getLockTimeout
	 * @author ye21st ye21st@gmail.com
	 * @date 2020年01月31日18:30:19
	 * @Description: 根据redis时间获得超时时间
	 * @return long
	 */
	long getLockTimeout();

}
