import Base from "../components/Base";
import UserContext from "../context/UserContext";
import { Card, CardBody, CardTitle, CardText, CardHeader, Container } from "reactstrap";
const About = () => {
  return (
    <UserContext.Consumer>
      {(object) => (
        <Base>
          {/* <h1>this is an about page</h1>
          <p>we are building blog website</p>
          <h3>Welcome user: {
            object.user.login && object.user.data.username}</h3> */}

          <Container>
          <Card
            className="mt-4"
            style={{backgroundColor: '#eceff1' , borderColor:"#212121"}}
            
          >
            <CardHeader className="text-center"><h2>Welcome to Bloggerly</h2></CardHeader>
            <CardBody>
              <CardTitle tag="h3">Special Title Treatment</CardTitle>
              <CardText>
                With supporting text below as a natural lead-in to additional
                content.
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
