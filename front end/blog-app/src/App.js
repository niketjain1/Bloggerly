import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import Signup from "./pages/Signup";
import Login from "./pages/Login";
import About from "./pages/About";
import Services from "./pages/Services";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import UserDashboard from "./pages/User-Routes/UserDashboard";
import Privateroute from "./components/Privateroute";
import ProfileInfo from "./pages/User-Routes/ProfileInfo";
import PostPage from "./pages/PostPage";
import Categories from "./pages/Categories";
import UserProvider from "./context/UserProvider";
import UpdateBlog from "./pages/UpdateBlog";

function App() {
  return (
    <UserProvider>
      <BrowserRouter>
        <ToastContainer position="bottom-center" />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<Signup />} />
          <Route path="/about" element={<About />} />
          
          <Route path="/posts/:pid" element={<PostPage />} />
          <Route path="/categories/:id" element={<Categories />} />

          <Route path="/user" element={<Privateroute />}>
            <Route path="dashboard" element={<UserDashboard />} />
            <Route path="profile-info/:id" element={<ProfileInfo />} />
            <Route path="update-blog/:id" element={<UpdateBlog />} />
            <Route path="services" element={<UserDashboard />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </UserProvider>
  );
}

export default App;
