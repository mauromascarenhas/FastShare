<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en-GB">
    <head>
        <meta charset="utf-8">
        <title>FastShare CMS | Home</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <script src="/scripts/homefeeder.js"></script>
        
        <link rel="shortcut icon" href="/imgs/logo_sr.png">
        <link rel="apple-touch-icon" href="/imgs/logo_sr.png">
        
        <link rel="stylesheet" href="/styles/default.css">
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <a class="navbar-brand" href="/">
                    <img src="/imgs/logo_hr.svg" width="30" height="30" class="d-inline-block align-top" alt="">
                    FastShareCMS
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#conteudoNavbarSuportado" aria-controls="conteudoNavbarSuportado" aria-expanded="false" aria-label="Alterna navegação">
                  <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="conteudoNavbarSuportado">
                  <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                      <a class="nav-link" href="/">Home <span class="sr-only">(página atual)</span></a>
                    </li>
                    <li class="nav-item dropdown">
                      <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Posts
                      </a>
                      <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="/editor">Create New</a>
                        <a class="dropdown-item disabled" href="/my-posts">My Posts</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item disabled" href="/manage-users">Manage Users</a>
                      </div>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" href="/about">About</a>
                    </li>
                  </ul>
                  <form class="form-inline my-2 my-lg-0" action="search" method="GET">
                    <input name="query" class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                    <button id="search_btn" class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                  </form>
                </div>
            </nav>
        </header>
        
        <!-- Content -->
        <main role="main" class="container">
            <div class="container">
                <div class="row">
                    <div class="col-md-8">
                        <h1 class="mt-3">Search results for : <c:out value="${param['query']}"></c:out></h1>
                        <p class="lead">Here is what we could find using your search criteria...</p>
                        <div id="posts">
                            
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card" style="margin-bottom: 20px;">
                            <div class="card-header text-center">
                                Quick Links
                            </div>
                            <div class="list-group list-group-flush">
                              <a class="list-group-item list-group-item-action" href="/editor">Create New Post</a>
                              <a class="list-group-item list-group-item-action disabled" href="/manage-users">Manage Users</a>
                              <a class="list-group-item list-group-item-action" href="/about">About Us</a>
                            </div>
                        </div>
                        
                        <div class="card text-center" style="margin-bottom: 20px;">
                            <div class="card-header">
                                About Us
                            </div>
                            <div class="card-body">
                                <blockquote class="blockquote mb-0">
                                    <p class="card-text">FastShare is a simple CMS which focuses on making people's life easier to spread quick information. <a href="/about" class="card-link">Learn more about us!</a></p>
                                </blockquote>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        
        <footer class="footer">
            <div class="container">
              <span class="text-muted">Copyright &copy; 2019. FastShareCMS - All rights reserved.</span>
            </div>
      </footer>
    </body>
</html>
