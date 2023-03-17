import Base from "../components/Base";
import { Form, FormGroup, Label, Input, Button, Container, Row, CardBody, Col, Card, CardHeader } from "reactstrap";
import { useState } from "react";
import {toast} from "react-toastify"
import { login } from "../services/user-service";
import { doLogin } from "../Auth";
import { useNavigate } from "react-router-dom";


const Login =()=>{

    const[loginDetail, setLoginDetail] = useState({
        username:'',
        password:''
    })

    const navigate = useNavigate()



    const handleChange = (event, field) =>{
        let actualValue = event.target.value
        setLoginDetail({
            ...loginDetail,
            [field]:actualValue
        })
    }

    const handleFormSubmit = (event) =>{
        event.preventDefault();
        console.log(loginDetail);
        
        // Validation
        if(loginDetail.username.trim() ===''){
            toast.error("Username is required !")
        }
        if(loginDetail.password.trim() ===''){
            toast.error("Password is required !")
        }

        // Submit data on the server side to generated token

        login(loginDetail).then((data) => {
            console.log("User Login:")
            console.log(data)

            // save data on local storage
            doLogin(data, () =>{
                console.log("Login detail saved successfully in local storage")
                // redirect to user dashboard page after login
                navigate("/user/dashboard")


            })

            toast.success("Login Successful !!")
        }).catch(error => {
            if(error.response.status === 400 || error.response.status === 404){
                toast.error(error.response.data.message)
            }else{
                // console.error("Something went wrong on server !!")

            }
            })
    }

    const resetFormData=()=>{
        setLoginDetail({
            username:'',
            password:''
        })
    }

    return(
        <Base>
            <Container>
                    <Row className="mt-4">
                    <Col sm={{size: 6, offset: 3}} >
                        <Card color="dark" inverse>
                            <CardHeader>
                                <h3> Login here !! </h3>
                            </CardHeader>
                            <CardBody>
                                
                                <Form onSubmit={handleFormSubmit}>
                                {/*Username Field*/}

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
                                    type="text"
                                    value={loginDetail.username}
                                    onChange={(e) =>handleChange(e, 'username')}
                                />
                                </FormGroup>
                                {' '}

                                {/*Password Field*/}
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
                                    value={loginDetail.password}
                                    onChange={(e) =>handleChange(e, 'password')}
                                />
                                </FormGroup>
                                <Container className="text-center">
                                    <Button color="primary" >Submit</Button>
                                    <Button onClick={resetFormData} color="secondary" type="reset" className="ms-2">Reset</Button>

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