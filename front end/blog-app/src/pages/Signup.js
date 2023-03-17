import { useEffect, useState } from "react";
import { Button, Card, CardBody, CardHeader, Col, Container, Form, FormFeedback, FormGroup, Input, Label, Row } from "reactstrap";
import Base from "../components/Base";
import {signUp} from "../services/user-service";
import {toast} from "react-toastify";
const Signup=()=>{



    const [data, setData] = useState({
            
        username:'',
        email:'',
        password:'',
        about:'',

    })

    const [error, setError] = useState({
        errors:{},
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

        // if(error.isError){
        //     toast.error("Form data is invalid, correct all the details and then submit.")
        //     setError({...error, isError:false})
        //     return;
        // }

        console.log(data);

        // data validate

        // call server api for sending data
        signUp(data).then((resp) => {
            console.log(data);
            console.log("success log")
            toast.success("User is registered successfully !!")
            setData({
                username:'',
                email:'',
                password:'',
                about:'',
            })
        }).catch((error) => {
            console.log(error)
            console.log("Error");

            if(error.response.status === 400 || error.response.status === 404 || error.response.status === 500){
                toast.error(error.response.data.message)

            }
            // handle errors in proper way
            setError({
                errors:error,
                isError:true
            })

        })
    };

    return(
        
        
        // handle change

        

        <Base>
            <Container>

                <Row className="mt-4">

                    {/* {JSON.stringify(data)} */}

                    <Col sm={{size: 6, offset: 3}} >

                        <Card color="dark" inverse >
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
                                      invalid={error.errors?.response?.data?.username ? true: false}
                                       />

                                    <FormFeedback>
                                    {error.errors?.response?.data?.username}
                                    </FormFeedback>
                                </FormGroup>
                                {/*Email Filed*/}
                                <FormGroup>
                                    <Label for="email">Email:</Label>
                                    <Input type="email"
                                     placeholder="Enter email"
                                      id="email"
                                      onChange={(e) => handleChange(e, 'email')}
                                      value={data.email}
                                      invalid={error.errors?.response?.data?.email ? true: false}

                                      />
                                    <FormFeedback>
                                        {error.errors?.response?.data?.email}
                                    </FormFeedback>
                                </FormGroup>
                                {/*Password Filed*/}
                                <FormGroup>
                                    <Label for="password">Password:</Label>
                                    <Input type="password"
                                     placeholder="Enter password"
                                      id="password"
                                      onChange={(e) => handleChange(e, 'password')}
                                      value={data.password}
                                      invalid={error.errors?.response?.data?.password ? true: false}

                                      />
                                    <FormFeedback>
                                        {error.errors?.response?.data?.password}
                                    </FormFeedback>
                                </FormGroup>
      
                                {/*About Filed*/}
                                <FormGroup>
                                    <Label for="about">About:</Label>
                                    <Input
                                        id="about"
                                        name="textarea"
                                        type="textarea"
                                        placeholder="Enter something about yourself !"
                                        style={{height: "250px"}}
                                        onChange={(e) => handleChange(e, 'about')}
                                        value={data.about}
                                        invalid={error.errors?.response?.data?.about ? true: false}

                                    />
                                    <FormFeedback>
                                        {error.errors?.response?.data?.about}
                                    </FormFeedback>
                                </FormGroup>
                                {/*Button Filed*/}
                                <Container className="text-center">
                                    <Button color="primary" inverse="true">Register</Button>
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