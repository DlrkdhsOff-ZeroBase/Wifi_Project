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
    <a href="bookmark-list">북마크 보기</a>
    &#124;
    <a href="bookmark-group">북마크 그룹 관리</a>
</div>

<%
    String id = request.getParameter("id");
    String actionUrl;
    String bt;
    if (id != null && !id.isEmpty()) {
        actionUrl = "update-bookmark-group";
        bt = "수정";
    } else {
        actionUrl = "add-bookmark-group";
        bt = "추가";
    }
%>
<form id="bookmarkForm" action="<%= actionUrl %>" method="post"  onsubmit="return validateForm()">
    <table id="table-list">
        <tr>
            <th>북마크 이름</th>
            <td>
                <label for="name"></label><input type="text" name="name" id="name">
            </td>
        </tr>
        <tr>
            <th>순서</th>
            <td>
                <label for="no"></label><input type="text" name="no" id="no">
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center;">
                <input type="submit" value="<%= bt %>">
            </td>
        </tr>
    </table>
    <input type="hidden" name="id" value="<%= id %>">
</form>

<script type="text/javascript">
    function validateForm() {
        const name = document.getElementById("name").value;
        const no = document.getElementById("no").value;

        if (name === "" && no === "") {
            alert("모든 필드를 입력해주세요.");
            return false;
        }else if (name === "") {
            alert("이름을 입력해주세요.");
            return false;
        }else if (no === "") {
            alert("순서를 입력해주세요.");
            return false;
        }

        return true;
    }

    window.onload = function() {
        const check = "<%= request.getAttribute("check") %>";
        if (check === "insert false") {
            alert("중복된 값이 있습니다. 다시 입력해주세요.");
        }else if (check === "update false") {
            alert("수정을 실패했습니다 다시 입력해주세요");
        }
    };
</script>

</body>
</html>