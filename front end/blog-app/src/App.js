import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import Signup from './pages/Signup';
import Login from './pages/Login';
import About from './pages/About';
import Services from './pages/Services';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function App() {
  return (
    <BrowserRouter>
    <ToastContainer position='bottom-center'/>
    <Routes>
      <Route path="/" element= {<Home/>} />
      <Route path="/login" element= {<Login />} />
      <Route path="/signup" element= {<Signup />} />
      <Route path="/about" element= {<About />} />
      <Route path="/services" element={<Services />} />
    </Routes>
    </BrowserRouter>

  );
} 



export default App;

