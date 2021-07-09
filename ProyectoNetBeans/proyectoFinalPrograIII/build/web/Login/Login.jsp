

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Login</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/StyleLogin.css" rel="stylesheet">
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>



    </head>
    <body>


    <div class="container">
        <div class="card card-container">
      
            <img id="profile-img" class="profile-img-card" src="images/avatar_user.png" />
            
            <p id="profile-name" class="profile-name-card"></p>
            <form class="form-signin" action="LogIn" method="POST">
                <input type="hidden" name="accion" value="login" />            
                <span id="reauth-email" class="reauth-email"></span>
                <input type="text" autocomplete="off"  name="usuario" id="inputEmail" class="form-control" placeholder="User" required autofocus="on">
                <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
               <%---     <div id="remember" class="checkbox">
                        <label>
                            <input type="checkbox" value="remember-me"> Remember me
                        </label>
                    </div> ---%>
                <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Sign in</button>
            </form><!-- /form -->
          <!--  <a href="#" class="forgot-password">
                Forgot the password?
            </a>--->
        </div><!-- /card-container -->
    </div><!-- /container -->
    </body>
</html>
