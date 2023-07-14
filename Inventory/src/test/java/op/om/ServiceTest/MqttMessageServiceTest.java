package op.om.ServiceTest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.hivemq.client.mqtt.mqtt3.exceptions.Mqtt3MessageException;
import com.om.app.services.MqttMessageService;

class MqttMessageServiceTest {

  @Mock
  private IMqttClient mqttClient;

  @Captor
  private ArgumentCaptor<IMqttMessageListener> messageListenerCaptor;

  @InjectMocks
  private MqttMessageService mqttMessageService;

  @BeforeEach
  void setUp() throws MqttException {
    MockitoAnnotations.initMocks(this);

    // Mock the IMqttClient behavior
    doAnswer(new Answer() {
      public Object answer(InvocationOnMock invocation) throws Exception {
        IMqttMessageListener listener = invocation.getArgument(1);
        listener.messageArrived("test/topic", new MqttMessage("Hello, MQTT!".getBytes()));
        return null;
      }
    }).when(mqttClient).subscribeWithResponse(anyString(), messageListenerCaptor.capture());
  }

  @Test
  void testPublish() throws Mqtt3MessageException, MqttException {
    String topic = "test/topic";
    String payload = "Hello, MQTT!";
    int qos = 1;
    boolean retained = true;

    mqttMessageService.publish(topic, payload, qos, retained);

    // Verify that the publish and disconnect methods were called
    verify(mqttClient, times(1)).publish(eq(topic), any(MqttMessage.class));
    verify(mqttClient, times(1)).disconnect();
  }

  @Test
  void testSubscribe() throws Exception {
    String topic = "test/topic";

    mqttMessageService.subscribe(topic);

    // Verify that the subscribeWithResponse method was called
    verify(mqttClient, times(1)).subscribeWithResponse(eq(topic), any(IMqttMessageListener.class));
    
    // Verify the received message
    IMqttMessageListener messageListener = messageListenerCaptor.getValue();
    messageListener.messageArrived(topic, new MqttMessage("Test Message".getBytes()));
    // Assert the behavior when a message is received
  }
}
