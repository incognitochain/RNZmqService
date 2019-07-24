
package com.reactlibrary;

import android.util.Log;
import android.widget.Toast;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.HashMap;
import java.util.Map;

public class RNZmqServiceModule extends ReactContextBaseJavaModule {

    private static final String DURATION_SHORT_KEY = "SHORT";
    private static final String DURATION_LONG_KEY = "LONG";
    //private final ZeroMQMessageTask zeroMQMessageTask;

    private void clientMessageReceived(String messageBody) {
        //textView.append(getTimeString() + " - client received: " + messageBody + "\n");
        Log.d("Test", " - client received: " + messageBody + "\n");

    }

    private final MessageListenerHandler clientMessageHandler = new MessageListenerHandler(
            new IMessageListener() {
                @Override
                public void messageReceived(String messageBody) {

                    clientMessageReceived(messageBody);

                }
            },
            Util.MESSAGE_PAYLOAD_KEY);


    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put(DURATION_SHORT_KEY, Toast.LENGTH_SHORT);
        constants.put(DURATION_LONG_KEY, Toast.LENGTH_LONG);
        return constants;
    }


    @ReactMethod
    public void sendData(String data) {
        //Toast.makeText(getReactApplicationContext(), message, duration).show();

        String address = "tcp://192.168.42.1:5004";
        ZeroMQMessageTask zeroMQMessageTask = new ZeroMQMessageTask(clientMessageHandler, address);
        zeroMQMessageTask.execute(data);

    }


    private final ReactApplicationContext reactContext;

    public RNZmqServiceModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNZmqService";
    }
}