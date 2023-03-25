import React, {  useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import { Card, CardBody, Col, Container, Row, Table } from 'reactstrap'
import Base from '../../components/Base'
import { getUserById } from '../../services/user-service'
import { loadPostByUserId, deletePostById } from "../../services/Post-service";
import Post from "../../components/Post";
import { toast } from 'react-toastify'

function ProfileInfo() {

  const { id } = useParams()
  // console.log(id)
  const [user, setUser] = useState(null)
  const [posts, setPosts] = useState([])

  useEffect(() => {
    getUserById(id).then(data => {
      // console.log(data)
      setUser({ ...data })
      loadPostDataByUserId()
    })

  }, [])

  function loadPostDataByUserId(){ 
    loadPostByUserId(id).then((data) => {
    // console.log(data)
    setPosts([...data].reverse())
  }).catch(error => {
    console.log(error)
  })
  }

  // function to delete post

  function deletePost(post){
    deletePostById(post.pid).then((response) => {
        console.log(response)
        toast.success("Post deleted successfully !")
        loadPostDataByUserId()
    }).catch(error => {
        console.log(error)
        toast.error("Error in deleting post !")
    })
}

  const userView = () => {
    return (
      <Row>
        <Col md={{ size: 8, offset: 2 }}>
          <Card className='mt-2 border-0 shadow rounded-0'>
            <CardBody>
              <h3 className='text-uppercase'>User Information</h3>

              <Container className='text-center'>
                <img
                  style={{ maxWidth: '200px', maxHeight: '200px' }}
                  src='https://st.depositphotos.com/2101611/3925/v/600/depositphotos_39258143-stock-illustration-businessman-avatar-profile-picture.jpg'
                  alt='user profile pic'
                  className='image-fluid rounded-circle'
                />
              </Container>
              <Table responsive striped hover={true} bordered={true} className="text-center mt-5">
                <tbody>
                  <tr>
                    <td>
                      UserId:
                    </td>
                    <td>
                      {user.id}
                    </td>
                  </tr>
                  <tr>
                    <td>
                      Username:
                    </td>
                    <td>
                      {user.username}
                    </td>
                  </tr>
                  <tr>
                    <td>
                      Email:
                    </td>
                    <td>
                      {user.email}
                    </td>
                  </tr>
                  <tr>
                    <td>
                      About:
                    </td>
                    <td>
                      {user.about}
                    </td>
                  </tr>
                  <tr>
                    <td>
                      Role:
                    </td>
                    <td>
                      {user.roles.map((role) => {
                        return (
                          <span key={role.id}>{role.name}</span>
                        )
                      })}
                    </td>
                  </tr>
                </tbody>
              </Table>
            </CardBody>
          </Card>
          <h2 className="mt-3">Posts count: ({posts.length})</h2>
          {posts.map((post, index) => {
            return (
              <Post post={post} key={index} deletePost={deletePost}/>
            )
          })}
        </Col>
      </Row>
    )
  }


  return (
    <Base>
      {user ? userView() : 'Loading user data...'}
    </Base>
  )
}

export default ProfileInfo