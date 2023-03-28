import { Row, Col, Container } from "reactstrap";
import Base from "../components/Base";
import CategorySideMenu from "../components/CategorySideMenu";
import NewFeed from "../components/NewFeed";
import WelcomePage from "../components/WelcomePage";

const Home = () => {
  return (
    
    <Base>
      <Container className="mt-3">
        <Row>
          <Col md={3} className="pt-1 mt-1">
            <CategorySideMenu />
          </Col>
          <Col md={9}>
            <NewFeed />
          </Col>
        </Row>
      </Container>
    </Base>
  );
};

export default Home;
