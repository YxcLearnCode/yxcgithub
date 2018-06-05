package work.try_01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

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


public class Examination_time extends AppCompatActivity {
    String cookie;
    String[][] att;
    String url;
    private final String id = "id";
    private final String name = "name";
    private final String date = "date";
    private final String time = "time";
    private final String classroom = "classroom";
    private final String class_name = "class_name";
    private final String seat = "seat";
    private final String zhuangtai = "zhuangtai";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examination_time);
        cookie = MainActivity.cookie;
        attendance();
    }

    private void attendance(){
        RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
        url = Main2Activity.url[3];
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        //Log.e("TAG", response);
                        att= parseHtml(response);

                        ListView listView = (ListView) findViewById(R.id.ListView01);
                        ArrayList<HashMap<String,String>> mylistArray = new ArrayList <HashMap<String,String>>();
                        HashMap<String, String> map1 = new HashMap<String, String>();
                        map1.put(id, "课程代码");
                        map1.put(name, "课程名称");
                        map1.put(date, "考试日期");
                        map1.put(time, "考试时间");
                        map1.put(classroom, "考场编码");
                        map1.put(class_name, "考场名称");
                        map1.put(seat, "考试座位");
                        map1.put(zhuangtai, "考试状态");
                        mylistArray.add(map1);
                        for(int i=0; i<att.length; i++){
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put(id, att[i][0]);
                            map.put(name, att[i][1]);
                            map.put(date, att[i][2]);
                            map.put(time, att[i][3]);
                            map.put(classroom, att[i][4]);
                            map.put(class_name, att[i][5]);
                            map.put(seat, att[i][6]);
                            map.put(zhuangtai, att[i][7]);
                            mylistArray.add(map);
                        }

                        //生成适配器，数组-->>ListItem
                        SimpleAdapter mSchedule = new SpecialAdapter(
                                Examination_time.this,
                                mylistArray,    //  数据来源
                                R.layout.examination_items,   // ListItem的XML实现
                                new String[] {id,name,date,time,classroom,class_name,seat,zhuangtai},   // 动态数组与ListItem对应的子项
                                new int[] {R.id.id,R.id.name,R.id.date,R.id.time,R.id.classroom,R.id.class_name,R.id.seat,R.id.zhuangtai}    // attendance_items的XML文件里面的TextView ID
                        );
                        listView.setAdapter(mSchedule);

                        /*for(int i=0;i<att.length;i++){
                            for(int j=0;j<att[i].length;j++){
                                System.out.print(att[i][j]);
                            }
                            System.out.println();
                        }*/
                        /*for(int i = 0;i<att.length;i++){
                            for(int j = 0;j<att[i].length;j++){
                                TextView button = (TextView) findViewById(buttons[i][j]);
                                button.setText(att[i][j]);
                            }
                        }*/

                        //Toast.makeText(Personal_information.this,response.toString(), Toast.LENGTH_LONG).show();//测试用的
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
        //将String转换成Document
        Document document= Jsoup.parse(html);
        Element infoTable = document.getElementsByAttributeValue("class", "table").first();
        Elements tableLineInfos = infoTable.select("tbody").select("td");
        Elements tableLineInfoss = infoTable.select("tbody").select("tr");
        String[][] informations = new String[tableLineInfoss.size()][tableLineInfos.size()];
        for(Element element:tableLineInfos) {
            String temp = element.text().trim();
            informations[i][j] = temp;
            if(j==7){
                i++;
                j=0;
            }else{
                j++;
            }
        }

        return informations;
    }
}
