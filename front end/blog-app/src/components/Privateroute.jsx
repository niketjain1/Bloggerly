import React from "react";
import { Outlet, Navigate } from "react-router-dom";
import { isloggedin } from "../Auth";


const Privateroute = () =>{

   

    if(isloggedin()){
        return <Outlet />
    }else{
        return <Navigate to={"/login"}/>;
    }
    
}

export default Privateroute