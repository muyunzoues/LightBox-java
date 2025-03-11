package com.easylive.web.component;

import com.easylive.component.RedisComponent;
import com.easylive.entity.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class RedisKeyExpireationListener extends KeyExpirationEventMessageListener {
    @Resource
    private RedisComponent redisComponent;

    public RedisKeyExpireationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String key=message.toString();
        if(!key.startsWith(Constants.REDIS_KEY_VIDEO_PLAY_COUNT_ONLINE_PREFIX+Constants.REDIS_KEY_VIDEO_PLAY_COUNT_USER_PREFIX)){
            return;
        }
        Integer userKeyIndex=key.indexOf(Constants.REDIS_KEY_VIDEO_PLAY_COUNT_USER_PREFIX)+Constants.REDIS_KEY_VIDEO_PLAY_COUNT_USER_PREFIX.length();
        String fileId= key.substring(userKeyIndex,userKeyIndex+Constants.LENGTH_20);
        redisComponent.decrementPlayOnline(String.format(Constants.REDIS_KEY_VIDEO_PLAY_COUNT_ONLINE,fileId));
    }
}
