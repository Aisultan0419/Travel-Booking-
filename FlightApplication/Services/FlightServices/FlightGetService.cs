using FlightApplication.DTO;
using FlightApplication.Interfaces.Repo;
using FlightApplication.Interfaces.Services;

namespace FlightApplication.Services.FlightServices
{
    public class FlightGetService : IFlightGetService
    {
        private readonly IPlaneRepository _planeRep;
        public FlightGetService(IPlaneRepository planeRep)
        {
            _planeRep = planeRep;
        }
        public async Task<IEnumerable<FlightDTO>> GetAllFlightsAsync()
        {
            var result = await _planeRep.GetAllFlights();

            if (!result.Any())
            {
                return Enumerable.Empty<FlightDTO>();
            }
            return result;
        }
    }
}
