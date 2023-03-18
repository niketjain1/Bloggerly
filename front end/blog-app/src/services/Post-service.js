import { privateAxios } from "./Helper";
import { myAxios } from "./Helper";
// createPost function to save post on server
export const savePost = (postdata) => {
  // console.log(postdata)
  return privateAxios
    .post(
      `/posts/user/${postdata.userId}/category/${postdata.categoryId}/post`,
      postdata
    )
    .then((response) => response.data);
};

// Get all posts

export const loadAllPosts = (pageNumber, pageSize) => {
  return myAxios.get(`/posts/all?pageNumber=${pageNumber}&pageSize=${pageSize}&sortBy=addedDate&sortDir=desc`).then((response) => response.data);
};

// Fetch a post from pid

export const loadPostById=(pid)=>{
  return myAxios.get("posts/" + pid).then(response => response.data);
};

export const createComment=(comment, pid)=>{
  return privateAxios.post(`/comments/post/${pid}/comment`, comment).then(response => response.data)
}