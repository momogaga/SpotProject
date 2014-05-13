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

            <!-- Message qui s'affiche lorsque la page est appelé avec un paramètre http message   
            <c:if test="${!empty param['message']}">  
                <h2>Reçu message : ${param.message}</h2>  
            </c:if>  -->

            <div class="row">
                <div class="col-md-2 sidebar">
                    <ul class="nav nav-pills nav-stacked" id="MyTab">
                        <li class="active"><a href="#rafraichir" data-toggle="tab"><span class="glyphicon glyphicon-refresh"></span> Rafraichir</a></li>
                        <li><a href="#ajouter" data-toggle="tab"><span class="glyphicon glyphicon-plus"></span> Crée</a></li>
                        <li><a href="#rechercher" data-toggle="tab"><span class="glyphicon glyphicon-search"></span> Rechercher</a></li>
                        <li><a href="#modifier" data-toggle="tab"><span class="glyphicon glyphicon-edit"></span> Mettre à jour</a></li>
                    </ul>
                </div>

                <div class="col-md-10 main">
                    <!-- Tab panes -->
                    <div class="tab-content">
                        <div class="tab-pane active" id="rafraichir">
                            <form class="form-horizontal" action="FileUpload" method="post" enctype="multipart/form-data">
                                <legend><a href="ServletUsers?action=listerLesUtilisateurs">Afficher la liste de tous les utilisateurs</a></legend>

                                <div class="form-group">

                                    <label class="col-md-3 control-label"></label>
                                    <div class="col-md-4">
                                        <input id="upload" name="uploaded" class="input-file" type="file" accept=".csv">
                                    </div> 
                                    <div class="col-md-4">
                                        <button type="submit" name="submit" class='btn btn-success'>Uploader</button>
                                    </div>
                                </div>

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

                                                <td><input type="checkbox" value="${u.id}" name="id" id="check"/></td>
                                                <!-- On compte le nombre de users -->  
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
                                    <br /><br />
                                    <input type="hidden" name="action" value="supprimerUnUtilisateur"/> 
                                    <button type="submit" name="submit" class="btn btn-danger" id="btn">Supprimer l'utilisateur</button> 

                                </form>
                            </c:if>  

                            <c:if test="${param.action == 'listerLeUtilisateur'}" >  
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

                                                <td><input type="checkbox" value="${u.id}" name="id" id="check"/></td>
                                                <!-- On compte le nombre de users -->  
                                                <c:set var="total" value="${total+1}"/>  
                                            </tr>  
                                        </c:forEach>  

                                        <!-- Affichage du solde total dans la dernière ligne du tableau -->  
                                        <tr>
                                            <td><b>Total</b></td>
                                            <td></td><td></td>
                                            <td><b>${total}</b></td>
                                        </tr>  
                                    </table>  

                                    <br /><br />
                                    <input type="hidden" name="action" value="supprimerUnUtilisateur"/> 
                                    <button type="submit" name="submit" class="btn btn-danger" id="btn" disabled="disabled">Supprimer l'utilisateur</button> 

                                </form>
                            </c:if>
                        </div>
                        <div class="tab-pane" id="ajouter">
                            <form class="form-horizontal" action="ServletUsers" method="get">
                                <fieldset>

                                    <legend>Ajouter une musique</legend>

                                    <div class="form-group">
                                        <label class="col-md-4 control-label" for="nom">Nom :</label>  
                                        <div class="col-md-4">
                                            <input id="nom" name="nom" type="text" placeholder="" class="form-control input-md" required="">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-4 control-label" for="prenom">Prénom :</label>  
                                        <div class="col-md-4">
                                            <input id="prenom" name="prenom" type="text" placeholder="" class="form-control input-md" required="">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-4 control-label" for="login">Login :</label>  
                                        <div class="col-md-4">
                                            <input id="login" name="login" type="text" placeholder="" class="form-control input-md" required="">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-4 control-label" for="password">Password :</label>  
                                        <div class="col-md-4">
                                            <input id="password" name="password" type="text" placeholder="" class="form-control input-md" required="">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-md-4">
                                            <!-- Astuce pour passer des paramètres à une servlet depuis un formulaire JSP !-->  
                                            <input type="hidden" name="action" value="creerUnUtilisateur"/> 
                                            <button type="submit" name="submit"  class="btn btn-info">Création de l'utilsateur</button>
                                        </div>
                                    </div>

                                </fieldset>
                            </form> 
                        </div>
                        <div class="tab-pane" id="rechercher">

                            <form class="form-horizontal" action="ServletUsers" method="get">
                                <fieldset>

                                    <legend>Rechercher une musique</legend>

                                    <div class="form-group">
                                        <label class="col-md-4 control-label" for="login">Login :</label>  
                                        <div class="col-md-4">
                                            <input id="login" name="login" type="text" placeholder="" class="form-control input-md" required="">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-md-4">
                                            <!-- Astuce pour passer des paramètres à une servlet depuis un formulaire JSP !-->  
                                            <input type="hidden" name="action" value="chercherParLogin"/>  
                                            <button type="submit" name="submit" class="btn btn-success">Recherche de l'utilsateur</button>  
                                        </div>
                                    </div>

                                </fieldset>
                            </form> 
                        </div>
                        <div class="tab-pane" id="modifier">
                            <form class="form-horizontal" action="ServletUsers" method="get">
                                <fieldset>

                                    <legend>Modifier une musique</legend>

                                    <div class="form-group">
                                        <label class="col-md-4 control-label" for="login">Login :</label>  
                                        <div class="col-md-4">
                                            <input id="login" name="login" type="text" placeholder="" class="form-control input-md" required="">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-4 control-label" for="nom">Nom :</label>  
                                        <div class="col-md-4">
                                            <input id="nom" name="nom" type="text" placeholder="" class="form-control input-md" required="">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-4 control-label" for="prenom">Prénom :</label>  
                                        <div class="col-md-4">
                                            <input id="prenom" name="prenom" type="text" placeholder="" class="form-control input-md" required="">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-4 control-label" for="password">Password :</label>  
                                        <div class="col-md-4">
                                            <input id="password" name="password" type="text" placeholder="" class="form-control input-md" required="">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-md-4">
                                            <!-- Astuce pour passer des paramètres à une servlet depuis un formulaire JSP !-->  
                                            <input type="hidden" name="action" value="modifierUnUtilisateur"/>  
                                            <button type="submit" name="submit" class="btn btn-warning">Mise à jour de l'utilisateur</button>  
                                        </div>
                                    </div>

                                </fieldset>
                            </form>  
                        </div>
                    </div>
                    <!-- Fin du menu -->  
                </div>
            </div>
        </c:if> 
        <c:if test="${empty login}">
            <p>Veuillez vous connecter pour accéder aux fonctionnalités</p>
        </c:if>

    </body>
</html>
