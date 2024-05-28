<%@ page import="com.zerobase.wifi.dto.WifiDTO" %>
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
    <a href="bookmark-list.jsp">북마크 보기</a>
    &#124;
    <a href="bookmark-group">북마크 그룹 관리</a>
</div>

<%--<%--%>
<%--    String mgrNo = request.getParameter("mgrNo");--%>
<%--    String dist = request.getParameter("dist");--%>

<%--    WifiDao wifiDao = new WifiDao();--%>
<%--    WifiDto wifiDto = wifiDao.select(mgrNo);--%>

<%--    BookmarkGroupDao bookmarkGroupDao = new BookmarkGroupDao();--%>
<%--    List<BookmarkGroupDto> bookmarkGroupDtoList = bookmarkGroupDao.selectList();--%>
<%--%>--%>

<%--<div id="bookmark-list">--%>
<%--    <form action="bookmark-add-submit.jsp" method="post" id="bookmark-form">--%>
<%--        <select name="gid">--%>
<%--            <option value="none" selected>북마크 그룹 이름 선택</option>--%>
<%--            <%--%>
<%--                for (BookmarkGroupDto item : bookmarkGroupDtoList) {--%>
<%--            %>--%>
<%--            <option value="<%= item.getId() %>">--%>
<%--                <%= item.getName() %>--%>
<%--            </option>--%>
<%--            <%--%>
<%--                }--%>
<%--            %>--%>
<%--        </select>--%>

<%--        <input type="submit" value="즐겨찾기 추가하기">--%>
<%--        <input type="hidden" name="mgrNo" value="<%= mgrNo %>">--%>
<%--    </form>--%>
<%--</div>--%>

<%
    WifiDTO dto = (WifiDTO) request.getAttribute("wifi");
%>
<table id="table-list">
    <tr>
        <th>거리(Km)</th>
        <td><%= dto.getDistance() %>
        </td>
    </tr>
    <tr>
        <th>관리번호</th>
        <td><%= dto.getMgr_no() %>
        </td>
    </tr>
    <tr>
        <th>자치구</th>
        <td><%= dto.getBorough() %>
        </td>
    </tr>
    <tr>
        <th>와이파이명</th>
        <td><%= dto.getWifi_name() %>
        </td>
    </tr>
    <tr>
        <th>도로명주소</th>
        <td><%= dto.getAddress1() %>
        </td>
    </tr>
    <tr>
        <th>상세주소</th>
        <td><%= dto.getAddress2() %>
        </td>
    </tr>
    <tr>
        <th>설치위치(층)</th>
        <td><%= dto.getIn_floor() %>
        </td>
    </tr>
    <tr>
        <th>설치유형</th>
        <td><%= dto.getIn_type() %>
        </td>
    </tr>
    <tr>
        <th>설치기관</th>
        <td><%= dto.getIn_by() %>
        </td>
    </tr>
    <tr>
        <th>서비스구분</th>
        <td><%= dto.getService() %>
        </td>
    </tr>
    <tr>
        <th>망종류</th>
        <td><%= dto.getNetwork() %>
        </td>
    </tr>
    <tr>
        <th>설치년도</th>
        <td><%= dto.getC_year() %>
        </td>
    </tr>
    <tr>
        <th>실내외구분</th>
        <td><%= dto.getIn_out() %>
        </td>
    </tr>
    <tr>
        <th>WIFI접속환경</th>
        <td><%= dto.getR_connection() %>
        </td>
    </tr>
    <tr>
        <th>X좌표</th>
        <td><%= dto.getLnt() %>
        </td>
    </tr>
    <tr>
        <th>Y좌표</th>
        <td><%= dto.getLat() %>
        </td>
    </tr>
    <tr>
        <th>작업일자</th>
        <td><%= dto.getWork_date() %>
        </td>
    </tr>
</table>
</body>
