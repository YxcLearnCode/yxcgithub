package work.try_01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

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

public class Punishment extends AppCompatActivity {
    protected String cookie;
    String url;
    private final String year = "year";
    private final String team = "team";
    private final String jianglijibie = "jianglijibie";
    private final String why = "why";
    private final String danwei = "danwei";
    private final String days = "days";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punishment);
        cookie = MainActivity.cookie;
        punishment();
    }
    private void punishment(){
        RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
        url= Main2Activity.url[5];
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String[][] aaa= parseHtml(response);
//                        for(int i=0;i<aaa.length;i++) {
//                            for (int j = 0; j < aaa[i].length; j++) {
//                                System.out.print(aaa[i][j]);
//                            }
//                            System.out.println();
//                        }
                        ListView listView = (ListView) findViewById(R.id.ListView_punishment);
                        ArrayList<HashMap<String,String>> mylistArray = new ArrayList <HashMap<String,String>>();
                        HashMap<String, String> map1 = new HashMap<String, String>();
                        map1.put(year, "学年");
                        map1.put(team, "学期");
                        map1.put(jianglijibie, "奖励级别");
                        map1.put(why, "奖励原因");
                        map1.put(danwei, "奖励单位/部门");
                        map1.put(days, "奖励日期");


                        mylistArray.add(map1);
                        for(int i=0; i<aaa.length; i++){
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put(year, aaa[i][0]);
                            map.put(team, aaa[i][1]);
                            map.put(jianglijibie, aaa[i][2]);
                            map.put(why, aaa[i][3]);
                            map.put(danwei, aaa[i][4]);
                            map.put(days, aaa[i][5]);
                            mylistArray.add(map);
                        }

                        //生成适配器，数组-->>ListItem
                        SimpleAdapter mSchedule = new SimpleAdapter(
                                Punishment.this,
                                mylistArray,    //  数据来源
                                R.layout.punishment_items,   // ListItem的XML实现
                                new String[] {year,team,jianglijibie,why,danwei,days},   // 动态数组与ListItem对应的子项
                                new int[] {R.id.year,R.id.team,R.id.jianglijibie,R.id.why,R.id.danwei,R.id.days}    // attendance_items的XML文件里面的TextView ID
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
    public String[][] parseHtml(String respond){
        int i=0;
        int j=0;
        String[][] titles = new String[2][6];
        try{
            //将String转换成Document
            Document document= Jsoup.parse(respond);
            String nbsp=Jsoup.parse(" ").text().trim();
            Elements elements=document.getElementsByAttributeValue("class","tablebody");
            for(Element element:elements){
                String temp=element.text().trim();
                if(temp.equals(nbsp)){
                    titles[i][j]=null;
                }else{
                    titles[i][j]=temp;
                }
                if(j==5){
                    i++;
                    j=0;
                }else{
                    j++;
                }
            }
        }catch(Exception e){
            Log.e("parseError",e.getMessage());
        }
        return titles;
    }
}
