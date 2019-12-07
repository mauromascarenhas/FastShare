var id;
var begin;
var lastDate;
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
            if (req.status === 200) populate(JSON.parse(req.responseText));
            else {
                loadError.classList.remove("d-none");
                spnLoading.style = "visibility : hidden";
                btLoadMore.style = "visibility : visible";
            }
        }
    };
    req.open("POST", lastDate ? `/postloader?date=${lastDate}` : "/postloader", true);
    req.send();
}

function populate(properties){
    for (let i = 0; i < properties.length; ++i){
        let text = document.createElement("p");
        let edit = document.createElement("a");
        let link = document.createElement("a");
        let card = document.createElement("div");
        let image = document.createElement("img");
        let author = document.createElement("p");
        let header = document.createElement("h4");
        let content = document.createElement("div");

        card.classList.add("card");
        card.classList.add("mb-3");
        
        link.href = properties[i]["linkto_url"];
        link.target = "_blank";
        
        let imaget = new Image();
        image.classList.add("card-img-top");
        image.classList.add("img-fluid");
        image.alt = "Featured image";
        imaget.src = properties[i]["image_url"];
        imaget.onload = function(){ image.src = properties[i]["image_url"]; };
        imaget.onerror = function() { image.src = "/imgs/gray-background.jpg"; };
        
        content.classList.add("card-body");
        
        header.classList.add("card-title");
        header.textContent = properties[i]["title"];
        
        text.classList.add("card-text");
        text.innerHTML = properties[i]["description"].replace('\n', '<br />');
        
        author.classList.add("text-muted");
        author.textContent = properties[i]["author"] + " ";
        
        edit.href = "/editor?action=edit&id=" + properties[i]["id"];
        edit.textContent = "( EDIT )";
        
        link.appendChild(image);
        card.appendChild(link);
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
    else {
        spnLoading.style = "visibility : hidden";
        btLoadMore.style = "visibility : visible";
    }
    
    if (properties.length > 0) lastDate = properties[properties.length - 1]["date"];
}

document.addEventListener("DOMContentLoaded", initializeEnv);