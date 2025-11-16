using FlightApplication.DTO;
using Microsoft.EntityFrameworkCore.Storage;
using FlightDomain.Models;

namespace FlightApplication.Interfaces.Repo
{
    public interface IPlaneRepository
    {
        Task<int?> getFreeSeats(Flight flight);
        Task<Flight?> GetFlight(Guid Id);
        Task<IEnumerable<FlightDTO>> GetAllFlights();
        Task<IEnumerable<PlaneDTO>> GetAllPlanes();
        Task<int> PriceDrop();
        Task<IDbContextTransaction> BeginTransactionAsync();
        Task AddBooking(FlightBooking flightBook);
    }
}
