import { useEffect, useState } from "react";
import { Button, Card, CardBody, CardHeader, Col, Container, Form, FormGroup, Input, Label, Row } from "reactstrap";
import Base from "../components/Base";
import {signUp} from "../services/user-service";

const Signup=()=>{



    const [data, setData] = useState({
            
        username:'',
        email:'',
        password:'',
        about:''

    })

    const [error, setError] = useState({
        error:{},
        isError: false
    })

    useEffect(() =>{
        console.log(data)
    }, [data])
    
    // handle change
    const handleChange = (event, property) =>{
        
        // dynamic setting the values
        setData({...data, [property]: event.target.value})
    }


    // reseting the form
    const resetData=()=>{
        setData({
        username:'',
        email:'',
        password:'',
        about:''
        })
    }

    // submit the form
    const submitForm=(event)=>{
        event.preventDefault()
        console.log(data);

        // data validate

        // call server api for sending data
        signUp(data).then((resp) => {
            console.log(data);
            console.log("success log")
        }).catch((error) => {
            console.log(error)
            console.log("Error");
        })
    };

    return(
        
        
        // handle change

        

        <Base>
            <Container>

                <Row className="mt-4">

                    {/* {JSON.stringify(data)} */}

                    <Col sm={{size: 6, offset: 3}} >

                        <Card color="dark" outline="true">
                        <CardHeader>
                            <h3> Fill information to register !! </h3>
                        </CardHeader>
                        <CardBody>
                            {/* creating form */}

                            <Form onSubmit={submitForm}>

                                {/*Name Filed*/}
                                <FormGroup>
                                    <Label for="username">Username: </Label>
                                    <Input type="text"
                                     placeholder="Enter username"
                                      id="username"
                                      onChange={(e) => handleChange(e, 'username')}
                                      value={data.username}
                                       />
                                </FormGroup>
                                {/*Name Filed*/}
                                <FormGroup>
                                    <Label for="email">Email:</Label>
                                    <Input type="text"
                                     placeholder="Enter email"
                                      id="email"
                                      onChange={(e) => handleChange(e, 'email')}
                                      value={data.email}

                                      />
                                </FormGroup>
                                {/*Name Filed*/}
                                <FormGroup>
                                    <Label for="password">Password:</Label>
                                    <Input type="text"
                                     placeholder="Enter password"
                                      id="password"
                                      onChange={(e) => handleChange(e, 'password')}
                                      value={data.password}
                                      />
                                </FormGroup>

                                <FormGroup>
                                    <Label for="about">About:</Label>
                                    <Input
                                        id="about"
                                        name="text"
                                        type="textarea"
                                        placeholder="Enter something about yourself !"
                                        style={{height: "250px"}}
                                        onChange={(e) => handleChange(e, 'about')}
                                        value={data.about}
                                    />
                                </FormGroup>

                                <Container className="text-center">
                                    <Button color="dark">Register</Button>
                                    <Button onClick={resetData} color="secondary" type="reset" className="ms-2">Reset</Button>

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