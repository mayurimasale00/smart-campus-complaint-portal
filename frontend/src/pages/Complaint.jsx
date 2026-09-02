import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Complaint.css";

function Complaint() {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    title: "",
    category: "",
    description: "",
    location: "",
    priority: "MEDIUM",
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    console.log("Complaint:", formData);

    alert("Complaint submitted successfully!");

    navigate("/my-complaints");
  };

  return (
    <div className="complaint-page">

      <div className="complaint-container">

        {/* Header */}
        <div className="complaint-header">
          <button
            className="back-btn"
            onClick={() => navigate("/dashboard")}
          >
            ← Dashboard
          </button>

          <div>
            <h1>Submit Complaint</h1>
            <p>Report an issue on your campus</p>
          </div>
        </div>

        {/* Form */}
        <div className="complaint-card">

          <form onSubmit={handleSubmit}>

            {/* Title */}
            <div className="form-group">
              <label>Complaint Title</label>

              <input
                type="text"
                name="title"
                placeholder="Enter complaint title"
                value={formData.title}
                onChange={handleChange}
                required
              />
            </div>

            {/* Category */}
            <div className="form-group">
              <label>Category</label>

              <select
                name="category"
                value={formData.category}
                onChange={handleChange}
                required
              >
                <option value="">Select category</option>
                <option value="INFRASTRUCTURE">
                  Infrastructure
                </option>
                <option value="ELECTRICITY">
                  Electricity
                </option>
                <option value="WATER">
                  Water Supply
                </option>
                <option value="CLEANLINESS">
                  Cleanliness
                </option>
                <option value="HOSTEL">
                  Hostel
                </option>
                <option value="LIBRARY">
                  Library
                </option>
                <option value="LAB">
                  Laboratory
                </option>
                <option value="OTHER">
                  Other
                </option>
              </select>
            </div>

            {/* Location */}
            <div className="form-group">
              <label>Location</label>

              <input
                type="text"
                name="location"
                placeholder="Example: Block A, Room 204"
                value={formData.location}
                onChange={handleChange}
                required
              />
            </div>

            {/* Priority */}
            <div className="form-group">
              <label>Priority</label>

              <select
                name="priority"
                value={formData.priority}
                onChange={handleChange}
              >
                <option value="LOW">Low</option>
                <option value="MEDIUM">Medium</option>
                <option value="HIGH">High</option>
                <option value="URGENT">Urgent</option>
              </select>
            </div>

            {/* Description */}
            <div className="form-group">
              <label>Description</label>

              <textarea
                name="description"
                placeholder="Describe the issue in detail..."
                rows="6"
                value={formData.description}
                onChange={handleChange}
                required
              />
            </div>

            {/* Buttons */}
            <div className="form-buttons">

              <button
                type="button"
                className="cancel-btn"
                onClick={() => navigate("/dashboard")}
              >
                Cancel
              </button>

              <button
                type="submit"
                className="submit-btn"
              >
                Submit Complaint
              </button>

            </div>

          </form>

        </div>

      </div>

    </div>
  );
}

export default Complaint;