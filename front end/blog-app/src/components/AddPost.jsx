import React, { useState, useEffect, useRef } from 'react'
import { Button, Card, CardBody, Form, Input, Label, Container } from 'reactstrap'
import { loadAllCategories } from '../services/Category-service'
import JoditEditor from 'jodit-react';
import { toast } from "react-toastify"
import { savePost } from '../services/Post-service';
import { getCurrentUserDetail } from '../Auth';

function AddPost() {

    const editor = useRef(null)
    // const [content, setContent] = useState('')
    const [categories, setCategories] = useState([])

    // const config = useMemo({
    //     placeholder: "Start typing..."
    // }, [placeholder])

    const [user, SetUser] = useState(undefined)

    const [post, setPost] = useState({
        title: '',
        content: '',
        categoryId: ''
    })


    useEffect(
        () => {

            SetUser(getCurrentUserDetail())
            // console.log(user)
            loadAllCategories().then((data) => {
                // console.log(data)
                setCategories(data)
            }).catch(error => {
                console.log(error)
            })
        },
        []
    )

    const fieldChanged = (event) => {
        setPost({
            ...post,
            [event.target.name]: event.target.value
        })
    }

    const contentFieldChanged = (data) => {
        setPost({ ...post, 'content': data })
    }

    // create post function

    const createPost = (event) => {
        event.preventDefault();
        // console.log(post)

        if (post.title.trim() === '') {
            toast.error("Title is required !!")
            return;
        }
        if (post.content.trim() === '') {
            toast.error("Content is required !!")
            return;
        }
        if (post.content.trim() === '') {
            toast.error("Content is required !!")
            return;
        }
        if (post.categoryId.trim() === '') {
            toast.error("Select some category")
            return;
        }

        // submit the form on server

        post['userId'] = user.id
        savePost(post).then(data => {
            toast.success("Post created")
            // console.log(post)
            setPost({
                title: '',
                content: '',
                categoryId: ''
            })
        }).catch((error) => {
            toast.error("Error")
            // console.log(error)
        })

    }



    return (
        <div className='wrapper'>

            <Card className='shadow mt-4 border'>
                <CardBody>
                    {/* {JSON.stringify(post)} */}
                    <h3>What's going in your mind?</h3>
                    <Form onSubmit={createPost}>

                        <div className='my-3'>
                            <Label for='title'>Post Title</Label>
                            <Input
                                type="text"
                                id='title'
                                name='title'
                                placeholder='Enter Here'
                                onChange={fieldChanged}

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
                                // onBlur={newContent => setContent(newContent)}
                                onChange={contentFieldChanged}

                            />


                        </div>

                        <div className='my-3'>
                            <Label for='category'>Post Category</Label>
                            <Input
                                type="select"
                                id='category'
                                placeholder='Enter Here'
                                name='categoryId'
                                onChange={fieldChanged}
                                defaultValue={0}
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
                                Create
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

export default AddPost