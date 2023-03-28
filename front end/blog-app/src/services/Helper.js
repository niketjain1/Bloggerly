import axios from "axios";
import { getToken } from "../Auth";


export const BASE_URL ='http://Blogapp-env-1.eba-74nfb38m.us-east-2.elasticbeanstalk.com';

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



