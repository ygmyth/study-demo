package rabbit.helloworld;

import com.rabbitmq.client.*;
import com.sun.javafx.font.PrismFontStrike;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.util.Arrays;

/**
 * @description:
 * @author: yuang gang
 * @create: 2019-04-03 17:36
 **/
public class Send {
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
        Channel channel=connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"direct",true);//声明可持久化交换器

        channel.queueDeclare(QUEUE_NAME, true, false, false, null);//声明可持久化队列
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME,   ROUTING_KEY1);//绑定队列到交换器并指定路由键

        channel.queueDeclare("queue2", false, false, false, null);//声明非持久化队列
        channel.queueBind("queue2", EXCHANGE_NAME, ROUTING_KEY1);

        byte[] message = "hello world".getBytes();
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY1, new AMQP.BasicProperties.Builder()
                .deliveryMode(2).build(), message);
        recvPush(connection);
        recvPull(connection);

    }

    public static void recvPush(Connection connection) throws Exception {
        Channel channel=connection.createChannel();
        channel.basicConsume(QUEUE_NAME, false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String routingkey=envelope.getRoutingKey();
                long deliveryTag=envelope.getDeliveryTag();
                String value=new String(body);
                System.out.println(value);
                //通知broker消息被确认
                //channel.basicAck(deliveryTag, false);
            }
        });
    }

    public static void recvPull(Connection connection) throws Exception {
        Channel channel=connection.createChannel();
        GetResponse response = channel.basicGet(QUEUE_NAME, false);
        System.out.println(Arrays.toString(response.getBody()));
        System.out.println(response.getEnvelope());
        channel.basicAck(response.getEnvelope().getDeliveryTag(),false);
    }
}
