var logged_in;
var logged_out;
var logged_name;
var main_header;
var logged_admin;

function initializeEnv(){
    main_header = document.querySelector("#main_header");
    logged_in = document.querySelectorAll('[data-login="1"]');
    logged_out = document.querySelectorAll('[data-login="0"]');
    logged_name = document.querySelectorAll('[data-uname="1"]');
    logged_admin = document.querySelectorAll('[data-admin="1"]');
    checkSession();
}

function checkSession(){
    let req = new XMLHttpRequest();
    req.onreadystatechange = function(){
        if (req.readyState === 4){
            if (req.status === 200){
                let status = JSON.parse(req.responseText);
                if (status["connected"]){
                    logged_in.forEach(function (item){
                        item.classList.remove("d-none");
                    });
                    logged_name.forEach(function (item){
                        item.textContent = status["username"];
                    });
                    if (status["role"] === "ADMIN"){
                        logged_admin.forEach(function (item){
                            item.classList.remove("d-none");
                        });
                    }
                    if (main_header) main_header.textContent = `You're welcome, ${status["name"]}!`;
                }
                else logged_out.forEach(function (item){
                    item.classList.remove("d-none");
                });
            }
        }
    };
    req.open("POST", "/sessionstatus", true);
    req.send();
}

document.addEventListener("DOMContentLoaded", initializeEnv);