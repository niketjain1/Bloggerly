import { useEffect, useState } from "react"
import { Link, useParams } from "react-router-dom"
import { Card, CardBody, CardText, Col, Container, Row } from "reactstrap"
import Base from "../components/Base"
import { loadPostById } from "../services/Post-service"
import { toast } from "react-toastify"
import { BASE_URL } from "../services/Helper"


const PostPage = () => {

    const { pid } = useParams()
    const [post, setPost] = useState(null)

    const printDate = (numbers) => {
        return new Date(numbers).toLocaleDateString()
    }

    useEffect(() => {
        // load post from pid
        loadPostById(pid).then((data) => {
            console.log(data)
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
                                            <img className="img-fluid" src={BASE_URL + "/posts/image/" + post.imageName} alt=" " />
                                        </div>
                                        <CardText className="mt-5" dangerouslySetInnerHTML={{ __html: post.content }}>

                                        </CardText>
                                    </CardBody>
                                )
                            }
                        </Card>

                    </Col>
                </Row>
            </Container>

        </Base>
    )
}

export default PostPage