import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Login.css";

function Login() {
  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = (e) => {
    e.preventDefault();

    // Temporary login logic
    console.log("Email:", email);
    console.log("Password:", password);

    // After successful login
    navigate("/dashboard");
  };

  return (
    <div className="login-page">
      <div className="login-card">

        <div className="login-header">
          <h1>Welcome Back 👋</h1>
          <p>Login to Smart Campus</p>
        </div>

        <form onSubmit={handleLogin}>

          <div className="form-group">
            <label>Email</label>
            <input
              type="email"
              placeholder="Enter your email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>

          <div className="form-group">
            <label>Password</label>
            <input
              type="password"
              placeholder="Enter your password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>

          <div className="login-options">
            <label>
              <input type="checkbox" />
              Remember me
            </label>

            <a href="#">Forgot Password?</a>
          </div>

          <button type="submit" className="login-btn">
            Login
          </button>

        </form>

        <div className="register-link">
          <p>
            Don't have an account?
            <span onClick={() => navigate("/register")}>
              Register
            </span>
          </p>
        </div>

      </div>
    </div>
  );
}

export default Login;