<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en-GB">
    <head>
        <meta charset="utf-8">
        <title>Manage Users | FastShare CMS</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <script src="/scripts/userapproval.js"></script>
        
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
                    <div class="col-md-12">
                        <h1 class="mt-3">User management</h1>
                        <div id="users">
                        <c:if test="${users.size() gt 0}">
                            <p class="lead">Check the boxes so as to allow users to post.</p>
                            <ul class="list-group list-group-flush mt-2">
                            <c:forEach var="user" items="${users}">
                                <li class="list-group-item${user.getRole() eq 'ADMIN' ? ' disabled' : ""}">
                                    <div class="container">
                                        <div class="row">
                                            <div class="col-1">
                                                <div id="spn-${user.getId()}" class="d-flex justify-content-center" style="visibility:hidden;">
                                                    <div class="spinner-grow" style="width: 1.5rem; height: 1.5rem;" role="status">
                                                      <span class="sr-only">Loading...</span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-11">
                                                <div class="custom-control custom-checkbox">
                                                    <c:if test="${user.getApproved()}">
                                                    <input type="checkbox" class="custom-control-input" onchange="changeUser(${user.getId()}, this)" id="${user.getId()}" checked>
                                                    </c:if>
                                                    <c:if test="${not user.getApproved()}">
                                                    <input type="checkbox" class="custom-control-input" onchange="changeUser(${user.getId()}, this)" id="${user.getId()}">
                                                    </c:if>
                                                    <label class="custom-control-label" for="${user.getId()}">[ ${user.getRole()} ] ${user.getUsername()}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                            </c:forEach>
                            </ul>
                        </c:if>
                            
                            <div id="load_error" class="card text-white bg-warning mb-3 text-center d-none mt-5">
                                <div class="card-title card-header">Update error</div>
                                <div class="card-body">
                                    <p class="card-text">Unfortunately it was not possible to update the user approval. We suggest you to try again later...</p>
                                </div>
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
