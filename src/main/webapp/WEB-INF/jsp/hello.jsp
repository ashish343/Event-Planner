<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
  <head>
	<title>Event Planner</title>
	<link type="text/css" href="${path}/resources/css/bootstrap.min.css" rel="stylesheet"/>
	<link type="text/css" href="${path}/resources/css/pageCss.css" rel="stylesheet"/>
	<%@ include file="/WEB-INF/jsp/js/pageJS.jsp" %>
  </head>
  <body>
    <div class="container">
        <%@ include file="/WEB-INF/jsp/NavigationBar/navbar.jsp" %>
	</div>
	<%@ include file="/WEB-INF/jsp/body/eventData.jsp" %>
    
	<%@ include file="/WEB-INF/jsp/js/loadJS.jsp" %>
  </body>
</html>