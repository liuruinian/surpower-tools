package io.github.liuruinian.redis.prefix;

public interface KeyPrefix {
		
	int expireSeconds();
	
	String getPrefix();
	
}
