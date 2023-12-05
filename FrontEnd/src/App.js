import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import Main from './page/mainpage/Mainpage';
import Posting from './page/Post/Posting';
import Post from './page/Post/Post';
import Modify from './page/Post/Modify';
import Application from './page/Application/Application';
import Login from './page/Login/Login';
import Mypage from './page/Mypage/Mypage';
import Recruit from './page/Recruit/Recruit';
import Rank from './page/Rankpage/Rankpage';
import Signup from "./page/Signup/Signup";
import Leader from "./page/Project/Leader";
import TeamInfo from "./page/Project/TeamInfo";
import Evaluation from "./page/Evaluation/Evaluation"
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
          <Route exact path="/post/:recuritPostId" element={<Post />} />
          <Route exact path="/modify/:recuritPostId" element={<Modify />} />
          <Route path="/mypage" element={<Mypage />} />
          <Route path="/rank" element={<Rank />} />
          <Route path="/application" element={<Application />} />
          <Route path="/auth/github/callback" element={<AuthCallback />} />
          <Route path="/signup" element={<Signup />}/>
          <Route path="/leader/:teamId" element={<Leader />}/>
          <Route path="/recruit" element={<Recruit />}/>
          <Route path="/evaluation" element={<Evaluation />}/>
          <Route path="/teaminfo/:teamId" element={<TeamInfo />}/>
          <Route path="/userteam" element={<UserTeam />}/>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;

