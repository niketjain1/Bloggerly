import React from 'react'
import { Button, Card, CardBody, CardText } from 'reactstrap'

function Post({post={title:"This is default post title", content: "This is a default content"}}) {
  return (

    <Card className='border-0 shadow mt-3'>
        <CardBody>
            <h1>{post.title}</h1>
            <CardText dangerouslySetInnerHTML={{ __html: post.content.substring(0, 100)}}>
                
            </CardText>

            <div>
                <Button>Read More</Button>
            </div>
        </CardBody>
    </Card>

    )
}

export default Post