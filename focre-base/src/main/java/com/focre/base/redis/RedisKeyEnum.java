package com.focre.base.redis;

/**
 * @ClassName: Abs
 * @Description:
 * @Author ye21st
 * @Date 2019/9/29 5:30 下午:01
 */
public enum RedisKeyEnum{

	TOKEN_VALID

	;

	public String getRedisKey() {
		return this.name().toLowerCase();
	}

	public static String getRedisKey(Object... objs) throws Exception {
		if (null == objs || objs.length == 0) {
			throw new Exception();
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < objs.length; i++){
			if (i == 0){
				sb.append(objs[i]);
			} else {
				sb.append("_").append(objs[i]);
			}
		}
		return sb.toString().toLowerCase();
	}

	/** Redis组合KEY */
	public String getRedisKeyBy(Object... objs) {
		if (null == objs || objs.length == 0) {
			return getRedisKey();
		}
		StringBuffer sb = new StringBuffer();
		for (Object item : objs) {
			sb.append("_").append(item);
		}
		return (this.name() + "_by" + sb.toString()).toLowerCase();
	}

	/** Redis业务锁记录KEY */
	public String getRedisLockKeyBy(Object... objs) {
		StringBuffer sb = new StringBuffer();
		for (Object item : objs) {
			sb.append("_").append(item);
		}
		return (this.name() + "_lock_by" + sb.toString()).toLowerCase();
	}

	/** Redis集合KEY */
	public String getRedisKeyList() {
		return this.name().toLowerCase() + "_list";
	}

	/** Redis集合KEY */
	public String getRedisKeyByList(Object... objs) {

		if (null == objs || objs.length == 0) {
			return getRedisKeyList();
		}

		StringBuffer sb = new StringBuffer();
		for (Object item : objs) {
			sb.append("_").append(item);
		}
		return (this.name() + "_by" + sb.toString() + "_list").toLowerCase();
	}
}
