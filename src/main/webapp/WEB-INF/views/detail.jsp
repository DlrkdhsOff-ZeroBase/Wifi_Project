<%@ page import="com.zerobase.wifi.dto.WifiDTO" %>
<%@ page import="com.zerobase.wifi.dto.BookMarkGroupDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>

    <style>
        #link-list {
            margin-bottom: 20px;
        }

        #bookmark-list {
            margin-bottom: 5px;
        }

        #bookmark-list select {
            float: left;
            margin-right: 5px;
        }

        #table-list {
            font-family: Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        #table-list td, #table-list th {
            border: 1px solid #ddd;
            font-size: 14px;
            padding: 8px;
        }

        #table-list tr:nth-child(odd) {
            background-color: #f2f2f2;
        }

        #table-list th {
            padding-top: 12px;
            padding-bottom: 12px;
            background-color: #04AA6D;
            text-align: center;
            color: white;
            width: 20%;
        }
    </style>
</head>
<body>
<h1>와이파이 정보 구하기</h1>

<div id="link-list">
    <a href="home">홈</a>
    &#124;
    <a href="getHistory">위치 히스토리 목록</a>
    &#124;
    <a href="lode-wifi-data">Open API 와이파이 정보 가져오기</a>
    &#124;
    <a href="bookmark-list">북마크 보기</a>
    &#124;
    <a href="bookmark-group">북마크 그룹 관리</a>
</div>

<%
    List<BookMarkGroupDTO> list = (List<BookMarkGroupDTO>) request.getAttribute("list");
    WifiDTO wifiDTO = (WifiDTO) request.getAttribute("wifi");
%>

<div id="bookmark-list">
    <form action="add-bookmark" method="post" id="bookmark-form">
        <select name="name">
            <option value="none" selected>북마크 그룹 이름 선택</option>
            <%
                for (BookMarkGroupDTO dto : list) {
            %>
            <option value="<%= dto.getName() %>">
                <%= dto.getName() %>
            </option>
            <%
                }
            %>
        </select>

        <input type="submit" value="북마크 추가하기">
        <input type="hidden" name="mgr_no" value="<%= wifiDTO.getMgr_no() %>">
        <input type="hidden" name="wifi_name" value="<%= wifiDTO.getWifi_name() %>">
    </form>
</div>
<table id="table-list">
    <tr>
        <th>거리(Km)</th>
        <td><%= wifiDTO.getDistance() %>
        </td>
    </tr>
    <tr>
        <th>관리번호</th>
        <td><%= wifiDTO.getMgr_no() %>
        </td>
    </tr>
    <tr>
        <th>자치구</th>
        <td><%= wifiDTO.getBorough() %>
        </td>
    </tr>
    <tr>
        <th>와이파이명</th>
        <td><%= wifiDTO.getWifi_name() %>
        </td>
    </tr>
    <tr>
        <th>도로명주소</th>
        <td><%= wifiDTO.getAddress1() %>
        </td>
    </tr>
    <tr>
        <th>상세주소</th>
        <td><%= wifiDTO.getAddress2() %>
        </td>
    </tr>
    <tr>
        <th>설치위치(층)</th>
        <td><%= wifiDTO.getIn_floor() %>
        </td>
    </tr>
    <tr>
        <th>설치유형</th>
        <td><%= wifiDTO.getIn_type() %>
        </td>
    </tr>
    <tr>
        <th>설치기관</th>
        <td><%= wifiDTO.getIn_by() %>
        </td>
    </tr>
    <tr>
        <th>서비스구분</th>
        <td><%= wifiDTO.getService() %>
        </td>
    </tr>
    <tr>
        <th>망종류</th>
        <td><%= wifiDTO.getNetwork() %>
        </td>
    </tr>
    <tr>
        <th>설치년도</th>
        <td><%= wifiDTO.getC_year() %>
        </td>
    </tr>
    <tr>
        <th>실내외구분</th>
        <td><%= wifiDTO.getIn_out() %>
        </td>
    </tr>
    <tr>
        <th>WIFI접속환경</th>
        <td><%= wifiDTO.getR_connection() %>
        </td>
    </tr>
    <tr>
        <th>X좌표</th>
        <td><%= wifiDTO.getLnt() %>
        </td>
    </tr>
    <tr>
        <th>Y좌표</th>
        <td><%= wifiDTO.getLat() %>
        </td>
    </tr>
    <tr>
        <th>작업일자</th>
        <td><%= wifiDTO.getWork_date() %>
        </td>
    </tr>
</table>
<script type="text/javascript">
    window.onload = function() {
        const check = "<%= request.getAttribute("check") %>";
        if (check === "fail") {
            alert("북마크 추가에 실해 했습니다. 다시 시도 해주세요.");
        } else if(check === "add false"){
            alert("북마크 이름을 선택해주세요.");
        }
    };
</script>
</body>
