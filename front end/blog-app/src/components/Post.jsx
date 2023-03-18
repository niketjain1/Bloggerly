import React from 'react'
import { Link } from 'react-router-dom'
import { Card, CardBody, CardText } from 'reactstrap'

function Post({post={title:"This is default post title", content: "This is a default content"}}) {
  return (

    <Card className='border-0 shadow mt-3'>
        <CardBody>
            <h1>{post.title}</h1>
            <CardText>
                <div dangerouslySetInnerHTML={{ __html: post.content.substring(0, 60) +"...."}}></div>
            </CardText>

            <div>
                <Link className='btn btn-secondary' to={'/posts/' + post.pid}>Read More</Link>
            </div>
        </CardBody>
    </Card>

    )
}

export default Post