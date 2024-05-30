package com.zerobase.wifi.controller;

import com.zerobase.wifi.service.APIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
public class APIController {

    @Autowired
    private APIService apiService;

    @GetMapping("/lode-wifi-data")
    public String fetchWifiData(Model model) {
        try {
            int start = 1;
            int end = 1000;
            int maxData = Integer.MAX_VALUE; // 최대 데이터 개수 설정
            int totalCount = getTotalCount(); // 모든 데이터 값

            int totalFetched = 0;   // 현재 저장한 데이터 값

            while (start <= totalCount && totalFetched < maxData) {
                String xmlData = sendRequest(start, end);
                int fetchedDataCount = apiService.saveWifiData(xmlData);
                totalFetched += fetchedDataCount;

                // 가져온 데이터가 없으면 종료
                if (fetchedDataCount == 0) {
                    break;
                }

                start = end + 1;
                end = end + 1000;

                // end 값이 maxData를 초과하지 않도록 조정
                end = Math.min(end, maxData);
            }
            model.addAttribute("totalFetched", totalFetched);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "lode-wifi-data";
    }

    public int getTotalCount() throws Exception {
        String response = sendRequest(1,1);
        int totalCount = 0;

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(response)));
            doc.getDocumentElement().normalize();

            // "list_total_count" 엘리먼트 찾기
            NodeList nodeList = doc.getElementsByTagName("list_total_count");

            if (nodeList.getLength() > 0) {
                Element element = (Element) nodeList.item(0);
                totalCount = Integer.parseInt(element.getTextContent());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalCount;
    }

    private String sendRequest(int start, int end) throws Exception {

        String urlBuilder = "http://openapi.seoul.go.kr:8088" +
                "/" + URLEncoder.encode("62565469726b616f37346a5343494c", StandardCharsets.UTF_8) +
                "/" + URLEncoder.encode("xml", StandardCharsets.UTF_8) +
                "/" + URLEncoder.encode("TbPublicWifiInfo", StandardCharsets.UTF_8) +
                "/" + URLEncoder.encode(String.valueOf(start), StandardCharsets.UTF_8) +
                "/" + URLEncoder.encode(String.valueOf(end), StandardCharsets.UTF_8);

        URL url = new URL(urlBuilder);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/xml");

        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        rd.close();
        conn.disconnect();

        return sb.toString();
    }
}