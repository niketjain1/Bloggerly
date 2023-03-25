import Base from "../../components/Base";
import { Container } from "reactstrap";
import AddPost from "../../components/AddPost";
// import { getCurrentUserDetail } from "../../Auth";
// import { loadPostByUserId } from "../../services/Post-service";
// import Post from "../../components/Post";

const UserDashboard = () => {

    // const [user, setUser] = useState({})
    // const [posts, setPosts] = useState([])

    // useEffect(() => {
    //     setUser(getCurrentUserDetail())

    //     loadPostByUserId(getCurrentUserDetail().id).then((data) => {
    //         console.log(data)
    //         setPosts([...data].reverse())
    //     }).catch(error => {
    //         console.log(error)
    //     })
    // }, [])


    return (
        <Base>

            <Container>

                <AddPost />
            </Container>
        </Base>
    )
}

export default UserDashboard