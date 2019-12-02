var id;
var begin;
var btLoadMode;
var spnLoading;
var postContainer;

var test = [{
    id : 500,
    author : "anonymous",
    title : "A Title",
    description : "I do not know what could I have written here before! (Still don't know).",
    image_url : "https://helpx.adobe.com/content/dam/help/en/stock/how-to/visual-reverse-image-search/jcr_content/main-pars/image/visual-reverse-image-search-v2_intro.jpg"
}];

function initializeEnv(){
    id = -1;
    begin = true;
    btLoadMore = document.querySelector("#load_more");
    spnLoading = document.querySelector("#loading_spinner");
    postContainer = document.querySelector("#post_container");
    getData();
}

function getData(){
    btLoadMore.style = "visibility : hidden";
    spnLoading.style = "visibility : visible";
    
    
    let req = new XMLHttpRequest();
    req.onreadystatechange = function(){
        if (req.readyState === 4){
            if (req.status === 200){
                populate(JSON.parse(req.responseText));
                populate(test);
                spnLoading.style = "visibility : hidden";
                btLoadMore.style = "visibility : visible";
            }
            // TODO : Show error div
            //else ;
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
        
        image.class = "card-img-top";
        image.src = properties[i]["image_url"];
        image.alt = "Featured image";
        
        content.classList.add("card-body");
        
        header.classList.add("card-title");
        header.textContent = properties[i]["title"];
        
        text.classList.add("card-text");
        text.textContent = properties[i]["description"];
        
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
}

document.addEventListener("DOMContentLoaded", initializeEnv);