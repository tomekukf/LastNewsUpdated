<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	type="text/css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/styles.css"
	type="text/css" rel="stylesheet">

<title>LastNews</title>
</head>
<body>
	<%@ include file="/WEB-INF/fragments/header.jspf" %>
	<!--  Klasy row bs-callout bs-callout-primary oznaczają wiersz oraz 
		dodatkową ornamentację, którą znaleźliśmy na blogu.

	 	col col-md-1 col-sm-2 to oznaczenie, że na ekranach średniego rozmiaru 
	
		chcemy dla danej sekcji 1 kolumnę, a na małych ekranach 2 kolumny.-->
	<c:if test="${not empty requestScope.newses }">
		<c:forEach var="news" items="${requestScope.newses }">
			<div class="container">
				<div class="row bs-callout bs-callout-primary">
					<div class="col col-md-1 col-sm-2">
						<!-- Kolumna głosowania -->
						<!-- /vote?discovery_id=3&vote=VOTE_UP -->
						<a href="${pageContext.request.contextPath}/vote?news_id=${news.id}&vote=VOTE_UP" class="btn btn-block btn-primary btn-success"><span
							class="glyphicon glyphicon-arrow-up"></span> </a>
						<div class="well well-sm centered">
							<c:out value="${news.upVote + news.downVote }" />
						</div>
						<a href="${pageContext.request.contextPath}/vote?news_id=${news.id}&vote=VOTE_DOWN" class="btn btn-block btn-primary btn-warning"><span
							class="glyphicon glyphicon-arrow-down"></span> </a>
					</div>
					<div class="col col-md-11 col-sm-10">
						<!-- Kolumna z treścią -->
						<h3 class="centered">
							<a href="<c:out value="${news.url}" />"><c:out value="${news.name}" /></a>
						</h3>
						  
						<h6>
							<small>Dodane przez: <c:out value="${news.user.username}" />, Dnia: <fmt:formatDate value="${news.timestamp}" pattern="dd/MM/YYYY"/></small>
						</h6>
						  
						<p><c:out value="${news.description}" /></p>
						  <a href="<c:out value="${news.url}" />" class="btn btn-default btn-xs">Przejdź do strony</a>
					</div>
				</div>
			</div>
		</c:forEach>
	</c:if>

	<%@ include file="/WEB-INF/fragments/footer.jspf" %>
	<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>