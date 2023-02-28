import Base from "../components/Base";
import { Form, FormGroup, Label, Input, Button, Container, Row, CardBody, Col, Card, CardHeader } from "reactstrap";

const Login =()=>{
    return(
        <Base>
            <Container>
                    <Row className="mt-4">
                    <Col sm={{size: 6, offset: 3}} >
                        <Card color="dark" outline="true">
                            <CardHeader>
                                <h3> Login here !! </h3>
                            </CardHeader>
                            <CardBody>
                                <Form>
                                <FormGroup>
                                    <Label
                                    for="username"
                                    >
                                    Username:
                                    </Label>
                                    <Input
                                    id="username"
                                    name="username"
                                    placeholder="Enter username"
                                    type="username  "
                                    />
                                </FormGroup>
                                {' '}
                                <FormGroup>
                                    <Label
                                    for="password"
                                    >
                                    Password:
                                    </Label>
                                    <Input
                                    id="password"
                                    name="password"
                                    placeholder="Enter Password"
                                    type="password"
                                    />
                                </FormGroup>
                                <Container className="text-center">
                                    <Button color="dark">Submit</Button>
                                    <Button color="secondary" type="reset" className="ms-2">Reset</Button>

                                </Container>
                                
                                </Form>
                                
                                </CardBody>
                            </Card>
                        </Col>
                    </Row>
            </Container>
        </Base> 
    )
}

export default Login;