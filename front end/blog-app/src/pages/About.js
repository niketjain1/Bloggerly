import Base from "../components/Base";
import UserContext from "../context/UserContext";
import {
  Card,
  CardBody,
  CardTitle,
  CardText,
  CardHeader,
  Container,
} from "reactstrap";
import "../AboutPage.css";
const About = () => {
  return (
    <UserContext.Consumer>
      {(object) => (
        <Base>
          {/* <h1>this is an about page</h1>
          <p>we are building blog website</p>
          <h3>Welcome user: {
            object.user.login && object.user.data.username}</h3> */}

          <Container >
            <Card
              className="mt-4 about-page"
            //  style={{ backgroundColor: "#F5F5F5", borderColor: "#212121" }}
            >
              <CardHeader className="text-center">
                <h2>About Bloggerly</h2>
              </CardHeader>
              <CardBody>
                <CardText className="description">
                  Bloggerly is a user-friendly web application that provides a
                  platform for bloggers to share their thoughts, opinions, and
                  experiences with the world. With its intuitive interface and
                  advanced features, Bloggerly makes it easy for users to
                  publish, view, update, and delete their blog posts, as well as
                  browse and comment on posts published by others.
                </CardText>
                <CardTitle className="h3">Features:</CardTitle>
                <CardText>
                <ul className="feature-list">
                  <li>User-friendly interface</li>
                  <li>Trending blog feed</li>
                  <li>Tagging mechanism</li>
                  <li>Secure user authentication</li>
                  <li>Commenting system</li>
                </ul>
                </CardText>
                <CardTitle className="h3">Technology Used:</CardTitle>

                <CardText className="technology-description">
                Bloggerly is built using modern and efficient technologies to ensure that the application is fast, reliable, and scalable. The backend is built using the SpringBoot framework, while the frontend is built using React.
                I have used MySQL for the database and employ the latest security practices to ensure the safety of the users' data.
                </CardText>
              </CardBody>
            </Card>
          </Container>
        </Base>
      )}
    </UserContext.Consumer>

    // <Base>
    //     <h1>this is an about page</h1>
    //     <p>we are building blog website</p>
    // </Base>
  );
};

export default About;
