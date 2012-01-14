<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Home</title>
</head>
<body>
<ul>
  <li><a href="<c:url value="/signout" />">Sign Out</a></li>
</ul>
<h3>Your Retweets</h3>
<ul>
  <c:forEach items="${requestScope.retweets}" var="retweet">
    <li>(<c:out value="${retweet.count}"/>) - <c:out value="${retweet.text}"/></li>
  </c:forEach>
</ul>
</body>
</html>