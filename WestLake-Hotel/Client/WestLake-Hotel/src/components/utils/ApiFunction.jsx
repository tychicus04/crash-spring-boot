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
