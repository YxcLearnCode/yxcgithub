package work.try_01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;


import com.android.volley.DefaultRetryPolicy;
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

import work.util.SpecialAdapter;

public class Offer_courses extends AppCompatActivity {
    String cookie;
    String url;
    private final String year = "year";
    private final String team = "team";
    private final String sushe = "sushe";
    private final String date = "date";
    private final String count = "count";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_courses);
        cookie = MainActivity.cookie;
        offer_courses();
    }

    private void offer_courses() {
        RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
        url = Main2Activity.url[6];
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String[][] aaa = parseHtml(response);
                        /*for(int i=0;i<aaa.length;i++){
                            for(int j=0;j<aaa[i].length;j++){
                                System.out.print(aaa[i][j]);
                            }
                            System.out.println();
                        }*/

                        ListView listView = (ListView) findViewById(R.id.ListView_offer_courses);
                        ArrayList<HashMap<String,String>> mylistArray = new ArrayList <HashMap<String,String>>();
                        HashMap<String, String> map1 = new HashMap<String, String>();
                        map1.put(year, "课程代码");
                        map1.put(team, "课程名称");
                        map1.put(sushe, "教学承担系");
                        map1.put(date, "学分");
                        map1.put(count, "考核方式");



                        mylistArray.add(map1);
                        for(int i=0; i<aaa.length; i++){
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put(year, aaa[i][0]);
                            map.put(team, aaa[i][1]);
                            map.put(sushe, aaa[i][2]);
                            map.put(date, aaa[i][3]);
                            map.put(count, aaa[i][4]);
                            mylistArray.add(map);
                        }

                        //生成适配器，数组-->>ListItem
                        SimpleAdapter mSchedule = new SpecialAdapter(
                                Offer_courses.this,
                                mylistArray,    //  数据来源
                                R.layout.mistake_items,   // ListItem的XML实现
                                new String[] {year,team,sushe,date,count},   // 动态数组与ListItem对应的子项
                                new int[] {R.id.year,R.id.team,R.id.sushe,R.id.date,R.id.count}    // attendance_items的XML文件里面的TextView ID
                        );
                        listView.setAdapter(mSchedule);
                        //Toast.makeText(Punishment.this,response.toString(), Toast.LENGTH_LONG).show();//测试用的
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

    //解析html
    public static String[][] parseHtml(String html){
        int i=0;
        int j=0;
        String[][] offer_courses =new String[2309][5];
        //将String转换成Document
        Document document = Jsoup.parse(html);
        String nbsp=Jsoup.parse(" ").text().trim();
        Elements elements = document.getElementsByAttributeValue("class","tablebody");
        for(Element element:elements){
            String temp=element.text().trim();
            if(temp.equals(nbsp)){
                offer_courses[i][j]=null;
            }else{
                offer_courses[i][j]=temp;
            }
            if(j==4){
                i++;
                j=0;
            }else{
                j++;
            }
        }

        return offer_courses;
    }
}
