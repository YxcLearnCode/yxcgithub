package work.try_01;

//import work.try_01.User_informationHelper;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.jsoup.Jsoup;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import work.util.CommonUtil;


public class MainActivity extends AppCompatActivity {
    EditText editText1,editText2;
    private Button btn;
    static String userName;
    private String psd;
    static String cookie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn1);
        editText1 = (EditText) findViewById(R.id.userID);
        editText1.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = editText1.getText().toString();//获取输入的用户名
                psd = ((EditText) findViewById(R.id.password)).getText().toString();//获取输入的密码
                login();
            }
        });
    }

    public void login() {
        final RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
        String url = "http://class.sise.com.cn:7001/sise/login_check.jsp";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {//这里是返回正确反馈的接口（只要请求成功反馈的数据都这这里）
                Log.d("Tag", response.toString());
                if (response.indexOf("index.jsp") != -1) {
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this,"用户名密码错误，请重新登录", Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(MainActivity.this,response, Toast.LENGTH_SHORT).show();//反馈给用户登录成功
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("AA", "post请求失败" + volleyError.getMessage(), volleyError);
            }
        }) {
            //这里是添加请求头的地方重写了getHeaders() 方法(发送设么请求头要根据自己实际开发需要设定)
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> header = new HashMap<String, String>();
                header.put("Host", "class.sise.com.cn:7001");
                header.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0");
                header.put("Content-Type", "application/x-www-form-urlencoded");
                header.put("Referer", "http://class.sise.com.cn:7001/sise/");
                return header;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                String hidden[] = CommonUtil.getHiddenMap();
                map.put(hidden[0], hidden[1]);
                map.put("username", userName);
                map.put("password", psd);
                return map;
            }

            protected Response<String> parseNetworkResponse(
                    NetworkResponse response) {
                try {
                    Map<String, String> responseHeaders = response.headers;
                    String rawCookies = responseHeaders.get("Set-Cookie");
                    cookie = rawCookies.split(";")[0];
                    System.out.println(cookie);
                    String dataString = new String(response.data, "GB2312");
                    return Response.success(dataString, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                }
            }
        };
//        StringRequest stringRequest = new StringRequest("http://class.sise.com.cn:7001/sise/module/student_schedular/student_schedular.jsp",
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.e("TAG", response);
//                        Toast.makeText(MainActivity.this,response, Toast.LENGTH_LONG).show();
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("TAG", error.getMessage(), error);
//            }
//        }) {
//            @Override
//            public Map<String, String> getHeaders() {
//                HashMap<String, String> header = new HashMap<String, String>();
//                header.put("Cookie", cookie);
//                return header;
//            }
//        };
//        mQueue.add(stringRequest);
        mQueue.add(request);
    }

}

