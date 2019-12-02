<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <script src="/scripts/signin.js"></script>

        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.10.2/css/all.css">
        
        <link rel="shortcut icon" href="/imgs/logo_sr.png">
        <link rel="apple-touch-icon" href="/imgs/logo_sr.png">

        <title>FastShare CMS | SignIn</title>
    </head>

    <body class="bg-light">
        <article class="card-body mx-auto" style="max-width: 95%; width: 600px;">
            <img src="/imgs/logo_hr.svg" class="mx-auto d-block" style="max-width: 95%; width: 150px;">
            <h3 class="card-title mt-3 text-center">Sign Into FastShareCMS</h3>
            <form id="login_form" class="mt-5" method="post" onsubmit="return appendParams();">
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-file-signature"></i> </span>
                    </div>
                    <input name="username" class="form-control" placeholder="Username" type="text" required="true">
                </div> <!-- form-group// -->

                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                    </div>
                    <input name="password" class="form-control" placeholder="Password" type="password" required="true">
                </div> <!-- form-group// -->

                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block"> Login </button>
                </div> <!-- form-group// -->      
                <p class="text-center">Doesn't have an account yet? <a href="/signup">Sign Up</a> </p>                                                                 
            </form>
        </article>
    </body>
</html>
