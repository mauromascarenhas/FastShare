<!DOCTYPE html>
<html lang="en-GB">
    <head>
        <meta charset="utf-8">
        <title>Post Editor | FastShare CMS</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <script src="/scripts/previewupdater.js"></script>
        <script src="/scripts/verifier.js"></script>
        
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.10.2/css/all.css">
        <link rel="stylesheet" href="/styles/default.css">
        
        <link rel="shortcut icon" href="/imgs/logo_sr.png">
        <link rel="apple-touch-icon" href="/imgs/logo_sr.png">
    </head>
    
    <body>
        <header>
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <a class="navbar-brand" href="/">
                    <img src="/imgs/logo_hr.svg" width="30" height="30" class="d-inline-block align-top" alt="">
                    FastShareCMS
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#conteudoNavbarSuportado" aria-controls="conteudoNavbarSuportado" aria-expanded="false" aria-label="Alterna navega��o">
                  <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="conteudoNavbarSuportado">
                  <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                      <a class="nav-link" href="/">Home <span class="sr-only">(p�gina atual)</span></a>
                    </li>
                    <li class="nav-item dropdown active">
                      <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Posts
                      </a>
                      <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item active" href="/#">Create New</a>
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
            <div class="row">
                <div class="col-sm-5">
                    <h4 class="mt-3 text-center">Post details</h4>
                    <form class="mt-1" method="post" action="/editor">
                        <div class="form-group input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"> <i class="fa fa-heading"></i> </span>
                            </div>
                            <input name="title" class="form-control" placeholder="Title" type="text" required="true" value="${content.getId() ne -1 ? content.getTitle() : ""}" oninput="updateTitle(this.value);">
                        </div>

                        <div class="form-group input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"> <i class="fa fa-images"></i> </span>
                            </div>
                            <input name="image-url" class="form-control" placeholder="https://server.domain/image.extension" type="text" required="true" value="${content.getId() ne -1 ? content.getImgURL() : ""}" onfocusout="updateImage(this.value);">
                        </div>

                        <div class="form-group input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"> <i class="fa fa-align-justify"></i> </span>
                            </div>
                            <textarea rows="3" max-length="512" name="description" class="form-control" placeholder="Your description goes here!" required="true" oninput="updateText(this.value);">${content.getId() ne -1 ? content.getDescription() : ""}</textarea>
                        </div>

                        <div class="form-group input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"> <i class="fa fa-external-link-alt"></i> </span>
                            </div>
                            <input name="link" class="form-control" placeholder="https://mylink.to/page/article/etc" type="text" required="true" value="${content.getId() ne -1 ? content.getPostLink() : ""}">
                        </div>

                        <input type="hidden" name="id" value="${content.getId()}" />
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary btn-block"> SAVE </button>
                        </div>
                    </form>
                </div>
                <div class="col-sm-7">
                    <h4 class="mt-3 text-center">Post Preview</h4>
                    <div class="card mb-3">
                        <img id="pv_image" src="${content.getId() ne -1 ? content.getImgURL() : "/imgs/gray-background.jpg"}" class="img-fluid">
                        <div class="card-body">
                            <h5 id="pv_title" class="card-title">${content.getId() ne -1 ? content.getTitle() : "Title"}</h5>
                            <p id="pv_text" class="card-text">${content.getId() ne -1 ? content.getDescription() : "Your description goes here!"}</p>
                            <p class="card-text"><small class="text-muted" data-uname="1">${content.getAuthor().getName()}</small></p>
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
