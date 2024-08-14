<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="DETAIL"></c:set>
<%@ include file="../common/head.jspf"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판</title>

</head>

<body>
    
        <a href="list" class="btn">리스트</a>
        <table>
            <thead>
                <tr>
                    <th>id</th>
                    <th>title</th>
                    <th>body</th>
                    <th>memberId</th>
                    <th>regDate</th>
                    <th>updateDate</th>
                    <c:if test="${loginedMember.id == article.memberId }">
                    <th class="gitar">수정</th>
                    <th class="gitar">삭제</th>
                    </c:if>
                </tr>
            </thead>
            <tbody>
			<tr>
				<td>${article.id }</td>
				<td>${article.title }</td>
				<td>${article.body }</td>
				<td>${article.memberId }</td>
				<td>${article.regDate }</td>
				<td>${article.updateDate }</td>
				<c:if test="${loginedMember.id == article.memberId }">
					<td class="gitar" ><a href="modify">수정</a></td>
					<td class="gitar" ><a href="doDelete?id=${article.id }">삭제</a></td>
				</c:if>
			</tr>
            </tbody>
        </table>

</body>
</html>