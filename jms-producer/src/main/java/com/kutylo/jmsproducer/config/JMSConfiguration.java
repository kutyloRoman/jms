package com.kutylo.jmsproducer.config;

import com.kutylo.jmsproducer.model.Order;
import com.kutylo.jmsproducer.model.OrderDetail;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.transaction.PlatformTransactionManager;

import javax.jms.ConnectionFactory;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JMSConfiguration {

  @Value("${spring.activemq.broker-url}")
  String brokerUrl;

  @Value("${spring.activemq.user}")
  String userName;

  @Value("${spring.activemq.password}")
  String password;

  @Bean
  public ConnectionFactory connectionFactory() {
    ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
    connectionFactory.setBrokerURL(brokerUrl);
    connectionFactory.setUserName(userName);
    connectionFactory.setPassword(password);
    return connectionFactory;
  }

  @Bean
  public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
                                                  DefaultJmsListenerContainerFactoryConfigurer configurer) {
    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
    configurer.configure(factory, connectionFactory);
    factory.setTransactionManager(jmsTransactionalManager());
    return factory;
  }

  @Bean
  public MessageConverter jacksonJmsMessageConverter() {
    MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();

    Map<String, Class<?>> typeIdMappings = new HashMap<String, Class<?>>();
    typeIdMappings.put("order", Order.class);
    typeIdMappings.put("order-detail", OrderDetail.class);

    converter.setTargetType(MessageType.TEXT);
    converter.setTypeIdPropertyName("_type");
    converter.setTypeIdMappings(typeIdMappings);
    return converter;
  }

  @Bean
  public JmsTemplate jmsTemplate() {
    JmsTemplate template = new JmsTemplate();
    template.setMessageConverter(jacksonJmsMessageConverter());
    template.setConnectionFactory(connectionFactory());
    template.setSessionTransacted(true);
    template.setPubSubDomain(true);
    return template;
  }

  @Bean
  public PlatformTransactionManager jmsTransactionalManager(){
    return new JmsTransactionManager(connectionFactory());
  }
}
