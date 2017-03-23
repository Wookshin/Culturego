package com.example.wooks.myremoteweb;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Wooks on 2017-02-25.
 */

public class NetWorkAsyncTask extends AsyncTask<LatLon, Void, String> {
    final static String TAG = "NetWorkAsyncTask";
    Context mContext = null;
    String mAddr;
    ProgressDialog dialog = null;

    public NetWorkAsyncTask(Context c, String a) {
        mContext = c;
        mAddr = a;
    }

    @Override
    protected void onPreExecute() {
        Log.i(TAG, "onPreExecute()");

        dialog = new ProgressDialog(mContext);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setTitle("Dialog");
        dialog.setMessage("down...");
        dialog.show();
    }

    @Override
    protected String doInBackground(LatLon... params) {
        Log.i(TAG, "doInBackground()");
        String data = "";
//        StringBuffer sb = new StringBuffer();
//        InputStream is = null;
//        InputStreamReader isr = null;
//        BufferedReader br = null;

        try {
            URL url = new URL(mAddr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout( 10000 /*milliseconds*/ );
            connection.setConnectTimeout(15000);
            connection.setUseCaches(false);
            connection.setDefaultUseCaches(false);
            connection.setDoInput(true);                         // 서버에서 읽기 모드 지정
            connection.setDoOutput(true);                       // 서버로 쓰기 모드 지정
            connection.setRequestMethod("POST");         // 전송 방식은 POST

            // 서버에게 웹에서 <Form>으로 값이 넘어온 것과 같은 방식으로 처리하라는 걸 알려준다
            //connection.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            try {
                JSONObject parent = new JSONObject();
                JSONObject child = new JSONObject();
                child.put("latitude", params[0].getLat());
                child.put("longitude", params[0].getLon());
                parent.put("sinabro",child);
                Log.d("output", parent.toString(2));

                OutputStream os = connection.getOutputStream(); // 서버로 보내기 위한 출력 스트림
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8")); // UTF-8로 전송
                bw.write(parent.toString()); // 매개변수 전송
                bw.flush();
                bw.close();
                os.close();

                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) { // 연결에 성공한 경우
                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream())); // 서버의 응답을 읽기 위한 입력 스트림

                    while ((line = br.readLine()) != null) // 서버의 응답을 읽어옴
                        data += line;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return data;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        Log.i(TAG, "onProgressUpdate()");
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Log.e("TAG", result);
        dialog.dismiss();
    }

    @Override
    protected void onCancelled() {
        Log.i(TAG, "onCancelled()");
        super.onCancelled();
    }
}
