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

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js" type="text/javascript"></script>
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
    </head>  
    <body> 

        <c:if test="${login ne null}">
            <div class="row">   

                <form class="form-inline" action="ServletMusic" method="get">
                    <fieldset>

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
                            <div class="col-md-4">  
                                <input type="hidden" name="action" value="searchMusic"/> 
                                <button type="submit" name="submit" class="btn btn-default">Rechercher</button>
                            </div>
                        </div>

                    </fieldset>
                </form> 

                <br>

                <div class="tab-pane active">
                    <a href="ServletMusic?action=listerMusic">Afficher la liste des musiques</a>

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
                                        <td>${m.artiste.nom}</td>
                                        <td> <a href="http://fr.wikipedia.org/wiki/${m.artiste.nom}" target="_blank">See!</a></td>
                                        <td><button type="button" class="btn btn-warning btn-xs"><span class="glyphicon glyphicon-euro"></span></button></td>
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
                                        <td>${m.artiste.nom}</td>
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

            <a href="ServletMusic?action=listerMusic"><h3>Testez trois morceaux gratuits !</h3></a>

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
                                <th>Année</th>
                                <th width="100px"><span class='glyphicon glyphicon-globe'></span> Wiki</th>
                            </tr>
                            <c:forEach var="m" items="${listMusic}" varStatus="status"  begin="0" end="2">  
                                <tr class="${status.index%2==0 ? 'alt' : ''}"> 
                                    <td><a href="#"><span class="glyphicon glyphicon-play-circle"></span></a></td>
                                    <td>${m.titre}</td> 
                                    <td>${m.artiste.nom}</td>
                                    <td>${m.annee}</td>
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
