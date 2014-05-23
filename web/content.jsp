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

            </div>
        </c:if> 
        <c:if test="${empty login}">
            <p>Veuillez vous connecter pour accéder aux fonctionnalités.</p>

            <div class="tab-pane active">
                <a href="ServletMusic?action=listerMusic">Afficher la liste des musiques</a>

                <!-- Zone qui affiche les utilisateurs si le paramètre action vaut listerUtilisateur -->  
                <c:if test="${param.action == 'listerMusic'}" >  
                    <form action="ServletMusic" method="get"> 
                        <table class="table table-striped">  
                            <!-- La ligne de titre du tableau des comptes -->  
                            <tr> 
                                <td></td>
                                <td><b>Titre</b></td>  
                                <td><b>Annee</b></td>                               
                            </tr>  

                            <!-- Ici on affiche les lignes, une par utilisateur -->  
                            <!-- cette variable montre comment on peut utiliser JSTL et EL pour calculer -->  
                            <c:set var="total" value="0"/>  

                            <c:forEach var="m" items="${listMusic}" varStatus="status">  
                                <tr class="${status.index%2==0 ? 'alt' : ''}"> 
                                    <td><span class="glyphicon glyphicon-play-circle"</span></td>
                                    <td>${m.titre}</td> 
                                    <td>${m.annee}</td>                                                                                           
                                    <c:set var="total" value="${total+1}"/>  
                                </tr>  
                            </c:forEach>  

                            <!-- Affichage du solde total dans la dernière ligne du tableau -->  
                            <tr>
                                <td>Total :</td>
                                <td></td>
                                <td><b>${total}</b></td>
                            </tr>  
                        </table>  
                        <div style="text-align: center">
                            <ul class="pagination pagination-sm" style="margin: 0px;">  
                                <c:if test="${currentPage != 1}">
                                    <li><a href="ServletMusic?action=listerMusic&page=${currentPage - 1}">Previous</a></li>
                                    </c:if>

                                <c:forEach begin="1" end="${noOfPages}" var="i">
                                    <c:choose>
                                        <c:when test="${currentPage eq i}">
                                            <li></li>
                                            </c:when>
                                            <c:otherwise>
                                            <li><a href="ServletMusic?action=listerMusic&page=${i}">${i}</a></li>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>


                                <%--For displaying Next link --%>
                                <c:if test="${currentPage lt noOfPages}">
                                    <li><a href="ServletMusic?action=listerMusic&page=${currentPage + 1}">Next</a></li>
                                    </c:if>  
                            </ul>
                        </div>
                    </form>
                </c:if>  
            </div>   
        </c:if>

    </body>
</html>
