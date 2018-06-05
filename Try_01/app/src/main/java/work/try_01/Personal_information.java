package work.try_01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.widget.TextView;
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

import java.util.HashMap;
import java.util.Map;


public class Personal_information extends AppCompatActivity {
    String cookie;
    String url;
    String information[][];
    //个人信息页面的TextView引用，6行2列
    private int[][] personals={
            {R.id.information01,R.id.information02},
            {R.id.information11,R.id.information12},
            {R.id.information21,R.id.information22},
            {R.id.information31,R.id.information32},
            {R.id.information41,R.id.information42}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        cookie = MainActivity.cookie;
        //Log.i("aa",cookie);  //测试用的
        personal_information();
    }

    private void personal_information(){
        RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
        url = Main2Activity.url[0];
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("TAG", response);
                        information= parseHtml(response);
                        /*for(int i=0;i<information.length;i++){
                            for(int j=0;j<information[i].length;j++){
                                System.out.print(information[i][j]);
                            }
                            System.out.println();
                        }*/
                        for(int i = 0;i<information.length;i++){
                            for(int j = 0;j<information[i].length;j++){
                                TextView textView = (TextView) findViewById(personals[i][j]);
                                textView.setText(information[i][j]);
                            }
                        }

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
        String[][] information =new String[5][2];
        try{
            //将String转换成Document
            Document document= Jsoup.parse(html);
            String nbsp=Jsoup.parse(" ").text().trim();
            Elements elements=document.getElementsByAttributeValue("class","td_left");
            for(Element element:elements){
                String temp=element.text().trim();
                if(temp.equals(nbsp)){
                    continue;
                }else{
                    information[i][j]=temp;
                }
                if(j==1){
                    i++;
                    j=0;
                }else{
                    j++;
                }
            }
        }catch(Exception e){
            Log.e("parseError",e.getMessage());
        }
        return information;
    }

}
