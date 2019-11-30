var begin;
var btLoadMode;
var spnLoading;
var postContainer;

function initializeEnv(){
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
                populate(req.responseText);
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

function populate(elements){
    properties = JSON.parse(elements);
    
    for (let k = 0; k < properties.length; ++k){
        let text = document.creatElement("p");
        let edit = document.createElement("a");
        let card = document.createElement("div");
        let author = document.createElement("p");
        let header = document.createElement("h4");
        let content = document.createElement("div");

        card.class = "card mb-3";
        //TODO :everything
    }
}

document.addEventListener("DOMContentLoaded", initializeEnv);