import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import Home from './components/mainpage/Mainpage';
import Posting from './components/Post/Posting';
import Post from './components/Post/Post';
import Login from './components/Login/Loginpage';
import Signup from "./components/Signup/Signuppage";
import SignupGit from './components/Signup/withGitHub';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/posting" element={<Posting />} />
          <Route path="/post" element={<Post />} />
          <Route path="/Loginpage" element={<Login />} />
          <Route path="/withGitHub" element={<SignupGit />} />
          <Route path="/Signuppage" element={<Signup />}></Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;

