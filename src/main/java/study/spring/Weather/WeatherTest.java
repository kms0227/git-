package study.spring.Weather;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import study.spring.helper.HttpHelper;
import study.spring.helper.JsonHelper;
import study.spring.model.WeatherBeans;

@Controller
public class WeatherTest {

	private static final Logger logger = LoggerFactory.getLogger(WeatherTest.class);

	@Autowired
	HttpHelper httpHelper;

	@Autowired
	JsonHelper jsonHelper;

	@RequestMapping(value = { "/WeatherTest.do", "/" })
	public String doRun(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {

		response.setContentType("application/json");

		List<WeatherBeans> list = null;
		
		
		try {
			/*
			 * 요청정보입력 아래와 같은 정보들은 사용자 가이드를 확인하여 찾아주시면 됩니다. 위도 경도는 엑셀파일 안에 있습니다.
			 */

			// 자신이 조회를 원하는 지역의 경도와 위도를 입력해주세요
			String nx = "60"; // 경도
			String ny = "115"; // 위도
			String baseDate = "20190421"; // 자신이 조회하고싶은 날짜를 입력해주세요
			String baseTime = "0500"; // 자신이 조회하고싶은 시간대를 입력해주세요
			// 서비스 인증키입니다. 공공데이터포털에서 제공해준 인증키를 넣어주시면 됩니다.
			String serviceKey = "xiiYTQYioDgh1T%2BJtscnFN2vhCuUJm3UbZO1ZHftBKbckqIFv9uSCURDcg6qab622VBsZWWpybcQsyNkoJeXDg%3D%3D";

			// 정보를 모아서 URL정보를 만들면됩니다. 맨 마지막 "&_type=json"에 따라 반환 데이터의 형태가 정해집니다.
			String urlStr = "http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastSpaceData?"
					+ "serviceKey=" + serviceKey + "&base_date=" + baseDate + "&base_time=" + baseTime + "&nx=" + nx
					+ "&ny=" + ny + "&_type=json";

			InputStream is = HttpHelper.getInstance().getWebData(urlStr, "UTF-8", null);

			// 통신결과 없으면 중단
			if (is == null) {
				System.out.println("Data Download Error");
				return null;
			}

			JSONObject json = jsonHelper.getJSONObject(is, "UTF-8");
			JSONObject response1 = json.getJSONObject("response");
			JSONObject body = response1.getJSONObject("body");
			JSONObject items = body.getJSONObject("items");
			JSONArray item = items.getJSONArray("item");

			// 필요한 데이터만 가져오기
			

			for (int i = 0; i < item.length(); i++) {
				JSONObject temp = item.getJSONObject(i);

				WeatherBeans wb = new WeatherBeans();

				wb.setBaseDate(temp.getInt("baseDate"));
				wb.setBaseTime(temp.getString("baseTime"));
				wb.setFcstDate(temp.getInt("fcstDate"));
				wb.setFcstTime(temp.getInt("fcstTime"));
				wb.setFcstValue(temp.getInt("fcstValue"));
				wb.setNx(temp.getInt("nx"));
				wb.setNy(temp.getInt("ny"));

				wb.setFcstValue(((Long) temp.getLong("fcstValue")).intValue()); // 실수로된 값과 정수로된 값이 둘다 있어서 실수로 통일
				if (temp.getString("category").equals("POP")) {
					wb.setCategory("강수확률");
				} else if (temp.getString("category").equals("REH")) {
					wb.setCategory("습도");
				} else if (temp.getString("category").equals("T3H")) {
					wb.setCategory("온도");
				}

				list.add(wb);

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("-------------------------------------");

		}
//		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("rt", "OK");
		data.put("item", list);

		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(response.getWriter(), data);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}