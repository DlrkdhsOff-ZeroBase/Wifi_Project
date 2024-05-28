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
<h1>즐겨찾기 그룹 관리</h1>

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

<form method="post" action="add-bookmark-group">
    <table id="table-list">
        <tr>
            <th>북마크 이름</th>
            <td>
                <input type="text" name="name">
            </td>
        </tr>
        <tr>
            <th>순서</th>
            <td>
                <input type="text" name="no">
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center;">
                <input type="submit" value="추가">
            </td>
        </tr>
    </table>
</form>

<script type="text/javascript">
    window.onload = function() {
        var check = "<%= request.getAttribute("check") %>";
        if (check === "empty") {
            alert("입력하지 않은 부분이 있습니다 다시 입력해주세요.");
        }else if (check === "no"){
            alert("중복된 순서 입니다. 다시 입력해주세요.");
        }
    };
</script>

</body>
</html>