package com.zerobase.wifi.service;

import com.zerobase.wifi.dto.WifiDTO;
import com.zerobase.wifi.mapper.WifiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class WifiService {

    @Autowired
    private WifiMapper wifiMapper;

    public int saveWifiData(String xmlData) {
        List<WifiDTO> wifiList = parseXmlData(xmlData);
        for (WifiDTO wifiDTO : wifiList) {
            wifiMapper.save(wifiDTO);
        }
        return wifiList.size(); // 저장된 데이터의 개수를 반환
    }

    private List<WifiDTO> parseXmlData(String xmlData) {
        List<WifiDTO> wifiList = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlData)));
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("row");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    WifiDTO wifiDTO = new WifiDTO();
                    wifiDTO.setMgr_no(element.getElementsByTagName("X_SWIFI_MGR_NO").item(0).getTextContent());
                    wifiDTO.setBorough(element.getElementsByTagName("X_SWIFI_WRDOFC").item(0).getTextContent());
                    wifiDTO.setWifi_no(element.getElementsByTagName("X_SWIFI_MAIN_NM").item(0).getTextContent());
                    wifiDTO.setAddress1(element.getElementsByTagName("X_SWIFI_ADRES1").item(0).getTextContent());
                    wifiDTO.setAddress3(element.getElementsByTagName("X_SWIFI_ADRES2").item(0).getTextContent());
                    wifiDTO.setIn_floor(element.getElementsByTagName("X_SWIFI_INSTL_FLOOR").item(0).getTextContent());
                    wifiDTO.setIn_type(element.getElementsByTagName("X_SWIFI_INSTL_TY").item(0).getTextContent());
                    wifiDTO.setIn_by(element.getElementsByTagName("X_SWIFI_INSTL_MBY").item(0).getTextContent());
                    wifiDTO.setService(element.getElementsByTagName("X_SWIFI_SVC_SE").item(0).getTextContent());
                    wifiDTO.setNetwork(element.getElementsByTagName("X_SWIFI_CMCWR").item(0).getTextContent());
                    wifiDTO.setC_year(element.getElementsByTagName("X_SWIFI_CNSTC_YEAR").item(0).getTextContent());
                    wifiDTO.setIn_out(element.getElementsByTagName("X_SWIFI_INOUT_DOOR").item(0).getTextContent());
                    wifiDTO.setR_connection(element.getElementsByTagName("X_SWIFI_REMARS3").item(0).getTextContent());
                    wifiDTO.setLat(Double.parseDouble(element.getElementsByTagName("LAT").item(0).getTextContent()));
                    wifiDTO.setLnt(Double.parseDouble(element.getElementsByTagName("LNT").item(0).getTextContent()));
                    wifiDTO.setWork_date(element.getElementsByTagName("WORK_DTTM").item(0).getTextContent());
                    wifiList.add(wifiDTO);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wifiList;
    }
}