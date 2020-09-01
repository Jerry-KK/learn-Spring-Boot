package cn.lethekk.chapter7;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableCaching
@MapperScan(
        basePackages = "cn.lethekk.chapter7",
        annotationClass = Repository.class
)
public class Chapter7Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter7Application.class, args);
    }

    @Autowired
    private RedisTemplate redisTemplate = null;

    //定义自定义后初始化的方法
    @PostConstruct
    public void init(){
        initRedisTemplate();
    }

    //设置RedisTemplate的序列化器
    private void initRedisTemplate(){
        RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
    }

    //Redis连接工厂
    @Autowired
    private RedisConnectionFactory connectionFactory = null;

    //Redis消息监听器
    @Autowired
    private MessageListener redisMsgListener = null;

    //任务池
    private ThreadPoolTaskScheduler taskScheduler = null;

    /*
    * 创建任务池，运行线程等待处理的Redis消息*/
    @Bean
    public ThreadPoolTaskScheduler initTaskScheduler(){
        if(taskScheduler != null){
            return taskScheduler;
        }
        taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(20);
        return taskScheduler;
    }

    /*
    * 定义Redis的监听内容*/
    @Bean
    public RedisMessageListenerContainer initRedisContainer(){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        //Redis连接工厂
        container.setConnectionFactory(connectionFactory);
        //设置运行任务池
        container.setTaskExecutor(initTaskScheduler());
        //定义监听渠道
        ChannelTopic topic1 = new ChannelTopic("topic1");
        //使用监听器监听Redis的消息
        container.addMessageListener(redisMsgListener,topic1);
        return container;
    }
}
