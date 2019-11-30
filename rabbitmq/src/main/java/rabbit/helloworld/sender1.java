package rabbit.helloworld;

import com.google.common.collect.Maps;
import com.rabbitmq.client.*;
import java.io.IOException;
import java.util.Map;

public class sender1 {
    private final static String QUEUE_NAME = "hello";
    private final static String EXCHANGE_NAME = "exchange1";
    private final static String ROUTING_KEY1 = "routingKey1";
    private final static String ROUTING_KEY2 = "routingKey2";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("guest");
        factory.setPassword("guest");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // channel.exchangeDeclare(EXCHANGE_NAME,"direct",true);//声明可持久化交换器

        Map<String, Object> exArgs = Maps.newHashMap();
        exArgs.put("alternate-exchange", "alternate-exchange");

        Map<String, Object> queueArgs = Maps.newHashMap();
        queueArgs.put("x-message-ttl", 20000);
        queueArgs.put("x-dead-letter-exchange", "dead-letter-exchange");
        queueArgs.put("x-dead-letter-routing-key", "dead-routing-key");


        channel.exchangeDeclare("direct-exchange", "direct", true, false, exArgs);// 声明可持久化交换器
        channel.exchangeDeclare("alternate-exchange", "fanout", true);// 备份交换器
        channel.exchangeDeclare("dead-letter-exchange", "direct", true);// 备份交换器


        channel.queueDeclare("queue1", true, false, false, queueArgs);// 声明非持久化队列
        channel.queueBind("queue1", "direct-exchange", ROUTING_KEY1);

        channel.queueDeclare("queue2", false, false, false, null);// 声明可持久化队列
        channel.queueBind("queue2", "alternate-exchange", "xxxx");// 绑定队列到交换器并指定路由键

        channel.queueDeclare("queueDLX", false, false, false, null);
        channel.queueBind("queueDLX", "dead-letter-exchange", "dead-routing-key");

        byte[] message = "hello world".getBytes();
        channel.basicPublish("ex1", ROUTING_KEY1,
                new AMQP.BasicProperties.Builder().deliveryMode(2).expiration("10000").build(), message);

        /*
         * recvPull(connection); recvPush(connection);
         */
    }
}