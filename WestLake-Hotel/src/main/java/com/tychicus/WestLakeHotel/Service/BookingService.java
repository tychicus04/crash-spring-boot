package com.tychicus.WestLakeHotel.Service;

import com.tychicus.WestLakeHotel.Exception.InvalidBookingRequestException;
import com.tychicus.WestLakeHotel.Exception.ResourceNotFoundException;
import com.tychicus.WestLakeHotel.Model.BookedRoom;
import com.tychicus.WestLakeHotel.Model.Room;
import com.tychicus.WestLakeHotel.Repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService implements IBookingService {
    private final BookingRepository bookingRepository;
    private final IRoomService roomService;

    @Override
    public List<BookedRoom> getAllBookingsByRoomId(Long id) {
        return bookingRepository.findByRoomId(id);
    }

    @Override
    public void cancelBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }

    @Override
    public String saveBooking(Long roomId, BookedRoom bookingRequest) {
        if (bookingRequest.getCheckOutDate().isBefore(bookingRequest.getCheckInDate())) {
            throw new InvalidBookingRequestException("Check out date cannot be before check in date");
        }
        Room room = roomService.getRoomById(roomId).get();
        List<BookedRoom> existingBookings = room.getBookings();
        boolean roomIsAvailable = roomIsAvailable(existingBookings, bookingRequest);
        if (roomIsAvailable) {
            room.addBooking(bookingRequest);
            bookingRepository.save(bookingRequest);
        } else {
            throw new InvalidBookingRequestException("Sorry, This room is not available for the selected dates;");
        }
        return bookingRequest.getBookingConfirmationCode();
    }


    @Override
    public BookedRoom findByBookingConfirmationCode(String confirmationCode) {
        return bookingRepository.findByBookingConfirmationCode(confirmationCode).orElseThrow(() -> new ResourceNotFoundException("No booking found with booking code " + confirmationCode));
    }

    @Override
    public List<BookedRoom> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public List<BookedRoom> getBookingsByUserEmail(String email) {
        return bookingRepository.findByGuestEmail(email);
    }

    private boolean roomIsAvailable(List<BookedRoom> existingBookings, BookedRoom bookingRequest) {
        return existingBookings.stream()
                .noneMatch(existingBooking ->
                        bookingRequest.getCheckInDate().equals(existingBooking.getCheckInDate())
                                || bookingRequest.getCheckOutDate().isBefore(existingBooking.getCheckOutDate())
                                || (bookingRequest.getCheckInDate().isAfter(existingBooking.getCheckInDate())
                                && bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckOutDate())
                        )
                                || (bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckInDate())
                                && bookingRequest.getCheckOutDate().equals(existingBooking.getCheckOutDate())
                        )
                                || (bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckInDate())
                                && bookingRequest.getCheckOutDate().isAfter(existingBooking.getCheckOutDate()))
                                || (bookingRequest.getCheckInDate().equals(existingBooking.getCheckOutDate())
                                && bookingRequest.getCheckOutDate().equals(existingBooking.getCheckInDate())
                        )
                                || (bookingRequest.getCheckInDate().equals(existingBooking.getCheckOutDate())
                                && bookingRequest.getCheckOutDate().isBefore(existingBooking.getCheckInDate())
                        )
                );
    }
}
