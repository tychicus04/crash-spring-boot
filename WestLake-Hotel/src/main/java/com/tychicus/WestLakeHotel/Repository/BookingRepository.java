package com.tychicus.WestLakeHotel.Repository;

import com.tychicus.WestLakeHotel.Model.BookedRoom;
import com.tychicus.WestLakeHotel.Model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<BookedRoom, Long> {
    BookedRoom findByBookingConfirmationCode(String confirmationCode);

    List<BookedRoom> findByRoomId(Long roomId);
}
