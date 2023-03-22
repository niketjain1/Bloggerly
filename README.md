# Bloggerly - blogging web application

Bloggerly is a user-friendly web application that allows users to publish, view, update, and delete their blog posts.
Users can also browse blog posts published by others and comment on them. The application is built using React and SpringBoot framework, and features a Facebook-style blog feed that recommends trending blogs and blogs from the users a person is following.
It also includes a tagging mechanism for categorizing blogs and secure user authentication using JWT.

### Getting started

To run Bloggerly on your local machine, you will need to have the following software installed:

- Node.js
- Java Development Kit (JDK)
- MySQL

Once you have these dependencies installed, follow these steps:

1. Clone the repository to your local machine using Git.
```
git clone https://github.com/niketjain1/bloggerly.git
```

2. Create a MySQL database for Bloggerly and configure the application properties file (`src/main/resources/application.properties`) with the database connection details:

```
server.port = 5000

spring.datasource.url=jdbc:mysql://localhost:3306/{Your data base name}
spring.datasource.username=root
spring.datasource.password=password
```

3. Run the backend SpringBoot application using the following command in the project root directory:
```
./gradlew bootRun
``` 

4. In a separate terminal, navigate to the `frontend` directory and install the required Node.js packages using following commands:
```
cd frontend
npm install
```

5. Start the frontend React application using the following command:
```
npm start
```

6. Open your web browser and navigate to `http://localhost:3000` to access the Bloggerly application.

## Usage

### Registering and logging in

You must first create an account by registering with your email and a password. Once registered, you can log in with your email and password.

### Creating a blog post

To create a new blog post, click the "New Post" button in the header navigation or click on you username which will redirect you to the add new post section. You will be prompted to enter a title, content, set the image, and select category for your post. Once you have filled in the necessary details, click the "Publish" button to publish your post to the Bloggerly feed.

### Browsing and searching for blog posts

The Bloggerly feed displays a list of all published blog posts, sorted by most recent first. You can browse the feed to view posts by other users. 

### Commenting on blog posts

To leave a comment on a blog post, simply scroll to the bottom of the post and enter your comment in the text box. Click the "Submit" button to submit your comment. You can view comments on a post by clicking the "Comments" button below the post content.

### Updating and deleting blog posts

To update or delete one of your own blog posts, navigate to the post in the feed and click the "Edit" or "Delete" button. You will be prompted to confirm your action before proceeding.

## Api documentation
The api documentation is available at: http://blogapp-env-1.eba-74nfb38m.us-east-2.elasticbeanstalk.com/swagger-ui/index.html



