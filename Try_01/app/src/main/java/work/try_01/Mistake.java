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


public class Mistake extends AppCompatActivity {
    String cookie;
    String username;
    String url;
    String  mistakes[][];
    private final String year = "year";
    private final String team = "team";
    private final String sushe = "sushe";
    private final String date = "date";
    private final String count = "count";
    private final String days = "days";
    private final String why = "why";
    private final String whos = "whos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mistake);
        cookie = MainActivity.cookie;
        mistake();
        username=MainActivity.userName;
        System.out.println(username);
    }
    private void mistake(){
        RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
        url = Main2Activity.url[7];
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.e("TAG", response);
                        mistakes= parseHtml(response);
                        ListView listView = (ListView) findViewById(R.id.ListView_mistake);
                        ArrayList<HashMap<String,String>> mylistArray = new ArrayList <HashMap<String,String>>();
                        HashMap<String, String> map1 = new HashMap<String, String>();
                        map1.put(year, "学年");
                        map1.put(team, "学期");
                        map1.put(sushe, "宿舍");
                        map1.put(date, "停电日期");
                        map1.put(count, "停电次数");
                        map1.put(days, "停电天数");
                        map1.put(why, "停电原因");
                        map1.put(whos, "责任人");

                        mylistArray.add(map1);
                        for(int i=0; i<mistakes.length; i++){
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put(year, mistakes[i][0]);
                            map.put(team, mistakes[i][1]);
                            map.put(sushe, mistakes[i][2]);
                            map.put(date, mistakes[i][3]);
                            map.put(count, mistakes[i][4]);
                            map.put(days, mistakes[i][5]);
                            map.put(why, mistakes[i][6]);
                            map.put(whos, mistakes[i][7]);
                            mylistArray.add(map);
                        }

                        //生成适配器，数组-->>ListItem
                        SimpleAdapter mSchedule = new SimpleAdapter(
                                Mistake.this,
                                mylistArray,    //  数据来源
                                R.layout.mistake_items,   // ListItem的XML实现
                                new String[] {year,team,sushe,date,count,days,why,whos},   // 动态数组与ListItem对应的子项
                                new int[] {R.id.year,R.id.team,R.id.sushe,R.id.date,R.id.count,R.id.days,R.id.why,R.id.whos}    // attendance_items的XML文件里面的TextView ID
                        );
                        listView.setAdapter(mSchedule);

                        for(int i=0;i<mistakes.length;i++){
                            for(int j=0;j<mistakes[i].length;j++){
                                System.out.print(mistakes[i][j]);
                            }
                            System.out.println();
                        }
                        //Toast.makeText(Mistake.this,response.toString(), Toast.LENGTH_LONG).show();//测试用的
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
    protected  String[][] parseHtml(String html){
        int i = 0;
        int j = 0;
        //将String转换成Document
        Document document= Jsoup.parse(html);
        Element infoTable = document.getElementsByAttributeValue("class", "table").first();
        Elements tableLineInfos = infoTable.select("tbody").select("td");
        Elements tableLineInfoss = infoTable.select("tbody").select("tr");
        String[][] informations = new String[tableLineInfoss.size()][tableLineInfos.size()];
//            System.out.println(tableLineInfos.size()-1);
//            System.out.println(tableLineInfoss.size());
        for(Element element:tableLineInfos) {
            String temp = element.text().trim();
            informations[i][j]=temp;
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
