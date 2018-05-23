package io.rezoome.manager.amq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.rezoome.lib.json.JSON;
import io.rezoome.manager.property.PropertyEnum;
import io.rezoome.manager.provider.ManagerProvider;
import io.rezoome.manager.pushcommand.entity.PushCommandEntity;

public class AMQMessageHandlerImpl implements AMQMessageHandler, MessageListener {

  private final Logger LOG = LoggerFactory.getLogger("AGENT_LOG");

  private static class Singleton {
    private static final AMQMessageHandler instance = new AMQMessageHandlerImpl();
  }

  public static AMQMessageHandler getInstance() {
    return Singleton.instance;
  }

  @Override
  public void onMessage(Message message) {
    // TODO Auto-generated method stub
    try {
      TextMessage consumerTextMessage = (TextMessage) message;
      LOG.debug("AMQMessage : {}", consumerTextMessage.getText());

      // AMQ key field RSA Decrypt
      String privateKey = ManagerProvider.property().getProperty(PropertyEnum.CERT_NAME);

      AMQMessageCryptoEntity amqCryptoEntity = new AMQMessageCryptoEntity();
      amqCryptoEntity = JSON.fromJson(consumerTextMessage.getText(), AMQMessageCryptoEntity.class);

      // AMQ User basic message AES Decrypt
      String clientKey = amqCryptoEntity.getKey();
      String clientIv = amqCryptoEntity.getIv();
      String amqMessage = amqCryptoEntity.getMsg();
      clientKey = ManagerProvider.crypto().decryptRSA(clientKey, privateKey);
      amqMessage = ManagerProvider.crypto().decryptAES(amqMessage, clientKey, clientIv);

      AMQMessageEntity amqEntity = new AMQMessageEntity();
      amqEntity = JSON.fromJson(amqMessage, AMQMessageEntity.class);
      // amqEntity = JSON.fromJson(consumerTextMessage.getText(), AMQMessageEntity.class);
      AMQMessageHandlerImpl.getInstance().handleMessage(amqEntity);
    } catch (NullPointerException ne) {
      // TODO Auto-generated catch block
      ne.printStackTrace();
    } catch (JMSException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public boolean handleMessage(AMQMessageEntity msg) {
    // TODO Auto-generated method stub
    try {
      PushCommandEntity pcEntity = msg.getCommand();
      pcEntity.setMid(msg.getMid());
      pcEntity.setSid(msg.getSid());
      pcEntity.setCmd(msg.getCmd());
      LOG.debug("PushEntity : {}", pcEntity);
      ManagerProvider.pushcommand().invokeCommand(pcEntity);
    } catch (RuntimeException re) {
      re.printStackTrace();
      // return false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    } finally {

    }
    return true;
  }
}
