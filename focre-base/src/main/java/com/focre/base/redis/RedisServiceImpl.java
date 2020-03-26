package com.focre.base.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

	// 日志对象
	private static final Logger log = LoggerFactory.getLogger(RedisServiceImpl.class);

	// 默认加锁超时时间
	private static final long LOCK_TIMEOUT = 60 * 1000;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	/**
	 * 删除缓存<br>
	 * 根据key精确匹配删除
	 * @param key
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void del(String... key) {
		if (key != null && key.length > 0) {
			if (key.length == 1) {
				redisTemplate.delete(key[0]);
			} else {
				redisTemplate.delete(CollectionUtils.arrayToList(key));
			}
		}
	}

	/**
	 * @Title: opsForListPushAll
	 * @author ye21st ye21st@gmail.com
	 * @date 2020年01月31日18:30:59
	 * @Description: 添加一个集合
	 * @param key:list key
	 * @param values: list集合
	 * @throws
	 */
	@Override
	public void opsForListPushAll(String key, List<Object> values) {
		opsForListRemoveAll(key);
		redisTemplate.opsForList().leftPushAll(key, values);
	}

	/**
	 * @Title: opsForListRemoveAll
	 * @author ye21st ye21st@gmail.com
	 * @date 2020年01月31日18:31:08
	 * @Description: 删除集合中的全部数据
	 * @param key ： list key
	 * @throws
	 */
	@Override
	public void opsForListRemoveAll(String key) {
		redisTemplate.opsForList().getOperations().delete(key);
	}

	/**
	 * @Title: opsForListKey
	 * @author ye21st ye21st@gmail.com
	 * @date 2020年01月31日18:31:14
	 * @Description: 根据key获取一个集合
	 * @param key:key
	 * @return List<Object>
	 * @throws
	 */
	@Override
	public List<Object> opsForListKey(String key) {
		return redisTemplate.opsForList().range(key, 0, -1);
	}

	/**
	 * @Title: opsForListRemove
	 * @author ye21st ye21st@gmail.com
	 * @date 2020年01月31日18:31:21
	 * @Description: 删除集合中的一个元素
	 * @param key:key
	 * @param value
	 * @throws
	 */
	@Override
	public void opsForListRemove(String key, Object value) {
		redisTemplate.opsForList().remove(key, 0, value);
	}

	/**
	 * @Title: opsForListLeftPush
	 * @author ye21st ye21st@gmail.com
	 * @date 2020年01月31日18:31:30
	 * @Description: 在集合前面添加元素
	 * @param key:key
	 * @param value:值
	 * @throws
	 */
	@Override
	public void opsForListLeftPush(String key, Object value) {
		redisTemplate.opsForList().leftPush(key, value);
	}

	/**
	 * @Title: opsForListRightPush
	 * @author ye21st ye21st@gmail.com
	 * @date 2020年01月31日18:31:42
	 * @Description: 在集合后面添加元素
	 * @param key:key
	 * @param value:值
	 * @throws
	 */
	@Override
	public void opsForListRightPush(String key, Object value) {
		redisTemplate.opsForList().rightPush(key, value);
	}

	/**
	 * @Title: opsForHashEntries
	 * @author ye21st ye21st@gmail.com
	 * @date 2020年01月31日18:31:48
	 * @Description: 获取 key下的 所有MAp
	 * @param key:key
	 * @return Map<Object,Object>
	 */
	@Override
	public Map<Object, Object> opsForHashEntries(String key) {
		return redisTemplate.opsForHash().entries(key);
	}

	/**
	 * @Title: opsForHashGet
	 * @author ye21st ye21st@gmail.com
	 * @date 2020年01月31日18:31:53
	 * @Description: 通过key、hashKey 获取一个值
	 * @param key:key
	 * @param hashKey:mapkey
	 * @return Object
	 * @throws
	 */
	@Override
	public Object opsForHashGet(String key, String hashKey) {
		return redisTemplate.opsForHash().get(key, hashKey);
	}

	/**
	 * @Title: opsForHashPut
	 * @author ye21st ye21st@gmail.com
	 * @date 2020年01月31日18:32:00
	 * @Description: 往Map中添加可以 value
	 * @param key:key
	 * @param hashKey:mapkey
	 * @param value:值
	 * @return void
	 */
	@Override
	public void opsForHashPut(String key, String hashKey, Object value) {
		redisTemplate.opsForHash().put(key, hashKey, value);
	}

	/**
	 * @Title: opsForHashPutAll
	 * @author ye21st ye21st@gmail.com
	 * @date 2020年01月31日18:32:07
	 * @Description: 批量添加 Map的键值对有则覆盖,没有则添加
	 * @param key:key
	 * @param map:map集合
	 * @return void
	 */
	@Override
	public void opsForHashPutAll(String key, Map<String, Object> map) {
		redisTemplate.opsForHash().putAll(key, map);
	}

	/**
	 * @Title: opsForHashPutIfAbsent
	 * @author ye21st ye21st@gmail.com
	 * @date 2020年01月31日18:32:19
	 * @Description: 添加 Map键值对, 不存在的时候才添加
	 * @param key:key
	 * @param hashKey:mapkey
	 * @param value:值
	 * @return void
	 */
	@Override
	public void opsForHashPutIfAbsent(String key, String hashKey, Object value) {
		redisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
	}

	/**
	 * @Title: delete
	 * @author ye21st ye21st@gmail.com
	 * @date 2020年01月31日18:32:12
	 * @Description: 删除指定map中的key
	 * @param key：key
	 * @param hashKeys;mapkey集合
	 * @return void
	 */
	@Override
	public void opsForHashDelete(String key, String... hashKeys) {
		if (null == hashKeys || hashKeys.length == 0) {
			return;
		}
		for (String hashKey : hashKeys) {
			redisTemplate.opsForHash().delete(key, hashKey);
		}
	}

	/**
	 * @Title: delete
	 * @author ye21st ye21st@gmail.com
	 * @date 2020年01月31日18:32:26
	 * @Description: 根据key删除全部Map数据
	 * @param key：key
	 * @param hashKeys;mapkey集合
	 * @return void
	 */
	@Override
	public void opsForHashDeleteAll(String key) {
		redisTemplate.delete(key);
	}

	/**
	 * 存放str 类型
	 * 
	 * @param key
	 * @param obj
	 */
	@Override
	public void setStr(String key, String obj) {
		if (obj == null) {
			return;
		}
		redisTemplate.opsForValue().set(key, obj);
	}

	@Override
	public void setStr(String key, String str, Long timeout) {
		if (str == null) {
			return;
		}
		redisTemplate.opsForValue().set(key, str, timeout);
	}

	@Override
	public void sendMessage(String key, String str) {
		if (str == null) {
			return;
		}
		redisTemplate.opsForValue().set(key, str, 600, TimeUnit.SECONDS);
	}

	/**
	 * 设置24小时 共享指数
	 * 
	 * @param key
	 * @param value
	 */
	@Override
	public void setPointIndex(String key, double value) {
		redisTemplate.opsForValue().set(key, value + "", 24 * 3600, TimeUnit.SECONDS);
	}

	@Override
	public Object get(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	@Override
	public void set(String key, Object obj, Long timeout, TimeUnit unit) {
		if (obj == null) {
			return;
		}
		if (timeout != null) {
			redisTemplate.opsForValue().set(key, obj, timeout, unit);
		} else {
			redisTemplate.opsForValue().set(key, obj);
		}
	}

	@Override
	public void set(String key, Object obj) {
		if (obj == null) {
			return;
		}
		redisTemplate.opsForValue().set(key, obj);
	}

	@Override
	public Long incr(String key) {
		RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
		return entityIdCounter.getAndIncrement();
	}

	@Override
	public Long incr(String key, long increment) {
		RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
		return entityIdCounter.addAndGet(increment);
	}
	
	@Override
	public void expire(String key, long time, TimeUnit unit) {
		redisTemplate.expire(key, time, unit);
	}
	
	@Override
	public void expireAt(String key, Date expire) {
		redisTemplate.expireAt(key, expire);
	}
	
	

	/**
	 * @Title: lock
	 * @author ye21st ye21st@gmail.com
	 * @date 2020年01月31日18:32:33
	 * @Description: 加锁(默认超时时间 60 * 1000)
	 * @param lockKey：加锁key
	 * @return Long: 到期时间戳
	 */
	@Override
	public synchronized Long lock(String lockKey) {
		log.debug("{}====开始执行加锁", lockKey);
		// 锁定时间
		Long lockTimeout = getLockTimeout();
		// 可以成功设置,也就是key不存在(对应setnx命令)
		if (redisTemplate.opsForValue().setIfAbsent(lockKey, lockTimeout)) {
			log.debug("{}-加锁成功(key不存在)", lockKey);
			// 设置超时时间，释放内存
			redisTemplate.expire(lockKey, LOCK_TIMEOUT, TimeUnit.MILLISECONDS);
			// 返回 超时时间
			return lockTimeout;
		}

		// 获取超时时间
		Long currtLockTimeout = (Long) redisTemplate.opsForValue().get(lockKey);
		// 如果超时值是为空或者小于redis时间，则表示已经失效，
		if (null == currtLockTimeout || currtLockTimeout < getRedisCurrtTime()) {
			// 获取上一个锁到期时间,并设置现在的锁到期时间
			Long oldLockTimeout = (Long) redisTemplate.opsForValue().getAndSet(lockKey, lockTimeout);
			// 如过获取到的超时时间不为空，并且和设置的超时时间一样则，则表示获得所成功
			// 多个线程都执行都到了这里，可以保证只有一个可以成功获得锁
			if (oldLockTimeout != null && oldLockTimeout.equals(currtLockTimeout)) {
				log.debug("{}-加锁成功(通过对比超时时间)", lockKey);
				// 设置超时时间，释放内存
				redisTemplate.expire(lockKey, LOCK_TIMEOUT, TimeUnit.MILLISECONDS);
				// 返回加锁时间
				return lockTimeout;
			}
		}

		try {
			log.debug("{}-等待再次加锁，休眠200毫秒", lockKey);
			// 设置睡眠100毫秒
			TimeUnit.MILLISECONDS.sleep(200);
		} catch (InterruptedException e) {
			log.debug("{}--执行加锁休眠异常：{}", lockKey, e);
			// 返回加锁时间
			return null;
		}

		log.debug("{}--执行加锁失败", lockKey);
		// 返回加锁时间
		return null;
	}

	/**
	 * @Title: unlock
	 * @author ye21st ye21st@gmail.com
	 * @date 2020年01月31日18:32:42
	 * @Description: 解锁
	 * @param lockKey：解锁key
	 * @param lockTimeout:超时时间戳
	 */
	@Override
	public void unlock(String lockKey, long lockTimeout) {
		log.debug("{}-{}-执行解锁", lockKey, lockTimeout);

		// 获取超时时间
		Long oldLockTimeout = (Long) redisTemplate.opsForValue().get(lockKey); // redis里的时间

		// 如果是加锁者 则删除锁 如果不是则等待自动过期 重新竞争加锁
		if (oldLockTimeout != null && oldLockTimeout.equals(lockTimeout)) {
			// 删除键
			redisTemplate.opsForValue().getOperations().delete(lockKey);
			log.debug("{}-{}-解锁成功", lockKey, lockTimeout);
		} else {
			log.debug("{}-{}-锁已过期", lockKey, lockTimeout);
		}

	}

	/**
	 * @Title: getRedisCurrtTime
	 * @author ye21st ye21st@gmail.com
	 * @date 2020年01月31日18:32:49
	 * @Description: 获取redis时间，避免多服务的时间不一致
	 * @return long
	 */
	@Override
	public long getRedisCurrtTime() {
		return redisTemplate.execute(new RedisCallback<Long>() {

			@Override
			public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
				return redisConnection.time();
			}
		});
	}

	/**
	 * @Title: getLockTimeout
	 * @author ye21st ye21st@gmail.com
	 * @date 2020年01月31日18:32:56
	 * @Description: 根据redis时间获得超时时间
	 * @return long
	 */
	@Override
	public long getLockTimeout() {
		return getRedisCurrtTime() + LOCK_TIMEOUT;
	}
}
