<%-- 
    Document   : template
    Created on : 20 févr. 2014, 14:45:20
    Author     : MoMo
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"  
         pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${param.title}</title>

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style.css" />  
        
        <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/favicon.ico" />
    </head>  

    <body>
        <div class="container">
            <jsp:include page="header.jsp"/>  

            <jsp:include page="${param.content}.jsp"/>  

            <jsp:include page="footer.jsp"/>  
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.js" ></script>

        <script type="text/javascript">
            $(document).ready(function() {
                $("#report tr:odd").addClass("odd");
                $("#report tr:not(.odd)").hide();
                $("#report tr:first-child").show();

                $("#report tr.odd").click(function() {
                    $(this).next("tr").toggle();
                    $(this).find(".arrow").toggleClass("up");
                });
                //$("#report").jExpand();
            });
        </script> 

        <script type="text/javascript">
            var checker = document.getElementById('check');

            var btn = document.getElementById('btn');
            checker.onchange = function() {
                if (this.checked) {
                    btn.disabled = false;
                } else {
                    btn.disabled = true;
                }
            }
        </script>
    </body>
</html>
