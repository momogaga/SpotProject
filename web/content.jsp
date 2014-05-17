<%-- 
    Document   : index
    Created on : 13 mars 2014, 13:55:34
    Author     : MoMo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!-- Ne pas oublier cette ligne sinon tous les tags de la JSTL seront ignorés ! -->  
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<html>
    <head>  
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
        <title>Gestionnaire de musique</title>  
    </head>  
    <body> 
        <c:if test="${login ne null}">
            <div class="row">
                <div class="col-md-2 sidebar">
                    <ul class="nav nav-pills nav-stacked" id="MyTab">
                        <li class="active"><a href="#rafraichir" data-toggle="tab"><span class="glyphicon glyphicon-refresh"></span> Rafraichir</a></li>
                    </ul>
                </div>

                <div class="col-md-10 main">
                    <!-- Tab panes -->
                    <div class="tab-content">
                        <div class="tab-pane active" id="rafraichir">
                            <form class="form-horizontal" action="FileUpload" method="post" enctype="multipart/form-data">
                                <legend><a href="ServletUsers?action=listerLesUtilisateurs">Afficher la liste de tous les utilisateurs</a></legend>
                            </form>
                            <!-- Zone qui affiche les utilisateurs si le paramètre action vaut listerUtilisateur -->  
                            <c:if test="${param.action == 'listerLesUtilisateurs'}" >  
                                <form action="ServletUsers" method="get"> 
                                    <table class="table table-striped">  
                                        <!-- La ligne de titre du tableau des comptes -->  
                                        <tr>  
                                            <td><b>Login</b></td>  
                                            <td><b>Nom</b></td>  
                                            <td><b>Prénom</b></td>
                                            <td><b></b></td>
                                        </tr>  

                                        <!-- Ici on affiche les lignes, une par utilisateur -->  
                                        <!-- cette variable montre comment on peut utiliser JSTL et EL pour calculer -->  
                                        <c:set var="total" value="0"/>  

                                        <c:forEach var="u" items="${listeDesUsers}" varStatus="status">  
                                            <tr class="${status.index%2==0 ? 'alt' : ''}">  
                                                <td>${u.login}</td>
                                                <td>${u.lastname}</td> 
                                                <td>${u.firstname}</td>                                            
                                                <c:set var="total" value="${total+1}"/>  
                                            </tr>  
                                        </c:forEach>  

                                        <!-- Affichage du solde total dans la dernière ligne du tableau -->  
                                        <tr>
                                            <td><b>TOTAL</b></td>
                                            <td></td><td></td>
                                            <td><b>${total}</b></td>
                                        </tr>  
                                    </table>  
                                    <div style="text-align: center">
                                        <ul class="pagination pagination-sm" style="margin: 0px;">  
                                            <c:if test="${currentPage != 1}">
                                                <li><a href="ServletUsers?action=listerLesUtilisateurs&page=${currentPage - 1}">Previous</a></li>
                                                </c:if>

                                            <c:forEach begin="1" end="${noOfPages}" var="i">
                                                <c:choose>
                                                    <c:when test="${currentPage eq i}">
                                                        <li></li>
                                                        </c:when>
                                                        <c:otherwise>
                                                        <li><a href="ServletUsers?action=listerLesUtilisateurs&page=${i}">${i}</a></li>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>


                                            <%--For displaying Next link --%>
                                            <c:if test="${currentPage lt noOfPages}">
                                                <li><a href="ServletUsers?action=listerLesUtilisateurs&page=${currentPage + 1}">Next</a></li>
                                                </c:if>  
                                        </ul>
                                    </div>
                                </form>
                            </c:if>  
                        </div>           
                    </div>

                </div>
            </div>
        </c:if> 
        <c:if test="${empty login}">
            <p>Veuillez vous connecter pour accéder aux fonctionnalités</p>
        </c:if>

    </body>
</html>
