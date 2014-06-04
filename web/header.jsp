<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="header">
    <div>  
        <c:if test="${empty login}">
            <button class="btn btn-default navbar-right" data-toggle="modal" data-target="#myConnect">Log in</button> 
            <button class="btn btn-default navbar-right" data-toggle="modal" data-target="#myCompte">Sign in</button>

            <div class="modal fade" id="myConnect" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-body">

                            <form action="SessionLogin" method="post" class="navbar-form" role="form">
                                <div class="form-group">
                                    <input type="text" placeholder="Login" class="form-control" name="log" required="true">
                                </div>
                                <div class="form-group">
                                    <input type="password" placeholder="Password" class="form-control" name="pass" required="true">
                                </div>

                                <input type="hidden" name="action" value="logUser"/>  
                                <button type="submit" name="submit" class="btn btn-default">Log in</button>

                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="myCompte" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-body">

                            <form class="form-horizontal" action="ServletUsers" method="get">
                                <fieldset>

                                    <legend>Sign up</legend>

                                    <div class="form-group">
                                        <label class="col-md-4 control-label" for="login">Login :</label>  
                                        <div class="col-md-4">
                                            <input id="login" name="login" type="text" placeholder="" class="form-control input-md" required="">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-4 control-label" for="password">Password :</label>  
                                        <div class="col-md-4">
                                            <input id="password" name="password" type="password" placeholder="" class="form-control input-md" required="">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-md-4">  
                                            <input type="hidden" name="action" value="creerUtilisateur"/> 
                                            <button type="submit" name="submit"  class="btn btn-info">Sign up</button>
                                        </div>
                                    </div>

                                </fieldset>
                            </form> 
                        </div>
                    </div>
                </div>
            </div>

        </c:if>
        <c:if test="${login ne null}">
            <div class="form-inline">
                <a class="btn btn-info navbar-right" data-toggle="modal" href="/SpotProject/cart.jsp" style="margin-top: 8px;" >Cart <span class="glyphicon glyphicon-shopping-cart"></span></a>
                <button class="btn btn-primary navbar-right" data-toggle="modal" data-target="#myGestion" style="margin-top: 8px; margin-right: 5px;" >Account <span class="glyphicon glyphicon-cog"></span></button>

                <form action="Deconnecte" method="get" class="navbar-form navbar-right" role="form">

                    <label>Bienvenue à ${login} !</label>
                    <button type="submit" name="submit" class="btn btn-success" style="margin-right: 5px;">Logout <span class="glyphicon glyphicon-off"></span></button>

                </form>

            </div>

            <div class="modal fade" id="myGestion" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-body">

                            <form class="form-horizontal" action="ServletUsers" method="get">
                                <fieldset>
                                    <div class="modal-header">

                                        <h4 class="modal-title">Modifier mon compte</h4>
                                    </div>
                                    <div class="modal-body">
                                        <input type="hidden" id="login" name="login" value="${login}"/> 
                                        <h3>Votre abonnement : "${abo.nom}" </h3>
                                        <h3>Prix de l'offre : ${abo.prix} &euro;</h3>
                                        <h3>Restant : ${delai} jours</h3>




                                    </div>
                                    <div class="modal-footer">

                                        <div class="form-inline">
                                            <label class=" control-label" for="abonnement">Abonnement :</label>

                                            <select id="abonnement" name="abonnement" class="form-control">
                                                <option value="2">Week-end</option>
                                                <option value="3">Semaine</option>
                                                <option value="4">Mois</option>
                                                <option value="5">An</option>
                                                <option value="6">Vie</option>
                                            </select>
                                            <input type="hidden" name="action" value="modifierUtilisateur"/> 
                                            <button type="submit" name="submit"  class="btn btn-info">Modifier mon compte</button>
                                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                        </div>
                                    </div>
                                </fieldset>
                            </form> 
                        </div>
                    </div>
                </div>
            </div>
        </c:if> 
    </div>

    <h3 class="text-muted">Gestionnaire de musiques</h3>
</div>

<script type="text/javascript">
    $('#myCompte').modal(options);
    $('#myConnect').modal(options);
    $('#myGestion').modal(options);
</script>
