using FlightApplication.DTO;

namespace FlightApplication.Interfaces.Services
{
    public interface IFlightGetService
    {
        Task<IEnumerable<FlightDTO>> GetAllFlightsAsync();
    }
}
