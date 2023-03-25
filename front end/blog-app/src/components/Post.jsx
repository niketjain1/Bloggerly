import React, { useContext, useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import { Button, Card, CardBody, CardText } from 'reactstrap'
import { getCurrentUserDetail, isloggedin } from '../Auth'
import userContext from '../context/UserContext'
import parse from 'html-react-parser'
import LinesEllipsis from 'react-lines-ellipsis'
import '../Form.css'

function Post({ post = { id: -1, title: "This is default post title", content: "This is a default content" }, deletePost }) {
    const [user, setUser] = useState(null)
    const [login, setLogin] = useState(null)
    useEffect(() => {
        setUser(getCurrentUserDetail())
        setLogin(isloggedin())
    }, [])
    const userContextData = useContext(userContext)

    const parsedContent = parse(post.content)
    const shortenedContent = document.createElement('div');
  shortenedContent.innerHTML = post.content;
  const textContent = shortenedContent.innerText.substring(0, 100) + '...';

    return (

        <Card className='border-0 shadow mt-3'>
            <CardBody>
                <h3>{post.title}</h3>
                {/* <CardText dangerouslySetInnerHTML={{ __html: post.content.substring(0, 60) +"...."}}> */}

                <CardText>
                    {/* <LinesEllipsis
                        text={contentString}
                        maxLine="3"
                        ellipsis="..."
                        trimRight
                        basedOn="words"
                    /> */}
                    {/* {parse(post.content)} */}
                    {/* <div dangerouslySetInnerHTML={{ __html: parse(post.content) }} /> */}

                    {textContent}
                
                </CardText>


                {/* <CardText>{ReactHtmLParser(post.content.substring(0, 60) + "...")}</CardText>     */}

                <div>
                    <Link className='btn btn-secondary' to={'/posts/' + post.pid}>Read More</Link>

                    {userContextData.user.login && ((user && user.id === post.user.id) ? <Button onClick={() => deletePost(post)} color='danger' className='ms-2'>Delete</Button> : '')}
                    {userContextData.user.login && ((user && user.id === post.user.id) ? <Button tag={Link} to={`/user/update-blog/${post.pid}`} color='warning' className='ms-2'>Update</Button> : '')}

                </div>
            </CardBody>
        </Card>

    )
}

export default Post