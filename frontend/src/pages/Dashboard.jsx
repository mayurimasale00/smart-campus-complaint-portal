import { useNavigate } from "react-router-dom";
import "./Dashboard.css";

function Dashboard() {
  const navigate = useNavigate();

  const handleLogout = () => {
    navigate("/login");
  };

  return (
    <div className="dashboard">

      {/* Sidebar */}
      <aside className="sidebar">

        <div className="sidebar-logo">
          <h2>Smart Campus</h2>
        </div>

        <nav className="sidebar-nav">
          <button className="active">
            🏠 Dashboard
          </button>

          <button onClick={() => navigate("/complaints")}>
            📝 Submit Complaint
          </button>

          <button onClick={() => navigate("/my-complaints")}>
            📋 My Complaints
          </button>

          <button onClick={() => navigate("/profile")}>
            👤 Profile
          </button>
        </nav>

        <button className="logout-btn" onClick={handleLogout}>
          🚪 Logout
        </button>

      </aside>

      {/* Main Content */}
      <main className="dashboard-main">

        <header className="dashboard-header">
          <div>
            <h1>Dashboard</h1>
            <p>Welcome back, Student 👋</p>
          </div>

          <div className="user-info">
            <div className="user-avatar">S</div>
            <span>Student</span>
          </div>
        </header>

        {/* Statistics */}
        <section className="stats-grid">

          <div className="stat-card">
            <div className="stat-icon">📝</div>
            <div>
              <h3>0</h3>
              <p>Total Complaints</p>
            </div>
          </div>

          <div className="stat-card">
            <div className="stat-icon">⏳</div>
            <div>
              <h3>0</h3>
              <p>Pending</p>
            </div>
          </div>

          <div className="stat-card">
            <div className="stat-icon">🔧</div>
            <div>
              <h3>0</h3>
              <p>In Progress</p>
            </div>
          </div>

          <div className="stat-card">
            <div className="stat-icon">✅</div>
            <div>
              <h3>0</h3>
              <p>Resolved</p>
            </div>
          </div>

        </section>

        {/* Quick Actions */}
        <section className="dashboard-section">

          <h2>Quick Actions</h2>

          <div className="action-grid">

            <div
              className="action-card"
              onClick={() => navigate("/complaints")}
            >
              <div className="action-icon">📝</div>
              <h3>Submit Complaint</h3>
              <p>Report a problem on campus</p>
            </div>

            <div
              className="action-card"
              onClick={() => navigate("/my-complaints")}
            >
              <div className="action-icon">📋</div>
              <h3>View Complaints</h3>
              <p>Track your submitted complaints</p>
            </div>

            <div
              className="action-card"
              onClick={() => navigate("/profile")}
            >
              <div className="action-icon">👤</div>
              <h3>My Profile</h3>
              <p>View and manage your profile</p>
            </div>

          </div>

        </section>

        {/* Recent Complaints */}
        <section className="dashboard-section">

          <div className="section-title">
            <h2>Recent Complaints</h2>

            <button
              onClick={() => navigate("/my-complaints")}
            >
              View All
            </button>
          </div>

          <div className="empty-state">
            <div className="empty-icon">📭</div>

            <h3>No complaints yet</h3>

            <p>
              You haven't submitted any complaints.
            </p>

            <button
              onClick={() => navigate("/complaints")}
              className="primary-btn"
            >
              Submit Your First Complaint
            </button>
          </div>

        </section>

      </main>
    </div>
  );
}

export default Dashboard;