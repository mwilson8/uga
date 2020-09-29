function verify(){
    
    var u = document.getElementById("username").value;
    var p = document.getElementById("password").value;
    var valid;
    
    if (u == "") {
        document.getElementById("userName").style.backgroundColor = "yellow"; 
        valid = false;
    }
    if (p == "") {
        document.getElementById("password").style.backgroundColor = "yellow";
        valid = false;
    }
    
    if (!valid) document.getElementById("error").innerHTML = "Username and password cannot be blank";
    
    if (valid)
    return valid;
}

