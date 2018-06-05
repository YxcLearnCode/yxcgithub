package work.try_01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Scoure extends AppCompatActivity {
    String cookie;
    static String url= Main2Activity.url[4];
    public static String mix_url[];
    public int length;
    public int length2;
    public static Document document2;
    public static Vector<String> CMT=new Vector<String>();
    public String url1="http://class.sise.com.cn:7001/sise/module/commonresult/";
    static RequestQueue mQueue;
    StringRequest req;
           String response;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        cookie=MainActivity.cookie;
        mQueue = Volley.newRequestQueue(getApplicationContext());
        get_score();
//        System.out.print("Main:"+mix_url[0]);
    }
    private void get_score(){

        final StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
//                        Log.v("tag",response);
                          parseHtml(response);
                        System.out.println(CMT);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> header = new HashMap<String, String>();
                header.put("Cookie", cookie);
                return header;
            }
        };

        mQueue.add(stringRequest);

    }
    /**

     */



    //解析html
    public void parseHtml(final String respond){
//            String score_url=new String[];
            //将String转换成Document
        Document document= Jsoup.parse(respond);
        Element Course;
        Elements elements=document.getElementsByTag("tbody");
        length=elements.get(5).getElementsByTag("tr").size();
        Elements elements2=document.select("a[href]");
        length2=elements2.size();
        for(int i=2,u=0;i<length&&u<length2;i++,u++){
            Course=(Element) elements.get(5).childNode(i);
            CMT.add(Course.text());
            url1+=elements2.get(u).attr("href");
            System.out.println(url1);
            StringRequest request = new StringRequest(url1, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {//这里是返回正确反馈的接口（只要请求成功反馈的数据都这这里）
                   // Log.d("Tag", response.toString());
                    System.out.print("返回结果"+response);
                    document2= Jsoup.parse(response);
                    System.out.print("返回结果doc"+document2);

                }
            }, new Response.ErrorListener() {
                public void onErrorResponse(VolleyError volleyError) {
                    Log.e("AA", "post请求失败" + volleyError.getMessage(), volleyError);
                }
            }){
                @Override
                public Map<String, String> getHeaders() {
                    HashMap<String, String> header = new HashMap<String, String>();
                    header.put("Cookie", cookie);
                    return header;
                }
            };
            Elements elements3=document2.getElementsByTag("tbody");
            saveDetail(elements3, 4, 0, 0, 2, 3);
            url1="http://class.sise.com.cn:7001/sise/module/commonresult/";
            mQueue.add(request);
        }

    }
    public static Vector<String> saveDetail(Elements elements, int t, int k, int l, int m, int n) {
        Element Course;
        for(int i=k;i<=l;i++){
            for (int j = m; j <=n; j++) {
                Course=(Element) elements.get(t).childNode(i).childNode(j);
                CMT.add(Course.text());
            }
        }
        return CMT;
    }

}
