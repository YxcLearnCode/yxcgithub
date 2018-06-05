package work.try_01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;
import work.util.CommonUtil;

public class Course_list extends AppCompatActivity {
    String cookie;
    String courses[][];
    String url;
    //课程页面的button引用，8行7列
    private int[][] lessons={
            {R.id.lesson11,R.id.lesson12,R.id.lesson13,R.id.lesson14,R.id.lesson15,R.id.lesson16,R.id.lesson17},
            {R.id.lesson21,R.id.lesson22,R.id.lesson23,R.id.lesson24,R.id.lesson25,R.id.lesson26,R.id.lesson27},
            {R.id.lesson31,R.id.lesson32,R.id.lesson33,R.id.lesson34,R.id.lesson35,R.id.lesson36,R.id.lesson37},
            {R.id.lesson41,R.id.lesson42,R.id.lesson43,R.id.lesson44,R.id.lesson45,R.id.lesson46,R.id.lesson47},
            {R.id.lesson51,R.id.lesson52,R.id.lesson53,R.id.lesson54,R.id.lesson55,R.id.lesson56,R.id.lesson57},
            {R.id.lesson61,R.id.lesson62,R.id.lesson63,R.id.lesson64,R.id.lesson65,R.id.lesson66,R.id.lesson67},
            {R.id.lesson71,R.id.lesson72,R.id.lesson73,R.id.lesson74,R.id.lesson75,R.id.lesson76,R.id.lesson77},
            {R.id.lesson81,R.id.lesson82,R.id.lesson83,R.id.lesson84,R.id.lesson85,R.id.lesson86,R.id.lesson87}
    };
    //某节课的背景图,用于随机获取
    private int[] bg={R.drawable.kb1,R.drawable.kb2,R.drawable.kb3,R.drawable.kb4,R.drawable.kb5,R.drawable.kb6,R.drawable.kb7};
//    private CourseService courseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        cookie = MainActivity.cookie;
        course();
    }

    private void course(){
        RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
        url = Main2Activity.url[1];
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.e("TAG", response);
                        courses= parseHtml(response);
                        /*for(int i=1;i<courses.length;i++){
                            for(int j=1;j<courses[i].length;j++){
                                System.out.print(courses[i][j]);
                            }
                            System.out.println();
                        }*/
                        for(int i=1;i<courses.length;i++) {
                            for (int j = 1; j < courses[i].length; j++) {
                                Button lesson = (Button) findViewById(lessons[i - 1][j - 1]);
                                lesson.setText(courses[i][j]);
                                if(courses[i][j]!=null) {
                                    int bgRes = bg[CommonUtil.getRandom(bg.length - 1)];//随机获取背景色
                                    lesson.setBackgroundResource(bgRes);//设置背景
                                }
                            }
                        }
                        //Toast.makeText(Course_list.this,response.toString(), Toast.LENGTH_LONG).show();//测试用的
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

    //解析课表html
    public static String[][] parseHtml(String html){
        int i=0;
        int j=0;
        String[][] source =new String[9][8];
        try{
            //将String转换成Document
            Document document=Jsoup.parse(html);
            String nbsp=Jsoup.parse("&nbsp;").text().trim();
            Elements elements=document.getElementsByAttributeValue("class","font12");
            for(Element element:elements){
                String temp=element.text().trim();
                if(temp.equals(nbsp)){
                    source[i][j]=null;
                }else{
                    source[i][j]=temp;
                }
                if(j==7){
                    i++;
                    j=0;
                }else{
                    j++;
                }
            }
        }catch(Exception e){
            Log.e("parseError",e.getMessage());
        }
        return source;
    }
}
