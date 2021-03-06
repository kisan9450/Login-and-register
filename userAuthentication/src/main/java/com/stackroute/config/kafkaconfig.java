package com.stackroute.config;


import com.stackroute.domain.Userprofiledomain;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;


import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableKafka
public class kafkaconfig {


    @Bean
    public ConsumerFactory<String,Object> consumerFactory(){
        Map<String,Object> configs = new HashMap<>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"172.23.239.197:9092");
        configs.put(ConsumerConfig.GROUP_ID_CONFIG,"userconsumer");
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(configs);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,Object> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,Object> factory = new ConcurrentKafkaListenerContainerFactory<String,Object>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }



    @Bean
    public ConsumerFactory<String, Userprofiledomain> userConsumerFactory(){
        Map<String,Object> configs = new HashMap<>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"172.23.239.197:9092");
        configs.put(ConsumerConfig.GROUP_ID_CONFIG,"userconsumer");
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        //configs.put(JsonDeserializer.TRUSTED_PACKAGES, "*");


        return new DefaultKafkaConsumerFactory<>(configs,new StringDeserializer(),new org.springframework.kafka.support.serializer.JsonDeserializer<>((Userprofiledomain.class)));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Userprofiledomain> kafkaUserListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,Userprofiledomain> factory = new  ConcurrentKafkaListenerContainerFactory<String,Userprofiledomain>();
        factory.setConsumerFactory(userConsumerFactory());
        //  factory.setMessageConverter(new StringJsonMessageConverter());
        return factory;
    }
}

