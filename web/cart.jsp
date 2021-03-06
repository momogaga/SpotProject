<!doctype html public "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <head>
        <title>Multitracksongs</title>
        <link rel="stylesheet" type="text/css" href="resources/bootstrap/css/bootstrap.min.css">

        <link rel="stylesheet" type="text/css" href="resources/style.css" /> 
        
        <link rel="icon" type="image/x-icon" href="resources/favicon.ico" />

    </head>
    <body>
        <div class="container">

            <jsp:include page="header.jsp"/>  
            <c:if test="${login ne null}">
                <div class="row"> 
                    <h3><span class='glyphicon glyphicon-shopping-cart'></span> Shopping cart</h3>

                    <table class="table table-striped">
                        <tr>
                            <th>Titre</th>
                            <th>Artiste</th>
                            <th>Prix</th>
                            <th></th>
                        </tr>
                        <c:set var="total" value="0"/>  
                        <c:forEach var="item" items="${cart.items}">
                            <tr valign="top">
                                <td>${item.morceau.titre}</td>
                                <td>${item.morceau.artiste.nom}</td>
                                <td>${item.morceau.prix}$</td>
                                <td style="text-align: right">
                                    <form action="SessionPanier">
                                        <input type="hidden" name="productCode" value="${item.morceau.id}">
                                        <input type="hidden" name="quantity" value="0"> 
                                        <button class="btn btn-danger" type="submit"><span class="glyphicon glyphicon-trash"</button>
                                    </form>
                                </td>
                                <c:set var="total" value="${total+item.morceau.prix}"/>  
                            </tr>
                        </c:forEach>
                        <tr>
                            <td><b>TOTAL</b></td>
                            <td></td>
                            <td><b>${total}$</b></td>
                            <td></td>
                        </tr>
                    </table>

                    <br>


                    <div class="btn-group pull-right">
                        <form action="<c:url value='/ServletMusic?action=listerMusic' />" method="post">
                            <button class="btn btn-default"type="submit">Continue Shopping</button>
                        </form>
                        <form action="Checkout" method="get" role="form">
                            <button class="btn btn-default"type="submit">Checkout <span class="glyphicon glyphicon-euro"></span></button>
                        </form>

                    </div>
                </div>
            </c:if>
            <jsp:include page="footer.jsp"/>  
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="resources/bootstrap/js/bootstrap.js" ></script>

    </body>
</html>
