import { Link } from "react-router-dom";
import "./Home.css";

function Home() {
  return (
    <div className="home">
      <nav className="navbar">
        <div className="logo">
          Smart<span>Campus</span>
        </div>

        <div className="nav-links">
          <Link to="/">Home</Link>
          <Link to="/login">Login</Link>
          <Link to="/register" className="register-btn">
            Register
          </Link>
        </div>
      </nav>

      <section className="hero">
        <div className="hero-content">
          <p className="tagline">SMART CAMPUS • BETTER CAMPUS</p>

          <h1>
            Make Your Campus
            <span> Better Together.</span>
          </h1>

          <p className="description">
            Report campus problems, track complaints, and stay updated with
            everything happening around your college.
          </p>

          <div className="hero-buttons">
            <Link to="/register" className="primary-btn">
              Get Started
            </Link>

            <Link to="/login" className="secondary-btn">
              Student Login
            </Link>
          </div>
        </div>

        <div className="hero-card">
          <div className="card-icon">🏫</div>

          <h2>Smart Campus</h2>

          <p>
            A simple platform for students and administrators to manage campus
            complaints efficiently.
          </p>

          <div className="features">
            <div>
              <strong>📝</strong>
              <span>Report</span>
            </div>

            <div>
              <strong>📊</strong>
              <span>Track</span>
            </div>

            <div>
              <strong>⚡</strong>
              <span>Resolve</span>
            </div>
          </div>
        </div>
      </section>

      <section className="features-section">
        <h2>Everything Your Campus Needs</h2>

        <div className="feature-grid">
          <div className="feature-box">
            <div className="feature-icon">📝</div>
            <h3>Report Complaints</h3>
            <p>
              Students can quickly report problems and provide important
              details.
            </p>
          </div>

          <div className="feature-box">
            <div className="feature-icon">🔍</div>
            <h3>Track Progress</h3>
            <p>
              Check the current status of your complaint anytime.
            </p>
          </div>

          <div className="feature-box">
            <div className="feature-icon">👨‍💼</div>
            <h3>Admin Management</h3>
            <p>
              Administrators can manage complaints and update their status.
            </p>
          </div>
        </div>
      </section>

      <footer>
        <p>© 2026 Smart Campus. Built for smarter campuses.</p>
      </footer>
    </div>
  );
}

export default Home;