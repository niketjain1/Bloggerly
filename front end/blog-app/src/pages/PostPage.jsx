import { useEffect, useState } from "react"
import { Link, useParams } from "react-router-dom"
import { Button, Card, CardBody, CardText, Col, Container, Input, Row } from "reactstrap"
import Base from "../components/Base"
import { createComment, loadPostById } from "../services/Post-service"
import { toast } from "react-toastify"
import { BASE_URL } from "../services/Helper"
import { isloggedin } from "../Auth"


const PostPage = () => {

    const { pid } = useParams()
    const [post, setPost] = useState(null)

    const printDate = (numbers) => {
        return new Date(numbers).toLocaleDateString()
    }

    const[comment, setComment] = useState({
        content:''
    })

    const submitComment=()=>{

        if(!isloggedin()){
            toast.error("Login first ")
            return;
        }

        if(comment.content.trim()===""){
            toast.error("Comment cannot be blank !")
            return;
        }
        createComment(comment, pid).then(data =>{
            console.log(data)
            toast.success("Comment added.")
            setPost({
                ...post,
                comments:[...post.comments, data]
            })
            setComment({
                content:''
            })
        }).catch(error =>{
            console.log(error)
        })
    }
     
    useEffect(() => {
        // load post from pid
        loadPostById(pid).then((data) => {
            // console.log(data)
            setPost(data)
        }).catch(error => {
            console.log(error)
            toast.error("Error in loading post")
        })

    }, [pid])

    return (
        <Base>

            <Container className="mt-4">

                <Link to={"/"}>Home</Link> / {post && (<Link to="">{post.title}</Link>)}

                <Row>
                    <Col md={{
                        size: 12
                    }}>

                        <Card className="mt-3 ps-2 border-0 shadow">
                            {
                                (post) && (
                                    <CardBody>
                                        <CardText>
                                            Posted By <b>{post.user.username}</b> on <b>{printDate(post.addedDate)}</b>
                                        </CardText>

                                        <CardText>
                                            <span className="text-muted">{post.category.categoryTitle}</span>
                                        </CardText>

                                        <div className="divider" style={{
                                            width: '100%',
                                            height: '1px',
                                            background: '#e2e2e2'
                                        }}></div>
                                        <CardText className="mt-3">
                                            <h1>{post.title}</h1>
                                        </CardText>
                                        <div className="image-container mt-4 shadow" style={{
                                            maxWidth: '50%'
                                        }}>
                                            
                                            <img className="img-fluid" src={
                                                post.imageName != "Default.png"
                                                ? BASE_URL + "/posts/image/" + post.imageName 
                                                : "https://images.pexels.com/photos/1591056/pexels-photo-1591056.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
                                            } alt=" " />
                                        </div>
                                        <CardText className="mt-5" dangerouslySetInnerHTML={{ __html: post.content }}>

                                        </CardText>
                                    </CardBody>
                                )
                            }
                        </Card>

                    </Col>
                </Row>

                <Row className="my-4">
                    <Col md={{
                        size: 9,
                        offset: 1
                    }}>
                        <h3>Comments ({post ? post.comments.length : 0})</h3>
                        {
                            post && post.comments.map((c, index) => (
                                <Card className="mt-2 border-0" key={index}>
                                    <CardBody>
                                        <CardText>{c.content}</CardText>
                                    </CardBody>
                                </Card>
                            ))
                        }

                        <Card className="mt-2 border-0">
                            <CardBody>
                                <CardText>Add a comment:</CardText>
                                <Input
                                type="textarea" 
                                placeholder="Enter Comment"
                                value={comment.content}
                                onChange={(event) => setComment({content:event.target.value})}
                                />
                                <Button onClick={submitComment} className="mt-2" color="primary">Submit</Button>
                            </CardBody>
                        </Card>

                    </Col>
                </Row>
            </Container>

        </Base>
    )
}

export default PostPage