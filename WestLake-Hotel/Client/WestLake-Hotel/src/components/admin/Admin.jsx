import React from "react";
import { Link } from "react-router-dom";

const Admin = () => {
  return (
    <section className="container">
      <h2>Welcome to Admin Panel</h2>
      <hr />
      <Link to={"/existing-rooms"}>Mannage Rooms</Link>
      <hr />
      <Link to={"/existing-bookings"}>Mannage Bookings</Link>
      <hr />
      <Link
      // to={"/existing-users"}
      >
        Mannage Users
      </Link>
    </section>
  );
};

export default Admin;
