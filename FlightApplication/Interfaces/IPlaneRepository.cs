
using FlightDomain.Models;

namespace FlightApplication.Interfaces
{
    public interface IPlaneRepository
    {
        Task<int?> getFreeSeats(Flight flight);
        Task<Flight?> GetFlight(Guid Id);
    }
}
