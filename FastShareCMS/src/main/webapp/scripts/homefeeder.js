var id;
var begin;
var endOfPost;
var loadError;
var btLoadMode;
var spnLoading;
var postContainer;

function initializeEnv(){
    id = -1;
    begin = true;
    endOfPost = document.querySelector("#load_end");
    loadError = document.querySelector("#load_error");
    btLoadMore = document.querySelector("#load_more");
    spnLoading = document.querySelector("#loading_spinner");
    postContainer = document.querySelector("#post_container");
    getData();
}

function getData(){
    loadError.classList.add("d-none");
    btLoadMore.style = "visibility : hidden";
    spnLoading.style = "visibility : visible";
    
    let req = new XMLHttpRequest();
    req.onreadystatechange = function(){
        if (req.readyState === 4){
            if (req.status === 200){
                console.log(req.responseText);
                populate(JSON.parse(req.responseText));
            }
            else {
                loadError.classList.remove("d-none");
                spnLoading.style = "visibility : hidden";
                btLoadMore.style = "visibility : visible";
            }
        }
    };
    req.open("POST", "/postloader", true);
    req.send();
}

function populate(properties){
    for (let i = 0; i < properties.length; ++i){
        let text = document.createElement("p");
        let edit = document.createElement("a");
        let card = document.createElement("div");
        let image = document.createElement("img");
        let author = document.createElement("p");
        let header = document.createElement("h4");
        let content = document.createElement("div");

        card.classList.add("card");
        card.classList.add("mb-3");
        
        image.classList.add("card-img-top");
        image.classList.add("img-fluid");
        image.src = properties[i]["image_url"];
        image.alt = "Featured image";
        
        content.classList.add("card-body");
        
        header.classList.add("card-title");
        header.textContent = properties[i]["title"];
        
        text.classList.add("card-text");
        text.innerHTML = properties[i]["description"].replace('\n', '<br />');
        
        author.classList.add("text-muted");
        author.textContent = properties[i]["author"] + " ";
        
        edit.href = "/editor?action=\"edit\"&id=" + properties[i]["id"];
        edit.textContent = "( EDIT )";
        
        card.appendChild(image);
        card.appendChild(content);
        content.appendChild(header);
        content.appendChild(text);
        content.appendChild(author);
        author.appendChild(edit);
        
        document.querySelector("#posts").appendChild(card);
    }
    
    if (properties.length < 10){
        spnLoading.style = "visibility : hidden";
        btLoadMore.style = "visibility : hidden";
        endOfPost.classList.remove("d-none");
    }
}

document.addEventListener("DOMContentLoaded", initializeEnv);