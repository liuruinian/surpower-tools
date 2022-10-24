package io.github.liuruinian.redis.callback;

public interface RedisSubscribeCallback {
    void callback(String msg);
}
