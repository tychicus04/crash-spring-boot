import React from "react";
import { Link } from "react-router-dom";

const Admin = () => {
  return (
    <section className="container">
      <h2>Welcome to Admin Panel</h2>
      <hr />
      <Link to={"/existing-rooms"}>Mannage Rooms</Link>
    </section>
  );
};

export default Admin;
