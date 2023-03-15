// authenticate or isloggedin

export const isloggedin = () =>{

   let data =  localStorage.getItem("data");
   if(data == null){
    return false;
   }else{
    return true;
   }
}

// doLogin - data => set data to local storage

export const doLogin=(data, next)=>{
    localStorage.setItem("data", JSON.stringify(data));
    next();
}

// doLogout - remove data from local storage

export const doLogout = (next) =>{
    localStorage.removeItem("data");
    next();
}

// get currentUser 

export const getCurrentUserDetail=()=>{
    if(isloggedin){
        return JSON.parse(localStorage.getItem("data"));
    }else{
        return undefined;
    }
}