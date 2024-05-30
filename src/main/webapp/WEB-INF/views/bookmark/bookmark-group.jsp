<%@ page import="com.zerobase.wifi.dto.BookMarkGroupDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>

    <style>
        #link-list {
            margin-bottom: 20px;
        }

        #table-list {
            font-family: Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        #table-list td, #table-list th {
            border: 1px solid #ddd;
            text-align: center;
            font-size: 14px;
            padding: 8px;
        }

        #table-list tr:nth-child(odd) {
            background-color: #f2f2f2;
        }

        #table-list tr:hover {
            background-color: #ddd;
        }

        #table-list th {
            padding-top: 12px;
            padding-bottom: 12px;
            background-color: #04AA6D;
            color: white;
        }
    </style>
</head>
<body>
<h1>즐겨찾기 그룹 관리</h1>

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

<button onclick="location.href='add-bookmark-group';">
    북마크 그룹 이름 추가
</button>

<table id="table-list">
    <thead>
    <tr>
        <th>ID</th>
        <th>북마크 이름</th>
        <th>순서</th>
        <th>등록일자</th>
        <th>수정일자</th>
        <th>비고</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<BookMarkGroupDTO> list = (List<BookMarkGroupDTO>) request.getAttribute("list");
        if (list != null && !list.isEmpty()) {
            for (BookMarkGroupDTO dto : list) {
    %>
    <tr>
        <td>
            <%= dto.getId() %>
        </td>
        <td>
            <%= dto.getBookMarkName() %>
        </td>
        <td>
            <%= dto.getNo() %>
        </td>
        <td>
            <%= dto.getInsertDate() %>
        </td>
        <td>
            <%= dto.getUpdateDate() %>
        </td>
        <td>
            <a href="update-bookmark-group?id=<%= dto.getId() %>">
                수정
            </a>
            <a href="delete-bookmark-group?id=<%= dto.getId() %>">
                삭제
            </a>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="6">
            정보가 존재하지 않습니다.
        </td>
    </tr>
    <%
        }
    %>
    <script type="text/javascript">
        window.onload = function() {
            const check = "<%= request.getAttribute("check") %>";
            if (check === "insert success") {
                alert("북마크 그룹 정보를 추가하였습니다.");
            }else if (check === "update success") {
                alert("북마크 수정을 성공하였습니다.");
            }else if (check === "delete success") {
                alert("삭제 성공하였습니다.")
            }
        };
    </script>
    </tbody>
</table>
</body>
</html>
