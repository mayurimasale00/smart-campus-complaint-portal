import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./AdminComplaints.css";

function AdminComplaints() {
  const navigate = useNavigate();

  const [statusFilter, setStatusFilter] = useState("ALL");
  const [search, setSearch] = useState("");

  const [complaints, setComplaints] = useState([
    {
      id: "CMP001",
      title: "Broken classroom fan",
      student: "Rahul Patil",
      category: "Electricity",
      priority: "HIGH",
      status: "IN_PROGRESS",
      date: "02 Sep 2026",
    },
    {
      id: "CMP002",
      title: "Water leakage in hostel",
      student: "Sneha Sharma",
      category: "Water Supply",
      priority: "URGENT",
      status: "PENDING",
      date: "02 Sep 2026",
    },
    {
      id: "CMP003",
      title: "Library lights not working",
      student: "Amit Joshi",
      category: "Electricity",
      priority: "MEDIUM",
      status: "RESOLVED",
      date: "01 Sep 2026",
    },
    {
      id: "CMP004",
      title: "Classroom cleaning issue",
      student: "Priya More",
      category: "Cleanliness",
      priority: "LOW",
      status: "PENDING",
      date: "01 Sep 2025",
    },
  ]);

  const updateStatus = (id, newStatus) => {
    setComplaints((current) =>
      current.map((complaint) =>
        complaint.id === id
          ? { ...complaint, status: newStatus }
          : complaint
      )
    );
  };

  const filteredComplaints = complaints.filter((complaint) => {
    const matchesStatus =
      statusFilter === "ALL" ||
      complaint.status === statusFilter;

    const matchesSearch =
      complaint.title
        .toLowerCase()
        .includes(search.toLowerCase()) ||
      complaint.id
        .toLowerCase()
        .includes(search.toLowerCase()) ||
      complaint.student
        .toLowerCase()
        .includes(search.toLowerCase());

    return matchesStatus && matchesSearch;
  });

  return (
    <div className="complaints-page">

      <header className="complaints-header">

        <div>
          <button
            className="back-btn"
            onClick={() => navigate("/admin/dashboard")}
          >
            ← Dashboard
          </button>

          <h1>Complaint Management</h1>
          <p>View and manage all student complaints</p>
        </div>

        <div className="admin-mini-profile">
          <div className="mini-avatar">A</div>
          <span>Administrator</span>
        </div>

      </header>

      <main className="complaints-content">

        <section className="filter-card">

          <div className="search-box">
            🔍
            <input
              type="text"
              placeholder="Search complaint, ID or student..."
              value={search}
              onChange={(e) => setSearch(e.target.value)}
            />
          </div>

          <select
            value={statusFilter}
            onChange={(e) =>
              setStatusFilter(e.target.value)
            }
          >
            <option value="ALL">All Status</option>
            <option value="PENDING">Pending</option>
            <option value="IN_PROGRESS">
              In Progress
            </option>
            <option value="RESOLVED">Resolved</option>
          </select>

        </section>

        <section className="complaints-list">

          <div className="list-heading">
            <div>
              <h2>All Complaints</h2>
              <span>
                {filteredComplaints.length} complaints found
              </span>
            </div>
          </div>

          {filteredComplaints.length === 0 ? (
            <div className="no-results">
              <h3>No complaints found</h3>
              <p>
                Try changing your search or filter.
              </p>
            </div>
          ) : (
            filteredComplaints.map((complaint) => (

              <div
                className="complaint-card"
                key={complaint.id}
              >

                <div className="complaint-top">

                  <div>
                    <span className="complaint-number">
                      {complaint.id}
                    </span>

                    <h3>{complaint.title}</h3>

                    <p>
                      👤 {complaint.student}
                      &nbsp; • &nbsp;
                      📁 {complaint.category}
                      &nbsp; • &nbsp;
                      📅 {complaint.date}
                    </p>
                  </div>

                  <div className="badges">

                    <span
                      className={`priority ${complaint.priority.toLowerCase()}`}
                    >
                      {complaint.priority}
                    </span>

                    <span
                      className={`status ${complaint.status
                        .toLowerCase()
                        .replace("_", "-")}`}
                    >
                      {complaint.status.replace("_", " ")}
                    </span>

                  </div>

                </div>

                <div className="complaint-actions">

                  <button
                    className="action-btn progress"
                    onClick={() =>
                      updateStatus(
                        complaint.id,
                        "IN_PROGRESS"
                      )
                    }
                  >
                    🔧 In Progress
                  </button>

                  <button
                    className="action-btn resolve"
                    onClick={() =>
                      updateStatus(
                        complaint.id,
                        "RESOLVED"
                      )
                    }
                  >
                    ✓ Resolve
                  </button>

                  <button
                    className="action-btn pending"
                    onClick={() =>
                      updateStatus(
                        complaint.id,
                        "PENDING"
                      )
                    }
                  >
                    ↩ Pending
                  </button>

                </div>

              </div>

            ))
          )}

        </section>

      </main>

    </div>
  );
}

export default AdminComplaints;