<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="header">
    <div>        
        <c:if test="${empty login}">
            <form action="SessionLogin" method="post" class="navbar-form navbar-right" role="form">
                <div class="form-group">
                    <input type="text" placeholder="Login" class="form-control" name="log" required="true">
                </div>
                <div class="form-group">
                    <input type="password" placeholder="Password" class="form-control" name="pass" required="true">
                </div>

                <input type="hidden" name="action" value="logUser"/>  
                <button type="submit" name="submit" class="btn btn-default">Connexion</button>  

            </form>
        </c:if>
        <c:if test="${login ne null}">
            <form action="Deconnecte" method="get" class="navbar-form navbar-right" role="form">
                <div class="form-group">
                    <label>Bienvenue à ${login}</label>
                    <button type="submit" name="submit" class="btn btn-default">Déconnexion</button></a>
                </div>
            </form>
        </c:if> 

    </div>

    <h3 class="text-muted">Gestionnaire d'utilisateurs</h3>
    
</div>
