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
  return myAxios.get(`/posts/all?pageNumber=${pageNumber}&pageSize=${pageSize}`).then((response) => response.data);
};
