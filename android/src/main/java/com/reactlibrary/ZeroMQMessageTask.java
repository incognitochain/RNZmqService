package com.reactlibrary;

import android.os.AsyncTask;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;


import org.zeromq.ZMQ;

public class ZeroMQMessageTask extends AsyncTask<String, Void, String> {
    private final Handler uiThreadHandler;
    private final String address;

    public ZeroMQMessageTask(Handler uiThreadHandler, String address) {
        this.uiThreadHandler = uiThreadHandler;
        this.address = address;
    }

//    public ZeroMQMessageTask(Handler uiThreadHandler) {
//        this.uiThreadHandler = uiThreadHandler;
//    }

    @Override
    protected String doInBackground(String... params) {

        String data = params != null && params.length > 0 ? params[0] : "";
        if (!TextUtils.isEmpty(data)) {
            ZMQ.Context context = ZMQ.context(1);
            ZMQ.Socket socket = context.socket(ZMQ.REQ);
            socket.connect(address);

            socket.send(data.getBytes(), 0);
            String result = new String(socket.recv(0));

            socket.close();
            context.term();

            return result;
        }
        return "";
    }

    @Override
    protected void onPostExecute(String result) {
        if (!TextUtils.isEmpty(result)) {
            Log.d("Test", result);
            uiThreadHandler.sendMessage(Util.bundledMessage(uiThreadHandler, result));
        }
    }
}
