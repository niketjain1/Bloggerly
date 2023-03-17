import axios from "axios";
// import { useState, useEffect } from "react";
import { getToken } from "../Auth";
// const[user, setUser] = useState(undefined)

// useEffect(
//     () => {
//        setUser(getCurrentUserDetail())
//     }, []);

export const BASE_URL ='http://localhost:5000';

export const myAxios = axios.create({
    baseURL:BASE_URL
});

export const privateAxios = axios.create({
    baseURL:BASE_URL
})

privateAxios.interceptors.request.use(config=>{
    const token = getToken()
    // console.log(token)
    if(token){
        config.headers.Authorization =`Bearer ${token}`
        return config
    }
} ,error=>Promise.reject(error))