import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';

import Signup from "./page/Signup/Signup";
import Login from './page/Login/Login';
import AuthCallback from './page/Login/AuthCallback';

import Main from './page/mainpage/Mainpage';
import Posting from './page/Post/Posting';
import Post from './page/Post/Post';
import Application from './page/Application/Application';

import Leader from "./page/Project/Leader";
import TeamInfo from "./page/Project/TeamInfo";
import UserTeam from "./page/Project/UserTeam";


function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/signup" element={<Signup />} />
          <Route path="/" element={<Login />} />
          <Route path="/auth/github/callback" element={<AuthCallback />} />
          <Route path="/main" element={<Main />} />
          <Route path="/posting" element={<Posting />} />
          <Route path="/post" element={<Post />} />
          <Route path="/application" element={<Application />} />
          <Route path="/leader" element={<Leader />} />
          <Route path="/teaminfo" element={<TeamInfo />} />
          <Route path="/userteam" element={<UserTeam />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;

