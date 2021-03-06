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
        <title>Multitracksongs</title>  

    </head>  
    <body> 

        <c:if test="${login ne null}">
            <div class="row">   

                <form class="form-inline" action="ServletMusic" method="get">
                    <fieldset>
                        <c:if test="${!empty param['message']}"> 
                            <div class="container" style="text-align: center; color:rgb(0, 204, 0); font-size: 18px">
                                <p><span class="glyphicon glyphicon-ok"></span> ${param.message}</p>
                            </div>
                        </c:if>  
                        <div class="form-group col-md-8">
                            <a class="btn btn-default" href="ServletMusic?action=listerMusic">Afficher tout</a>
                            <a class="btn btn-default" href="ServletMusic?action=listerAchats">Afficher mes achats</a>
                        </div>

                        <div class="form-group">
                            <input id="search" type="text" name="search" class="form-control" placeholder="Search">
                        </div>

                        <div class="form-group">
                            <div>
                                <select id="type" name="type" class="form-control">
                                    <option value="Artiste">Artiste</option>
                                    <option value="Titre">Titre</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">                            
                            <input type="hidden" name="action" value="searchMusic"/> 
                            <button type="submit" name="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>

                        </div>

                    </fieldset>
                </form> 

                <br>

                <div class="tab-pane active">


                    <c:if test="${(param.action == 'listerMusic') && (abo.nom == 'Gratuit')}" >  
                        <form action="ServletMusic" method="get"> 

                            <table class="table table-striped" id="report">
                                <tr class="active">  
                                    <th width="10px"><span class='glyphicon glyphicon-music'></span></th>
                                    <th>Titre</th>
                                    <th>Artiste</th>
                                    <th width="100px"><span class='glyphicon glyphicon-globe'></span> Wiki</th>
                                    <th width="10px"> </th>
                                </tr>

                                <c:forEach var="m" items="${listMusic}" varStatus="status">  

                                    <tr class="${status.index%2==0 ? 'alt' : ''}"> 

                                        <td><span class="glyphicon glyphicon-play-circle"></span></td>                   
                                        <td>${m.titre}</td> 
                                        <td>
                                            <a href='ServletMusic?action=searchFacette&facette=${m.artiste.nom}'> ${m.artiste.nom}</a>
                                        </td>
                                        <td> <a href="http://fr.wikipedia.org/wiki/${m.artiste.nom}" target="_blank">See!</a></td>

                                        <td><button type="button" class="btn btn-warning btn-xs" onclick="self.location.href = '/SpotProject/SessionPanier?productCode=${m.id}'"><span class="glyphicon glyphicon-euro"></span></button></td>


                                    </tr>   
                                    <tr>
                                        <td colspan="5">
                                            <h4>Pistes disponibles :</h4>
                                            <ul style='list-style-type:none;'>
                                                <c:forEach var="p" items="${m.pistes}">
                                                    <li><span class="glyphicon glyphicon-play-circle"></span> ${p.nom}</li>  
                                                    </c:forEach>
                                            </ul>
                                        </td>  
                                    </tr>
                                </c:forEach>  
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
                    <c:if test="${(param.action == 'listerAchats')}" >  
                        <form action="ServletMusic" method="get"> 

                            <table class="table table-striped" id="report">
                                <tr class="active">  
                                    <th width="10px"><span class='glyphicon glyphicon-music'></span></th>
                                    <th>Titre</th>
                                    <th>Artiste</th>
                                    <th width="100px"><span class='glyphicon glyphicon-globe'></span> Wiki</th>
                                </tr>

                                <c:forEach var="m" items="${listAchat}" varStatus="status">  

                                    <tr class="${status.index%2==0 ? 'alt' : ''}"> 
                                        <td><a href="#"><span style="color:rgb(0, 204, 0);" class="glyphicon glyphicon-play-circle"></span></a></td>
                                        <td>${m.titre}</td> 
                                        <td>
                                            <a href='ServletMusic?action=searchFacette&facette=${m.artiste.nom}'> ${m.artiste.nom}</a>
                                        </td>
                                        <td> <a href="http://fr.wikipedia.org/wiki/${m.artiste.nom}" target="_blank">See!</a></td>
                                    </tr>   
                                    <tr>
                                        <td colspan="5">
                                            <h4>Pistes disponibles :</h4>
                                            <ul style='list-style-type:none;'>
                                                <c:forEach var="p" items="${m.pistes}">
                                                    <li><span class="glyphicon glyphicon-play-circle"></span> ${p.nom}</li>  
                                                    </c:forEach>
                                            </ul>
                                        </td>  
                                    </tr>
                                </c:forEach>  
                            </table>                   
                        </form>
                    </c:if>  

                    <!--pour les abonnés-->
                    <c:if test="${(param.action == 'listerMusic') && (abo.nom != 'Gratuit')}" >

                        <form action="ServletMusic" method="get"> 

                            <table class="table table-striped" id="report">
                                <tr class="active">  
                                    <th width="10px"><span class='glyphicon glyphicon-music'></span></th>
                                    <th>Titre</th>
                                    <th>Artiste</th>
                                    <th width="100px"><span class='glyphicon glyphicon-globe'></span> Wiki</th>
                                </tr>

                                <c:forEach var="m" items="${listMusic}" varStatus="status">  

                                    <tr class="${status.index%2==0 ? 'alt' : ''}"> 

                                        <td><a href="#"><span style="color:rgb(0, 204, 0);" class="glyphicon glyphicon-play-circle"></span></a></td>
                                        <td>${m.titre}</td> 
                                        <td>
                                            <a href='ServletMusic?action=searchFacette&facette=${m.artiste.nom}'> ${m.artiste.nom}</a>
                                        </td>
                                        <td> <a href="http://fr.wikipedia.org/wiki/${m.artiste.nom}" target="_blank">See!</a></td>
                                    </tr>   
                                    <tr>
                                        <td colspan="5">
                                            <h4>Pistes disponibles :</h4>
                                            <ul style='list-style-type:none;'>
                                                <c:forEach var="p" items="${m.pistes}">
                                                    <li><span class="glyphicon glyphicon-play-circle"></span> ${p.nom}</li>  
                                                    </c:forEach>
                                            </ul>
                                        </td>  
                                    </tr>
                                </c:forEach>  
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
            </div>
        </c:if> 
        <c:if test="${empty login}">
            <div class="container" style="font-size: 20px">
                <a href="ServletMusic?action=listerMusic"><span class='glyphicon glyphicon-random'></span> Testez trois morceaux gratuits !</a>
            </div>
            <div>
                <c:if test="${!empty param['message']}"> 
                    <div class="container" style="font-size: 18px; text-align: center; color:rgb(0, 204, 0);">
                        <p><span class="glyphicon glyphicon-ok"></span> ${param.message}</p>
                    </div>
                </c:if>  
            </div>
            <div class="tab-pane active">
                <br>

                <!-- Zone qui affiche les utilisateurs si le paramètre action vaut listerUtilisateur -->  
                <c:if test="${param.action == 'listerMusic'}" >  
                    <form action="ServletMusic" method="get"> 

                        <table class="table table-bordered">
                            <tr class="active">  
                                <th width="10px"><span class='glyphicon glyphicon-music'></span></th>
                                <th>Titre</th>
                                <th>Artiste</th>
                                <th width="100px"><span class='glyphicon glyphicon-globe'></span> Wiki</th>
                            </tr>
                            <c:forEach var="m" items="${listMusic}" varStatus="status"  begin="0" end="2">  
                                <tr class="${status.index%2==0 ? 'alt' : ''}"> 
                                    <td><a href="#"><span class="glyphicon glyphicon-play-circle"></span></a></td>
                                    <td>${m.titre}</td> 
                                    <td>${m.artiste.nom}</td>
                                    <td> <a href="http://fr.wikipedia.org/wiki/${m.artiste.nom}" target="_blank">See!</a></td>
                                </tr>  

                            </c:forEach> 

                        </table>  
                    </form>
                </c:if>  
            </div>   
        </c:if>

    </body>
</html>
