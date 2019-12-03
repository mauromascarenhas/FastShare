var p_text;
var p_title;
var p_image;

function initializeEnv(){
    p_text = document.querySelector("#pv_text");
    p_title = document.querySelector("#pv_title");
    p_image = document.querySelector("#pv_image");
}

function updateText(value){
    p_text.textContent = value ? value : "Your description goes here!";
}

function updateTitle(value){
    p_title.textContent = value ? value : "Title";
}

function updateImage(url){
    let image = new Image();
    image.src = url;
    
    p_image.src = (image.complete && image.height !== 0) ? url : "/imgs/gray-background.jpg";
}

document.addEventListener("DOMContentLoaded", initializeEnv);