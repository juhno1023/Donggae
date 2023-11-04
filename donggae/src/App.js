import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import Home from './components/mainpage/Mainpage';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;

