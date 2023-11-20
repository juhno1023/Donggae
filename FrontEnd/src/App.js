import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import Main from './page/mainpage/Mainpage';
import Posting from './page/Post/Posting';
import Post from './page/Post/Post';
import Application from './page/Application/Application';
import Login from './page/Login/Login';
import Signup from "./page/Signup/Signup";
import Leader from "./page/Project/Leader";
import TeamInfo from "./page/Project/TeamInfo";
import UserTeam from "./page/Project/UserTeam";
import AuthCallback from './page/Login/AuthCallback';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/main" element={<Main />} />
          <Route path="/posting" element={<Posting />} />
          <Route path="/post" element={<Post />} />
          <Route path="/application" element={<Application />} />
          <Route path="/auth/github/callback" element={<AuthCallback />} />
          <Route path="/signup" element={<Signup />}></Route>
          <Route path="/leader" element={<Leader />}></Route>
          <Route path="/teaminfo" element={<TeamInfo />}></Route>
          <Route path="/userteam" element={<UserTeam />}></Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;

