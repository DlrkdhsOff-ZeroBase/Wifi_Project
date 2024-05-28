
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
    <a href="bookmark-list.jsp">즐겨찾기 보기</a>
    &#124;
    <a href="bookmark-group">즐겨찾기 그룹 관리</a>
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
<%--    <%--%>
<%--        BookmarkGroupDao bookmarkGroupDao = new BookmarkGroupDao();--%>
<%--        if (bookmarkGroupDao.count() == 0) {--%>
<%--    %>--%>
    <tr>
        <td colspan="6">
            정보가 존재하지 않습니다.
        </td>
    </tr>
<%--    <%--%>
<%--    } else {--%>
<%--        List<BookmarkGroupDto> bookmarkGroupDtoList = bookmarkGroupDao.selectList();--%>
<%--        for (BookmarkGroupDto item : bookmarkGroupDtoList) {--%>
<%--            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");--%>
<%--            String regDate = sdf.format(item.getRegDttm());--%>
<%--            String uptDate = item.getUptDttm() == null ? "" : sdf.format(item.getUptDttm());--%>
<%--    %>--%>
<%--    <tr>--%>
<%--        <td>--%>
<%--            <%= item.getId() %>--%>
<%--        </td>--%>
<%--        <td>--%>
<%--            <%= item.getName() %>--%>
<%--        </td>--%>
<%--        <td>--%>
<%--            <%= item.getSeq() %>--%>
<%--        </td>--%>
<%--        <td>--%>
<%--            <%= regDate %>--%>
<%--        </td>--%>
<%--        <td>--%>
<%--            <%= uptDate %>--%>
<%--        </td>--%>
<%--        <td>--%>
<%--            <a href="bookmark-group-edit.jsp?id=<%= item.getId() %>">--%>
<%--                수정--%>
<%--            </a>--%>
<%--            <a href="bookmark-group-delete.jsp?id=<%= item.getId() %>">--%>
<%--                삭제--%>
<%--            </a>--%>
<%--        </td>--%>
<%--    </tr>--%>
<%--    <%--%>
<%--            }--%>
<%--        }--%>
<%--    %>--%>
    </tbody>
</table>
</body>
</html>
