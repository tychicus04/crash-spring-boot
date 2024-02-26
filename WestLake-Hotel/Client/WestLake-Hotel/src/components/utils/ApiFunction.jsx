import axios from "axios";

export const api = axios.create({
  baseURL: "http://localhost:9192",
});

/* This function add a new room to the database*/
export async function addRoom(photo, roomType, roomPrice) {
  const formData = new FormData();
  formData.append("photo", photo);
  formData.append("roomType", roomType);
  formData.append("roomPrice", roomPrice);

  const resoponse = await api.post("/rooms/add/new-room", formData);
  if (resoponse.status === 201) {
    return true;
  } else {
    return false;
  }
}

/* This function gets all room types from the database */
export async function getRoomTypes() {
  try {
    const response = await api.get("/rooms/room/types");
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.error(error);
    throw new Error("Error fetching room types");
  }
}

/* This function gets all rooms from the database */
export async function getAllRooms() {
  try {
    const result = await api.get("/rooms/all-rooms");
    console.log(result.data);
    return result.data;
  } catch (error) {
    throw new Error("Error fetching rooms");
  }
}

/* This  function will delete a room by the Id*/
export async function deleteRoom(roomId) {
  try {
    const result = await api.delete(`rooms/delete/room/${roomId}`);
    return result.data;
  } catch (error) {
    throw new Error(`Error deleting room ${roomId}`);
  }
}

/* This function will update room */
export async function updateRoom(roomId, roomData) {
  const formData = new FormData();
  formData.append("photo", roomData.photo);
  formData.append("roomType", roomData.roomType);
  formData.append("roomPrice", roomData.roomPrice);
  const response = await api.put(`/rooms/update/${roomId}`);
  return response;
}

/* This function will get a room by Id */
export async function getRoomById(roomId) {
  try {
    const result = await api.get(`rooms/room/${roomId}`);
    return result.data;
  } catch (error) {
    throw new Error(`Error fetching room ${error.message}`);
  }
}
