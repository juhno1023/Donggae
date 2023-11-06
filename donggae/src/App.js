import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import Home from './components/mainpage/Mainpage';
import Posting from './components/Post/Posting';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/posting" element={<Posting />} />
          
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;

