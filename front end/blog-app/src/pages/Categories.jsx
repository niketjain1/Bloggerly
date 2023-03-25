import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import Base from '../components/Base'
import { Row, Col, Container } from "reactstrap";
import CategorySideMenu from "../components/CategorySideMenu";
import { loadPostByCategoryId } from '../services/Post-service';
import { toast } from 'react-toastify';
import Post from '../components/Post';
function Categories() {

    const { id } = useParams()
    const[posts, setPosts] = useState([])

    useEffect(() => {
        console.log(id)
        loadPostByCategoryId(id).then((data) => {
            setPosts([...data])
        }).catch(error => {
            console.log(error)
            toast.error("Error in loading posts !")
        })
    }, [id])
    return (
        <Base>
            <Container className="mt-3">
                <Row>
                    <Col md={2} className="pt-5">
                        <CategorySideMenu />
                    </Col>
                    <Col md={10}>
                        {
                            posts && posts.map((post, index) => {
                                return(
                                    <Post key={index} post={post} />
                                )
                            })
                        }

                        {
                            posts.length <= 0 ? <h1 className='mt-5'>No posts in this category.</h1> : '' 
                        }
                    </Col>
                </Row>
            </Container>
        </Base>
    )
}

export default Categories