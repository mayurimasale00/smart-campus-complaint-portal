import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Profile.css";

function Profile() {
  const navigate = useNavigate();

  const [isEditing, setIsEditing] = useState(false);

  const [profile, setProfile] = useState({
    name: "Student",
    email: "student@example.com",
    phone: "",
    department: "Computer Science Engineering",
    year: "3rd Year",
    rollNumber: "",
  });

  const handleChange = (e) => {
    setProfile({
      ...profile,
      [e.target.name]: e.target.value,
    });
  };

  const handleSave = () => {
    setIsEditing(false);
    alert("Profile updated successfully!");
  };

  return (
    <div className="profile-page">

      <div className="profile-container">

        {/* Header */}
        <div className="profile-header">
          <button
            className="back-btn"
            onClick={() => navigate("/dashboard")}
          >
            ← Dashboard
          </button>

          <h1>My Profile</h1>
          <p>View and manage your account information</p>
        </div>

        {/* Profile Card */}
        <div className="profile-card">

          <div className="profile-top">

            <div className="profile-avatar">
              {profile.name.charAt(0).toUpperCase()}
            </div>

            <div>
              <h2>{profile.name}</h2>
              <p>Student</p>
            </div>

          </div>

          {/* Personal Information */}
          <div className="profile-section">

            <div className="section-heading">
              <h2>Personal Information</h2>

              {!isEditing && (
                <button
                  className="edit-btn"
                  onClick={() => setIsEditing(true)}
                >
                  ✏️ Edit Profile
                </button>
              )}
            </div>

            <div className="profile-grid">

              <div className="profile-field">
                <label>Full Name</label>

                {isEditing ? (
                  <input
                    type="text"
                    name="name"
                    value={profile.name}
                    onChange={handleChange}
                  />
                ) : (
                  <p>{profile.name}</p>
                )}
              </div>

              <div className="profile-field">
                <label>Email</label>
                <p>{profile.email}</p>
              </div>

              <div className="profile-field">
                <label>Phone Number</label>

                {isEditing ? (
                  <input
                    type="tel"
                    name="phone"
                    placeholder="Enter phone number"
                    value={profile.phone}
                    onChange={handleChange}
                  />
                ) : (
                  <p>{profile.phone || "Not provided"}</p>
                )}
              </div>

              <div className="profile-field">
                <label>Roll Number</label>

                {isEditing ? (
                  <input
                    type="text"
                    name="rollNumber"
                    placeholder="Enter roll number"
                    value={profile.rollNumber}
                    onChange={handleChange}
                  />
                ) : (
                  <p>{profile.rollNumber || "Not provided"}</p>
                )}
              </div>

            </div>

          </div>

          {/* Academic Information */}
          <div className="profile-section">

            <h2>Academic Information</h2>

            <div className="profile-grid">

              <div className="profile-field">
                <label>Department</label>
                <p>{profile.department}</p>
              </div>

              <div className="profile-field">
                <label>Year</label>
                <p>{profile.year}</p>
              </div>

              <div className="profile-field">
                <label>Role</label>
                <p>Student</p>
              </div>

              <div className="profile-field">
                <label>Account Status</label>
                <span className="account-status">
                  Active
                </span>
              </div>

            </div>

          </div>

          {/* Save / Cancel */}
          {isEditing && (
            <div className="profile-actions">

              <button
                className="cancel-btn"
                onClick={() => setIsEditing(false)}
              >
                Cancel
              </button>

              <button
                className="save-btn"
                onClick={handleSave}
              >
                Save Changes
              </button>

            </div>
          )}

        </div>

      </div>

    </div>
  );
}

export default Profile;