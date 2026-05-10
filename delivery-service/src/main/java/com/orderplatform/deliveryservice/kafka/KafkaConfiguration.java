package com.orderplatform.deliveryservice.config;

import com.orderplatform.commonlibs.kafka.OrderPaidEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Map;

@Configuration
public class KafkaConfiguration {

    @Bean
    public ConsumerFactory<Long, OrderPaidEvent> orderPaidEventConsumerFactory(
            KafkaProperties kafkaProperties
    ) {
        Map<String, Object> props = kafkaProperties.buildConsumerProperties(null);

        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.orderplatform.commonlibs.kafka");

        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Long, OrderPaidEvent>>
    orderPaidEventListenerFactory(
            ConsumerFactory<Long, OrderPaidEvent> orderPaidEventConsumerFactory
    ) {
        ConcurrentKafkaListenerContainerFactory<Long, OrderPaidEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(orderPaidEventConsumerFactory);
        factory.setBatchListener(false);

        return factory;
    }
}