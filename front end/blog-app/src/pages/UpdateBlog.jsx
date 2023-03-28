import React, { useRef, useContext, useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import { toast } from 'react-toastify'
import Base from '../components/Base'
import userContext from '../context/UserContext'
import { loadPostById, updatePostById } from '../services/Post-service'
import { loadAllCategories } from '../services/Category-service'
import { Button, Card, CardBody, Form, Input, Label, Container } from 'reactstrap'
import JoditEditor from 'jodit-react';

function UpdateBlog() {
    
    const editor = useRef(null)

    const [categories, setCategories] = useState([])
    const { id } = useParams()
    const object = useContext(userContext)
    const naviate = useNavigate()
    const [post, setPost] = useState(null)

    useEffect(() => {

        loadAllCategories().then((data) => {
            // console.log(data)
            setCategories(data)
        }).catch(error => {
            console.log(error)
        })


        // load the blog from database
        loadPostById(id).then(data => {
            setPost({...data, categoryId: data.category.id})

        }).catch(error => {
            console.log(error)
            toast.error("Error in loading the post")
        })
    }, [])


    useEffect(() => {
        if (post) {
            // console.log(object)
            // console.log(post)
            if (post.user.id !== object.user.data.id) {

                toast.error("This is not your post !")
                naviate("/")
            }
        }

    }, [post])
    
    const handleChange=(event, fieldName)=>{
        setPost({
            ...post,
            [fieldName]: event.target.value
        })
    }
    
    const updatePost=(event)=>{
        event.preventDefault()
        // console.log(post)
        updatePostById({...post, category: {id: post.categoryId}}, post.pid)
        .then(response => {
            // console.log(response)
            toast.success("Post updated successfully !")
            naviate("/")
        }).catch(error => {
            console.log(error)
            toast.error("Error in updating the post !")
        })
    }

    const updateHtml = () => {
        return (
            <div className='wrapper'>
                <Card className='shadow mt-4 border'>
                    <CardBody>
                        {/* {JSON.stringify(post)} */}
                        <h3>Update Post</h3>
                        <Form onSubmit={updatePost}>

                            <div className='my-3'>
                                <Label for='title'>Post Title</Label>
                                <Input
                                    type="text"
                                    id='title'
                                    name='title'
                                    placeholder='Enter Here'
                                    value={post.title}
                                    onChange={(event) => handleChange(event,'title')}

                                />
                            </div>



                            <div className='my-3'>
                                <Label for='content'>Post Content</Label>
                                {/* <Input
                                type="textarea"
                                id='content'
                                placeholder='Enter Here'
                                style={{
                                    height: "300px"

                                }}
                            /> */}

                                <JoditEditor
                                    ref={editor}
                                    value={post.content}
                                    onBlur={newContent => setPost({...post, content: newContent})}
                                    // onChange={''}
                                // {event => {
                                //     if (event.target) {
                                //       setPost({ ...post, 'content': event.target.innerHTML })
                                //     }
                                // }
                                // }
                                // onChange={contentFieldChanged}

                                />


                            </div>

                            {/*File Field*/}

                            <div className='mt-3'>
                                <Label for="image">
                                    Image:
                                </Label>
                                <Input
                                    id="image"
                                    type="file"
                                    // onChange={''}
                                    multiple
                                    accept='image/*'
                                />

                            </div>
                            <div className='my-3'>
                                <Label for='category'>Post Category</Label>
                                <Input
                                    type="select"
                                    id='category'
                                    placeholder='Enter Here'
                                    name='categoryId'
                                    onChange={(event) => handleChange(event, 'categoryId')}
                                    value={post.categoryId}
                                >

                                    <option disabled value={0}>---Select some category---</option>
                                    {

                                        categories.map((category) => (
                                            <option key={category.id} value={category.id}>
                                                {category.categoryTitle}
                                            </option>
                                        ))

                                    }
                                </Input>

                            </div>

                            <Container className='text-center'>
                                <Button type='submit' color='primary'>
                                    Update post
                                </Button>
                                <Button color='danger' className='ms-2'>
                                    Reset
                                </Button>
                            </Container>

                        </Form>
                    </CardBody>
                </Card>

            </div>
        )
    }

    return (
        <Base>
            <Container>
                {post && updateHtml()}
            </Container>
        </Base>
    )
}

export default UpdateBlog