package id.co.ist.los.gateway.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.Collection;
import java.util.Set;

@Service
@Slf4j
public class RedisService {
	
	private final ValueOperations<String, Object> valueOperations;
	private final RedisTemplate<String, Object> redisTemplate;
	
	@Autowired
	public RedisService(ValueOperations<String, Object> valueOperations, RedisTemplate<String, Object> redisTemplate) {
		this.valueOperations = valueOperations;
		this.redisTemplate = redisTemplate;
	}

	@SuppressWarnings("unchecked")
	public <T> T getData(String key) {
		return (T) valueOperations.get(key);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getData(String key, Duration expired) {
		return (T) valueOperations.getAndExpire(key, expired);
	}
	
	public <T> T getDataFromJsonString(String key, Class<T> clazz) {
		String value = getData(key);
		if (StringUtils.isEmpty(value)) {
			return null;
		}
		
		return JsonUtils.stringJsonAsObject(value, clazz);
	}
	
	public <T> T getDataFromJsonString(String key, Class<T> clazz, Duration expired) {
		String value = getData(key, expired);
		if (StringUtils.isEmpty(value)) {
			return null;
		}
		
		return JsonUtils.stringJsonAsObject(value, clazz);
	}
	
	public void setData(String key, Object value) {
		valueOperations.set(key, value);
	}
	
	public void setData(String key, Object value, Duration expired) {
		valueOperations.set(key, value, expired);
	}
	
	public void setDataAsJsonString(String key, Object value){
		valueOperations.set(key, JsonUtils.objectAsStringJson(value));
	}
	
	public void setDataAsJsonString(String key, Object value, Duration expired){
		valueOperations.set(key, JsonUtils.objectAsStringJson(value), expired);
	}
	
	public void setDataIfAbsent(String key, Object value) {
		valueOperations.setIfAbsent(key, value);
	}
	
	public void setDataIfAbsent(String key, Object value, Duration expired) {
		valueOperations.setIfAbsent(key, value, expired);
	}
	
	public void setDataIfPresent(String key, Object value) {
		valueOperations.setIfPresent(key, value);
	}
	
	public void setDataIfPresent(String key, Object value, Duration expired) {
		valueOperations.setIfPresent(key, value, expired);
	}
	
	public Set<String> getAllKeys(String pattern) {
		return redisTemplate.keys(pattern);
	}
	
	public Boolean deleteKey(String key) {
		return redisTemplate.delete(key);
	}
	
	public Long deleteKeys(String pattern) {
		Set<String> keys = getAllKeys(pattern);
		return redisTemplate.delete(keys);
	}
	
	public Long deleteKeys(Collection<String> keys) {
		return redisTemplate.delete(keys);
	}
	
	
}
