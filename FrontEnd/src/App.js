import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import Home from './page/mainpage/Mainpage';
import Posting from './page/Post/Posting';
import Post from './page/Post/Post';
import Application from './page/Application/Application';
import Login from './page/Login/Login';
import Signup from "./page/Signup/Signup";
import Leader from "./page/Project/Leader";
import AuthCallback from './page/Login/AuthCallback';

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
          <Route path="/auth/github/callback" element={<AuthCallback />} />
          <Route path="/Signup" element={<Signup />}></Route>
          <Route path="/Leader" element={<Leader />}></Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;

