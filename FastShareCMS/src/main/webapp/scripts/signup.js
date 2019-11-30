function checkAndCreate(){
    let uName;
    let uPass = document.querySelector('input[name="password"]').value;
    
    if (uPass.length == 0)
        window.alert("Empty password")
    if (uPass !== document.querySelector('input[name="password_c"]').value)
        window.alert("Erro senha incompativel");
}

