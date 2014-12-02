<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="templates/clientlist.jsp"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/knockout-3.2.0.js"></script>
<script type="text/javascript">
    var client_ajax="${pageContext.request.contextPath}/client/list.do";
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/viewmodels/clientlist.js"></script>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Client Tracker</title>
    </head>
    <body>
        <p>This is the entry page for the client tracker application</p>
        <div data-bind="template:{name :'clientlist', data: clients}"></div>
    </body>

</html>
<script type="text/javascript">
        $(document).ready(function(){
            retrieve_client_list();
        });
</script>