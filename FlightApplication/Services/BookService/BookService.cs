
using FlightApplication.Interfaces.Services;
using FlightApplication.DTO;
using FlightApplication.Interfaces.Repo;
using FlightDomain.Models;

namespace FlightApplication.Services.BookService
{
    public class BookService : IBookService
    {
        private readonly IPlaneRepository _planeRep;
        private readonly IUserRepository _userRep;
        private readonly IUnitRepository _unitRep;
        public BookService(IPlaneRepository planeRep, IUserRepository userRep, IUnitRepository unitRep)
        {
            _planeRep = planeRep;
            _userRep = userRep;
            _unitRep = unitRep;
        }

        public async Task<ResponseDTO> BookAsync(BookQueryDTO queryDTO)
        {
            var user = await _userRep.GetUser(queryDTO.UserId);

            if (user == null)
            {
                return new ResponseDTO
                {
                    Success = false,
                    Message = $"The User by id - {queryDTO.UserId} was not found"
                };
            }

            var flight = await _planeRep.GetFlight(queryDTO.Id);

            if (flight == null)
            {
                return new ResponseDTO
                {
                    Success = false,
                    Message = $"The flight by id - {queryDTO.Id} was not found"
                };
            }

            if (queryDTO.Price < 0)
            {
                return new ResponseDTO
                {
                    Success = false,
                    Message = $"Price cannot be negative"
                };
            }

            using var tx = await _planeRep.BeginTransactionAsync();
            try
            {
                var flightBook = new FlightBooking
                {
                    Id = Guid.NewGuid(),
                    UserId = queryDTO.UserId,
                    FlightId = queryDTO.Id,
                    BookingTime = DateTime.UtcNow,
                    PricePaid = queryDTO.Price,
                    PriceTotal = flight.Price,
                    Status = queryDTO.Price == flight.Price ? "Totally Paid" : "Partially Paid"
                };

                await _planeRep.AddBooking(flightBook);
                await _unitRep.SaveChanges();
                await tx.CommitAsync();
            }
            catch (Exception ex)
            {
                await tx.RollbackAsync();
                throw new Exception($"The booking process failed: {ex.Message}", ex);
            }


            return new ResponseDTO
            {
                Success = true,
                Message = "Booking proccess was done successfully"
            };
        }
    }
}
