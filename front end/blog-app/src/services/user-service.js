import { myAxios } from "./Helper";

export const signUp = (user) => {
  return myAxios.post("/auth/register", user).then((response) => response.data);
};

export const login = (loginDetail) => {
  return myAxios
    .post("/auth/login", loginDetail)
    .then((response) => response.data);
};

// Get User by userId

export const getUserById = (id) => {
  return myAxios.get(`/auth/user/${id}`).then((response) => response.data);
};
