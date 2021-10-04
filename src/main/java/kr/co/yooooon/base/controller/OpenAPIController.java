package kr.co.yooooon.base.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import kr.co.yooooon.base.to.OpenapiTO;

@RestController
public class OpenAPIController{
	private ModelMap map = null;

	@RequestMapping(value="/base/weatherJinju", method=RequestMethod.GET)
	public ModelMap show(HttpServletRequest request, HttpServletResponse response) {
		try {

			String url = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?stationName=%EC%A0%95%EC%B4%8C%EB%A9%B4&dataTerm=daily&pageNo=1&numOfRows=100&returnType=xml&serviceKey=%2Bysf4Q6iWMstUtW44t0D6XlkAwbXN81RJkQPEvKZUkU2dSXzv1HPaS5usdVG2lljFb0lu1RELcuKjkLUqyZT%2FA%3D%3D";

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(url);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("item");

			ArrayList<OpenapiTO> list = new ArrayList<OpenapiTO>();

			for (int temp = 0; temp < nList.getLength(); temp++) {
				OpenapiTO test = new OpenapiTO();
				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					
					test.setDataTime(getTagValue("dataTime", eElement));
					test.setO3Value(getTagValue("o3Value", eElement));
					test.setCoValue(getTagValue("coValue", eElement));
					test.setPm10Value(getTagValue("pm10Value", eElement));
					
					list.add(test);
				}
			}

			System.out.println(list);

			map = new ModelMap();
			map.put("list", list);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	private static String getTagValue(String tag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		// 하위 노드 리스트의 첫 번째 노드(item index 0)
		if (nValue == null) {
			return null;
		}
		return nValue.getNodeValue();
	}

}
