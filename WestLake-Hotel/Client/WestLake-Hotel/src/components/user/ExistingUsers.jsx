/* eslint-disable no-unused-vars */
import React, { useEffect, useState } from "react";
import { Col, Row } from "react-bootstrap";
import { Link } from "react-router-dom";
import { FaEdit, FaEye, FaTrashAlt } from "react-icons/fa";
import RoomPaginator from "../common/RoomPaginator";
import RoomFilter from "../common/RoomFilter";
import { deleteUser, getAllUsers } from "../utils/ApiFunction";

const ExistingUsers = () => {
  const [users, setUsers] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [usersPerPage] = useState(8);
  const [isLoading, setIsLoading] = useState(false);
  const [filteredUsers, setFilteredUsers] = useState([]);
  const [selectedUserRole, setSelectedUserRole] = useState("");
  const [successMessage, setSuccessMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  useEffect(() => {
    fetchUsers();
  }, []);

  const fetchUsers = async () => {
    setIsLoading(true);
    try {
      const result = await getAllUsers();
      console.log(result);
      setUsers(result);
      setIsLoading(false);
    } catch (error) {
      console.log("1");
      setErrorMessage(error.message);
    }
  };

  useEffect(() => {
    if (selectedUserRole === "") {
      setFilteredUsers(users);
    } else {
      const filtered = users.filter(
        (user) => user.roles.name === selectedUserRole
      );
      setFilteredUsers(filtered);
    }
    setCurrentPage(1);
  }, [users, selectedUserRole]);

  const calculateTotalPages = (filteredUsers, usersPerPage, users) => {
    const totalUsers =
      filteredUsers.length > 0 ? filteredUsers.length : users.length;
    return Math.ceil(totalUsers / usersPerPage);
  };

  const indexOfLastUser = currentPage * usersPerPage;
  const indexOfFirstUser = indexOfLastUser - usersPerPage;
  //   const currentRooms = filteredRooms.slice(indexOfFirstRoom, indexOfLastRoom);
  // Assuming filteredRooms is supposed to be an array
  const currentUsers = Array.isArray(filteredUsers)
    ? filteredUsers.slice(indexOfFirstUser, indexOfLastUser)
    : [];

  // Now you can use currentRooms as intended

  const handlePaginationClick = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  const handleDeleteUserById = async (userId) => {
    const result = await deleteUser(userId);
    try {
      if (result === "") {
        setSuccessMessage(`User ${userId} was deleted`);
        fetchUsers();
      }
    } catch (error) {
      setErrorMessage(`Error deleting user: ${result.message}`);
    }
    setTimeout(() => {
      setErrorMessage("");
      setSuccessMessage("");
    }, 3000);
  };

  return (
    <>
      <div className="container col-md-8 col-lg-6">
        {successMessage && (
          <p className="alert alert-success mt-5" role="alert">
            {successMessage}
          </p>
        )}
        {errorMessage && (
          <p className="alert alert-danger mt-5" role="alert">
            {errorMessage}
          </p>
        )}
      </div>
      {isLoading ? (
        <p>Loading existing users</p>
      ) : (
        <>
          <section className="mt-5 mb-5 container">
            <div className="d-flex justify-content-between mb-3 mt-5">
              <h2>Existing Users</h2>
            </div>
            <Row>
              <Col md={6} className="mb-2 md-mb-0">
                <RoomFilter data={users} setFilteredData={setFilteredUsers} />
              </Col>
            </Row>

            <table className="table table-bordered table-hover">
              <thead>
                <tr className="text-center">
                  <th className="">ID</th>
                  <th className="">Account</th>
                  <th className="">User role</th>
                  <th className="">Actions</th>
                </tr>
              </thead>

              <tbody>
                {currentUsers.map((user) => (
                  <tr key={user.id} className="text-center">
                    <td>{user.id}</td>
                    <td>{user.email}</td>
                    <td>{user.roles.name}</td>
                    <td className="gap-2">
                      <Link to={`/edit-room/${user.id}`}>
                        <span className="btn btn-info btn-sm">
                          <FaEye />
                        </span>
                        <span className="btn btn-warning btn-sm">
                          <FaEdit />
                        </span>
                      </Link>

                      <button
                        className="btn btn-danger btn-sm"
                        onClick={() => {
                          handleDeleteUserById(user.id);
                        }}
                      >
                        <FaTrashAlt />
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
            <RoomPaginator
              currentPage={currentPage}
              totalPages={calculateTotalPages(
                filteredUsers,
                usersPerPage,
                users
              )}
              onPageChange={handlePaginationClick}
            />
          </section>
        </>
      )}
    </>
  );
};

export default ExistingUsers;
