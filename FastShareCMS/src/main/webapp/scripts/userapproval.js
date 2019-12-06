var loadError;

function initializeEnv(){
    loadError = document.querySelector("#load_error");
}

function changeUser(id, caller){
    let spnsend = document.querySelector(`#spn-${id}`);
    caller.disabled = "disabled";
    spnsend.style = "visibility : visible";
    
    let req = new XMLHttpRequest();
    req.onreadystatechange = function(){
        if (req.readyState === 4){
            if (req.status === 200){
                if (req.responseText === "OK"){
                    caller.disabled = "";
                    spnsend.style = "visibility : hidden";
                }
                else loadError.classList.remove("d-none");
            }
            else loadError.classList.remove("d-none");
        }
    };
    req.open("POST", `/usermanagement?id=${id}`, true);
    req.send();
}

document.addEventListener("DOMContentLoaded", initializeEnv);