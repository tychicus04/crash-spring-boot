// import React, { useEffect, useState } from "react";
// import { getUser } from "../utils/ApiFunction";
// import { useParams } from "react-router-dom";

// const Edituser = () => {
//   const [user, setUser] = useState({
//     id: "",
//     firstName: "",
//     lastName: "",
//     email: "",
//     roles: [
//       {
//         id: "",
//         name: "",
//       },
//     ],
//   });

//   const [role, setRole] = useState({
//     id: "",
//     name: "",
//   });

//   const [successMessage, setSuccessMessage] = useState("");
//   const [errorMessage, setErrorMessage] = useState("");

//   const { userId } = useParams();

//   const handleInputChange = (e) => {
//     const { name, value } = e.target;

//     setUser({ ...user, [name]: value });
//   };

//   useEffect(() => {
//     const fetchUser = async () => {
//       try {
//         const userData = await getUser(userId);
//         setUser(userData);
//       } catch (error) {
//         console.error(error);
//       }
//     };

//     fetchUser();
//   }, [userId]);

//   const handleSubmit = async (e) => {
//     e.preventDefault();

//     try {
//       const response = await updateRoom(roomId, room);

//       if (response.status === 200) {
//         setSuccessMessage("Room updated successfully");
//         const updatedRoomData = await getRoomById(roomId);

//         setRoom(updatedRoomData);
//         setImagePreview(updatedRoomData.photo);
//         setErrorMessage("");
//       } else {
//         setErrorMessage("Error updating room");
//       }
//     } catch (error) {
//       console.log(error);
//       setErrorMessage(error.message);
//     }

//     setTimeout(() => {
//       setSuccessMessage("");
//       setErrorMessage("");
//     }, 3000);
//   };
//   return <div></div>;
// };

// export default Edituser;
