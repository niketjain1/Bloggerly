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
          <Col md={3} className="pt-5 mt-3">
            <h3>Categories</h3>
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
