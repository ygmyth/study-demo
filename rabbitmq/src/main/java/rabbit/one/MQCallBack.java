package rabbit.one;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @description:
 * @author: yuang gang
 * @create: 2019-11-22
 **/
@Slf4j
public class MQCallBack implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback{
  @Override
  public void confirm(CorrelationData correlationData, boolean ack, String cause) {
    if (ack) {
      log.info("消息投递成功,ID为: {}", correlationData.getId());
      return;
    }
    log.error("消息投递失败,ID为: {},错误信息: {}", correlationData.getId(), cause);
  }

  /**
   * @return
   * @Author lly
   * @Description 消息消费发生异常时返回
   * @Date 2019-05-14 16:22
   * @Param [message, replyCode, replyText, exchange, routingKey]
   **/
  @Override
  public void returnedMessage(Message message, int replyCode, String replyText, String exchange,
      String routingKey) {
    log.info("消息发送失败id:{}", message.getMessageProperties().getCorrelationId());
    log.info("消息主体 message : {}", message);
    log.info("消息主体 message : {}", replyCode);
    log.info("描述：" + replyText);
    log.info("消息使用的交换器 exchange : {}", exchange);
    log.info("消息使用的路由键 routing : {}", routingKey);
  }
}
