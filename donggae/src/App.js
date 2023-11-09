import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import Home from './components/mainpage/Mainpage';
import Posting from './components/Post/Posting';
import Post from './components/Post/Post';
import Application from './components/Application/Application';
import Login from './components/Login/Login';
import Signup from "./components/Signup/Signup";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/posting" element={<Posting />} />
          <Route path="/post" element={<Post />} />
          <Route path="/application" element={<Application />} />
          <Route path="/Login" element={<Login />} />
          <Route path="/Signup" element={<Signup />}></Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;

