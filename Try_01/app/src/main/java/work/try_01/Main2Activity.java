package work.try_01;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
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
import java.util.List;
import java.util.Map;


import static work.util.getUrl.*;

public class Main2Activity extends AppCompatActivity {
    private String cookies;
    public static String[] url = new String[8];
    private int[] imageId = new int[] { R.drawable.img01, R.drawable.img02,
            R.drawable.img03, R.drawable.img04, R.drawable.img05,
            R.drawable.img06, R.drawable.img07, R.drawable.img08}; // 定义并初始化保存图片id的数组

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main2);
        cookies=MainActivity.cookie;


        GridView gridview = (GridView) findViewById(R.id.gridView1); // 获取GridView组件
        String[] title = new String[] { "个人信息", "课程表", "考勤信息", "考试时间", "平时成绩",
                "奖惩查询", "课程信息查询", "晚归、违规电器查询"};
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();// 创建一个list集合
        // 通过for循环将图片id和列表项文字放到Map中，并添加到list集合中
        for (int i = 0; i < imageId.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", imageId[i]);
            map.put("title", title[i]);
            listItems.add(map); // 将map对象添加到List集合中
        }

        SimpleAdapter adapter = new SimpleAdapter(this,
                listItems,
                R.layout.items,
                new String[] { "title", "image" },
                new int[] {R.id.title, R.id.image }
        ); // 创建SimpleAdapter
        gridview.setAdapter(adapter); // 将适配器与GridView关联
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    Intent intent = new Intent(Main2Activity.this,Personal_information.class);
                    startActivity(intent);
                }
                if(position==1){
                    Intent intent = new Intent(Main2Activity.this,Course_list.class);
                    startActivity(intent);
                }
                if(position==2){
                    Intent intent = new Intent(Main2Activity.this,Attendance.class);
                    startActivity(intent);
                }
                if(position==3){
                    Intent intent = new Intent(Main2Activity.this,Examination_time.class);
                    startActivity(intent);
                }
                if(position==4){
                    Intent intent = new Intent(Main2Activity.this,Scoure.class);
                    startActivity(intent);
                }if(position==5){
                    Intent intent = new Intent(Main2Activity.this,Punishment.class);
                    startActivity(intent);
                }if(position==6){
                    Intent intent = new Intent(Main2Activity.this,Offer_courses.class);
                    startActivity(intent);
                }
                if(position==7){
                    Intent intent = new Intent(Main2Activity.this,Mistake.class);
                    startActivity(intent);
                }

            }

        });
        course();
    }
    private void course(){
        RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest("http://class.sise.com.cn:7001/sise/module/student_states/student_select_class/main.jsp",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Document document= Jsoup.parse(response);
                        url[0]= getPersonal_informationlink(document);
                        url[1]= getCourselink(document);
                        url[2]= getAttendancelink(document);
                        url[3]= getExaminationlink(document);
                        url[4]= getScorelink(document);
                        url[5]= getPunishmenlink(document);
                        url[6]= getOffercourseslink(document);
                        url[7]= getMistaketUrl(document);

//                        Toast.makeText(Main2Activity.this,url[6], Toast.LENGTH_LONG).show();//测试用的
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
                header.put("Cookie", cookies);
                return header;
            }
        };
        mQueue.add(stringRequest);
    }
}
