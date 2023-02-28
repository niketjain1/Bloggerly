import { Button, Card, CardBody, CardHeader, Col, Container, Form, FormGroup, Input, Label, Row } from "reactstrap";
import Base from "../components/Base";

const Signup=()=>{
    return(

        <Base>
            <Container>

                <Row className="mt-4">

                    <Col sm={{size: 6, offset: 3}} >

                        <Card color="dark" outline="true">
                        <CardHeader>
                            <h3> Fill information to register !! </h3>
                        </CardHeader>
                        <CardBody>
                            {/* creating form */}

                            <Form>

                                {/*Name Filed*/}
                                <FormGroup>
                                    <Label for="username">Username:</Label>
                                    <Input type="text" placeholder="Enter username" id="username" />
                                </FormGroup>
                                {/*Name Filed*/}
                                <FormGroup>
                                    <Label for="email">Email:</Label>
                                    <Input type="text" placeholder="Enter email" id="email" />
                                </FormGroup>
                                {/*Name Filed*/}
                                <FormGroup>
                                    <Label for="password">Password:</Label>
                                    <Input type="text" placeholder="Enter password" id="password"/>
                                </FormGroup>

                                <FormGroup>
                                    <Label for="about">About:</Label>
                                    <Input
                                        id="about"
                                        name="text"
                                        type="textarea"
                                        placeholder="Enter something about yourself !"
                                        style={{height: "250px"}}
                                    />
                                </FormGroup>

                                <Container className="text-center">
                                    <Button color="dark">Register</Button>
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

export default Signup;