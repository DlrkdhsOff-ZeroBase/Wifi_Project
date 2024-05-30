<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
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
    <c:choose>
        <c:when test="${not empty bookMarkGroupList}">
            <c:forEach var="dto" items="${bookMarkGroupList}">
                <tr>
                    <td>${dto.id}</td>
                    <td>${dto.bookMarkName}</td>
                    <td>${dto.no}</td>
                    <td>${dto.insertDate}</td>
                    <td>${dto.updateDate}</td>
                    <td>
                        <a href="update-bookmark-group?id=${dto.id}">수정</a>
                        <a href="delete-bookmark-group?id=${dto.id}">삭제</a>
                    </td>
                </tr>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <tr>
                <td colspan="6">정보가 존재하지 않습니다.</td>
            </tr>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>

<script type="text/javascript">
    window.onload = function() {
        const check = "<%=request.getAttribute("check")%>";
        if (check === "insert success") {
            alert("북마크 그룹 정보를 추가하였습니다.");
        } else if (check === "update success") {
            alert("북마크 수정을 성공하였습니다.");
        } else if (check === "delete success") {
            alert("삭제 성공하였습니다.");
        }
    };
</script>
</body>
</html>