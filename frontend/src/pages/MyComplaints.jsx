import { useNavigate } from "react-router-dom";
import "./MyComplaints.css";

function MyComplaints() {
  const navigate = useNavigate();

  // Temporary data — later this will come from Spring Boot
  const complaints = [
    {
      id: "CMP001",
      title: "Broken classroom fan",
      category: "Electricity",
      location: "Block A - Room 204",
      priority: "HIGH",
      status: "IN_PROGRESS",
      date: "02 Sep 2026",
    },
    {
      id: "CMP002",
      title: "Water leakage in hostel",
      category: "Water Supply",
      location: "Hostel Block B",
      priority: "URGENT",
      status: "PENDING",
      date: "01 Sep 2026",
    },
    {
      id: "CMP003",
      title: "Library lights not working",
      category: "Electricity",
      location: "Central Library",
      priority: "MEDIUM",
      status: "RESOLVED",
      date: "28 Aug 2026",
    },
  ];

  const getStatusClass = (status) => {
    return status.toLowerCase().replace("_", "-");
  };

  const getPriorityClass = (priority) => {
    return priority.toLowerCase();
  };

  return (
    <div className="complaints-page">

      <div className="complaints-container">

        {/* Header */}
        <div className="complaints-header">

          <div>
            <button
              className="back-btn"
              onClick={() => navigate("/dashboard")}
            >
              ← Dashboard
            </button>

            <h1>My Complaints</h1>
            <p>Track and manage your submitted complaints</p>
          </div>

          <button
            className="new-complaint-btn"
            onClick={() => navigate("/complaints")}
          >
            + New Complaint
          </button>

        </div>

        {/* Summary */}
        <div className="complaint-summary">

          <div>
            <span>Total</span>
            <strong>{complaints.length}</strong>
          </div>

          <div>
            <span>Pending</span>
            <strong>
              {
                complaints.filter(
                  (complaint) => complaint.status === "PENDING"
                ).length
              }
            </strong>
          </div>

          <div>
            <span>In Progress</span>
            <strong>
              {
                complaints.filter(
                  (complaint) => complaint.status === "IN_PROGRESS"
                ).length
              }
            </strong>
          </div>

          <div>
            <span>Resolved</span>
            <strong>
              {
                complaints.filter(
                  (complaint) => complaint.status === "RESOLVED"
                ).length
              }
            </strong>
          </div>

        </div>

        {/* Complaints */}
        <div className="complaints-list">

          {complaints.map((complaint) => (

            <div className="complaint-item" key={complaint.id}>

              <div className="complaint-top">

                <div>
                  <span className="complaint-id">
                    {complaint.id}
                  </span>

                  <h2>{complaint.title}</h2>
                </div>

                <span
                  className={`status-badge ${getStatusClass(
                    complaint.status
                  )}`}
                >
                  {complaint.status.replace("_", " ")}
                </span>

              </div>

              <div className="complaint-details">

                <div>
                  <span>Category</span>
                  <strong>{complaint.category}</strong>
                </div>

                <div>
                  <span>Location</span>
                  <strong>{complaint.location}</strong>
                </div>

                <div>
                  <span>Priority</span>
                  <strong
                    className={`priority ${getPriorityClass(
                      complaint.priority
                    )}`}
                  >
                    {complaint.priority}
                  </strong>
                </div>

                <div>
                  <span>Submitted</span>
                  <strong>{complaint.date}</strong>
                </div>

              </div>

              <div className="complaint-footer">
                <button
                  onClick={() =>
                    alert(
                      `Complaint ${complaint.id} details will be available here.`
                    )
                  }
                >
                  View Details →
                </button>
              </div>

            </div>

          ))}

        </div>

      </div>

    </div>
  );
}

export default MyComplaints;