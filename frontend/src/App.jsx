import { BrowserRouter, Routes, Route } from "react-router-dom";
import Complaint from "./pages/Complaint";
import Home from "./pages/Home";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Dashboard from "./pages/Dashboard";
import MyComplaints from "./pages/MyComplaints";
import Profile from "./pages/Profile";
function App() {
  return (
    <BrowserRouter>
     <Routes>
       <Route path="/my-complaints" element={<MyComplaints />} />
  <Route path="/" element={<Home />} />
  <Route path="/login" element={<Login />} />
  <Route path="/register" element={<Register />} />
  <Route path="/dashboard" element={<Dashboard />} />
   <Route path="/complaints" element={<Complaint />} />
   <Route path="/profile" element={<Profile />} />

</Routes>
    </BrowserRouter>
  );
}

export default App;