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
  return myAxios
    .get(
      `/posts/all?pageNumber=${pageNumber}&pageSize=${pageSize}&sortBy=addedDate&sortDir=desc`
    )
    .then((response) => response.data);
};

// Fetch a post from pid

export const loadPostById = (pid) => {
  return myAxios.get("posts/" + pid).then((response) => response.data);
};

// create comment
export const createComment = (comment, pid) => {
  return privateAxios
    .post(`/comments/post/${pid}/comment`, comment)
    .then((response) => response.data);
};

// Upload post image

export const uploadPostImage = (image, pid) => {
  let formData = new FormData();
  formData.append("image", image);
  return privateAxios
    .post(`/posts/image/upload/${pid}`, formData)
    .then((response) => response.data);
};

// Get category wise posts

export const loadPostByCategoryId = (id) => {
  return myAxios.get(`/posts/category/${id}/posts`).then((response) => response.data)
}

// Get posts by userId

export const loadPostByUserId = (id) => {
  return myAxios.get(`/posts/user/${id}/posts`).then(response => response.data)
}

// Delete Post

export const deletePostById = (pid) => {
  return privateAxios.delete(`/posts/${pid}`).then(response => response.data)
}

// update Post

export const updatePostById = (post, pid) => {
  // console.log(post)
  return privateAxios.put(`/posts/${pid}`, post).then(response => response.data)
}