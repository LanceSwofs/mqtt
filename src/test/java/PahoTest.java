import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.Test;
import paho.SampleAsyncCallBack;

public class PahoTest {
    @Test
    public void publishMQTT(){
        //Unique clientId
        actionMQTT("publish", "publish");
    }

    @Test
    public void subscribeMQTT(){
        //Unique clientId
        actionMQTT("subscribe", "subscribe");
    }

    public void actionMQTT(String action, String clientId){
        String topic        = "MQTT Examples";
        String content      = "Message from MqttPublishSample";
        int qos             = 2;
        String protocol = "tcp://";
        String broker 		= "localhost";
        int port 			= 61613;
        String url = protocol + broker + ":" + port;
        try {
            // Create an instance of this class
            SampleAsyncCallBack sampleClient = new SampleAsyncCallBack(url, clientId, true, false,"admin","password");

            // Perform the requested action
            if (action.equals("publish")) {
                sampleClient.publish(topic,qos,content.getBytes());
            } else if (action.equals("subscribe")) {
                sampleClient.subscribe(topic,qos);
            }
        } catch(MqttException me) {
            // Display full details of any exception that occurs
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
