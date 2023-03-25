import React, { useContext, useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import { Button, Card, CardBody, CardText } from 'reactstrap'
import { getCurrentUserDetail, isloggedin } from '../Auth'
import userContext from '../context/UserContext'

function Post({post={id: -1, title:"This is default post title", content: "This is a default content"}, deletePost}) {
    const [user, setUser] = useState(null)
    const [login, setLogin] = useState(null)
    useEffect(() => {
        setUser(getCurrentUserDetail())
        setLogin(isloggedin())
    }, [])
    const userContextData = useContext(userContext)
    
  return (

    <Card className='border-0 shadow mt-3'>
        <CardBody>
            <h3>{post.title}</h3>
            <CardText dangerouslySetInnerHTML={{ __html: post.content.substring(0, 60) +"...."}}>
            </CardText>

            {/* <CardText>{ReactHtmLParser(post.content.substring(0, 60) + "...")}</CardText>     */}
            
            <div>
                <Link className='btn btn-secondary' to={'/posts/' + post.pid}>Read More</Link>

                {userContextData.user.login && ((user && user.id === post.user.id) ? <Button onClick={() => deletePost(post)} color='danger' className='ms-2'>Delete</Button> :'')}
                {userContextData.user.login && ((user && user.id === post.user.id) ? <Button tag={Link} to={`/user/update-blog/${post.pid}`} color='warning' className='ms-2'>Update</Button> :'')}

            </div>
        </CardBody>
    </Card>

    )
}

export default Post