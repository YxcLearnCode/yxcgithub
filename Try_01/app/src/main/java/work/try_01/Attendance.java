package work.try_01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

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

public class Attendance extends AppCompatActivity {
    String cookie;
    String att[][];
    String url;
    private final String id = "id";
    private final String name = "name";
    private final String message = "message";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        cookie = MainActivity.cookie;
        //Log.i("aa",cookie);  //测试用的
        attendance();
    }
    private void attendance(){
        RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
        url = Main2Activity.url[2];
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.e("TAG", response);
                        att= parseHtml(response);
                        ListView listView = (ListView) findViewById(R.id.ListView02);
                        ArrayList<HashMap<String,String>> mylistArray = new ArrayList <HashMap<String,String>>();
                        HashMap<String, String> map1 = new HashMap<String, String>();
                        map1.put(id, "课程代码");
                        map1.put(name, "课程名称");
                        map1.put(message, "详细信息");
                        mylistArray.add(map1);
                        for(int i=0; i<att.length; i++){
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put(id, att[i][0]);
                            map.put(name, att[i][1]);
                            map.put(message, att[i][2]);
                            mylistArray.add(map);
                        }

                        //生成适配器，数组-->>ListItem
                        SimpleAdapter mSchedule = new SpecialAdapter(
                                Attendance.this,
                                mylistArray,    //  数据来源
                                R.layout.attendance_items,   // ListItem的XML实现
                                new String[] {id,name,message},   // 动态数组与ListItem对应的子项
                                new int[] {R.id.id,R.id.name,R.id.message}    // attendance_items的XML文件里面的TextView ID
                        );
                        listView.setAdapter(mSchedule);
                        /*for(int i=0;i<att.length;i++){
                            for(int j=0;j<att[i].length;j++){
                                System.out.print(att[i][j]);
                            }
                            System.out.println();
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
            if(j==2){
                i++;
                j=0;
            }else{
                j++;
            }
        }

        return informations;
    }
}
