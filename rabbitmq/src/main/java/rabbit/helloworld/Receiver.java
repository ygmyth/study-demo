package rabbit.helloworld;

import com.google.common.collect.Maps;
import com.rabbitmq.client.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * @description:
 * @author: yuang gang
 * @create: 2019-04-03 17:36
 **/
public class Receiver {

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
    channel.exchangeDeclare("ex1", "direct", true, false, exArgs);// 声明可持久化交换器
    channel.exchangeDeclare("alternate-exchange", "fanout", true);// 备份交换器
    channel.exchangeDeclare("dead-letter-exchange", "direct", true);// 备份交换器

    Map<String, Object> queueArgs = Maps.newHashMap();
    queueArgs.put("x-message-ttl", 20000);
    queueArgs.put("x-dead-letter-exchange", "dead-letter-exchange");
    queueArgs.put("x-dead-letter-routing-key", "dead-routing-key");
    channel.queueDeclare("queue1", true, false, false, queueArgs);// 声明非持久化队列
    channel.queueBind("queue1", "ex1", ROUTING_KEY1);

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

  public static void recvPush(Connection connection) throws Exception {
    Channel channel = connection.createChannel();
    channel.basicConsume("queue1", false, new DefaultConsumer(channel) {
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope,
          AMQP.BasicProperties properties,
          byte[] body) throws IOException {
        String routingkey = envelope.getRoutingKey();
        long deliveryTag = envelope.getDeliveryTag();
        String value = new String(body);
        System.out.println("push message:" + value);
        // 通知broker消息被确认
        channel.basicAck(deliveryTag, false);
      }
    });
  }

  public static void recvPull(Connection connection) throws Exception {
    Channel channel = connection.createChannel();
    GetResponse response = channel.basicGet("queue2", false);
    System.out.println("pull message:" + Arrays.toString(response.getBody()));
    System.out.println("envelope:" + response.getEnvelope());
    channel.basicAck(response.getEnvelope().getDeliveryTag(), false);
    // channel.basicReject(response.getEnvelope().getDeliveryTag(),false);
  }
}
