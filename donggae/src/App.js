import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import Home from './components/mainpage/Mainpage';
import Posting from './components/Post/Posting';
import Login from './components/Login/Loginpage';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/posting" element={<Posting />} />
          <Route path="/Loginpage" element={<Login />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;

