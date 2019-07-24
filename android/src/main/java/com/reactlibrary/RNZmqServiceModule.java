
package com.reactlibrary;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.Toast;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class RNZmqServiceModule extends ReactContextBaseJavaModule {
    private static final String TAG = "RNZmqServiceModule";
    private static final String DURATION_SHORT_KEY = "SHORT";
    private static final String DURATION_LONG_KEY = "LONG";
    //private final ZeroMQMessageTask zeroMQMessageTask;
    private String address;

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


//    @ReactMethod
//    public void sendData(String data) {
//        //Toast.makeText(getReactApplicationContext(), message, duration).show();
//        Log.d(TAG, "sendData: ");
//        String address = "tcp://192.168.42.1:5004";
//        ZeroMQMessageTask zeroMQMessageTask = new ZeroMQMessageTask(clientMessageHandler, address);
//        zeroMQMessageTask.execute(data);
//
//    }

    @ReactMethod
    public void sendData(String data, final Promise promise) {
        //Toast.makeText(getReactApplicationContext(), message, duration).show();

        String address = String.format("tcp://%s:5004", this.address);
        Log.d(TAG, "sendData: " + data + ", address = " + address);
        ZeroMQMessageTask zeroMQMessageTask = new ZeroMQMessageTask(new MessageListenerHandler(
                new IMessageListener() {
                    @Override
                    public void messageReceived(String messageBody) {

                        Log.d(TAG, " messageReceived received: " + messageBody + "\n");
                        if (promise != null) {
                            promise.resolve(messageBody);
                        }
                    }
                },
                Util.MESSAGE_PAYLOAD_KEY), address);
        zeroMQMessageTask.execute(data);

    }


    private final ReactApplicationContext reactContext;

    public RNZmqServiceModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        WifiManager wifiManager = (WifiManager) reactContext.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

//        final String address = Formatter.formatIpAddress(wifiManager.getDhcpInfo().gateway);// gateway -
//
//        InetAddress serverIP = null;
//        try {
//            serverIP = InetAddress.getByName(address);
//
//        } catch (Exception e) {
//
//        }


//        this.address = serverIP.toString() + "";
        this.address = intToInetAddress(wifiManager.getDhcpInfo().serverAddress).getHostAddress();
        Log.d(TAG, "RNZmqServiceModule:  address = " + this.address);
    }

    public InetAddress intToInetAddress(int hostAddress) {
        byte[] addressBytes = {(byte) (0xff & hostAddress),
                (byte) (0xff & (hostAddress >> 8)),
                (byte) (0xff & (hostAddress >> 16)),
                (byte) (0xff & (hostAddress >> 24))};

        try {
            return InetAddress.getByAddress(addressBytes);
        } catch (UnknownHostException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String getName() {
        return "RNZmqService";
    }
}