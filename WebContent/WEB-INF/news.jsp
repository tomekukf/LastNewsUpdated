<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>Dodawanie_Newsa</title>
</head>
<body>
<%@ include file="/WEB-INF/fragments/header.jspf" %>

  <div class="container">
        <div class="col-md-8 col-md-offset-2">
            <form class="form-signin" method="post" action="new">
                <h2 class="form-signin-heading">Dodaj nowego newsa ! </h2>
                <input name="inputName" type="text" class="form-control" placeholder="Co dodajesz?"
                    required autofocus />
                <input name="inputUrl" type="url" class="form-control" placeholder="URL"
                    required autofocus /> 
                <textarea name="inputDescription" rows="10
                " name="inputUsername"
                    class="form-control" placeholder="Opis" required autofocus></textarea>
                <input class="btn btn-lg btn-primary btn-block" type="submit"
                    value="Dodaj!" />
            </form>
        </div>
    </div>



<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
<%@ include file="/WEB-INF/fragments/footer.jspf" %>
</body>
</html>