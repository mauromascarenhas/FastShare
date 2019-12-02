function appendParams(){
    let form = document.querySelector("#login_form");
    let params = (new URL(document.location)).searchParams;
    let redirect = params.get("redirect");
    let action = params.get("action");
    
    console.log(redirect);
    console.log(action);
    
    if (redirect){
        let rdirect = document.querySelector("#form_redirect");
        if (!rdirect) rdirect = document.createElement("input");
        rdirect.id = "form_redirect";
        
        rdirect.setAttribute("name", "redirect");
        rdirect.setAttribute("value", redirect);
        rdirect.setAttribute("type", "hidden");
        form.appendChild(rdirect);
    }
    
    if (action) {
        let ract = document.querySelector("#form_raction");
        if (!ract) ract = document.createElement("input");
        ract.id = "form_raction";
        
        ract.setAttribute("name", "action");
        ract.setAttribute("value", action);
        ract.setAttribute("type", "hidden");
        form.appendChild(ract);
    }
    
    return true;
}