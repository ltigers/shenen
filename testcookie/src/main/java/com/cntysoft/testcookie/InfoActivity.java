package com.cntysoft.testcookie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class InfoActivity extends Activity {

    private TextView textView;
    private Button button;
    private String sessionid;
    private static final String url = "http://192.168.0.139/getinfo.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        button = (Button) findViewById(R.id.btn);
        Intent intent = getIntent();
        sessionid = intent.getStringExtra("sessionid");
        textView = (TextView) findViewById(R.id.hello);
        textView.setText(sessionid);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getInfo();
            }
        });
    }

    private boolean getInfo(){
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        List<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
        pairs.add(new BasicNameValuePair("sess_username","1"));
        pairs.add(new BasicNameValuePair("sess_sessionid",sessionid));
        try{
            httpPost.setEntity(new UrlEncodedFormEntity(pairs, HTTP.UTF_8));
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }

        try{
            HttpResponse httpResponse = defaultHttpClient.execute(httpPost);
            if(httpResponse.getStatusLine().getStatusCode() == 200){
                HttpEntity entity = httpResponse.getEntity();
                if(entity != null){
                    String info = EntityUtils.toString(entity);
                    textView.setText(info);
                    String flag = "";
                    JSONObject jsonObject =null;
                    try{
                        jsonObject = new JSONObject(info);
                        flag = jsonObject.getString("flag");
                        sessionid = jsonObject.getString("sessionid");
                    }catch(JSONException e){
                        e.printStackTrace();
                    }
                    if(flag.equals("success")){
                        return true;
                    }else{
                        return false;
                    }
                }else{
                    return false;
                }
            }
        }catch(ClientProtocolException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }
}
