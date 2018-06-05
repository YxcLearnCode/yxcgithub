
package work.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;


/**
 * 公共类
 * @author （不是我）
 * @date 2015-2-1
 */
public class CommonUtil {
	
	/**随机课程表背景颜色
	 * @param max
	 * @return int
	 */
	public static int getRandom(int max){
		return (int)(Math.random()*max);
	}


	/**
	 * 获取Taken值
	 * @return 字符串
	 */
	public static String[] getHiddenMap() {
		String[]   hidden = new String[2];
		String html="http://class.sise.com.cn:7001/sise/";
		try {
			Document document = Jsoup.connect(html).get();
			Element hiddenElement = document.getElementsByTag("input").first();
			hidden[0] = hiddenElement.attr("name");
//			System.out.println(hidden[0]);
			hidden[1] = hiddenElement.attr("value");
//			System.out.println(hidden[0]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return hidden;
	}

}
