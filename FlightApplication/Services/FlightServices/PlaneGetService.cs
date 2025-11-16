
using FlightApplication.DTO;
using FlightApplication.Interfaces.Repo;
using FlightApplication.Interfaces.Services;

namespace FlightApplication.Services.FlightServices
{
    public class PlaneGetService : IPlaneGetService
    {
        private readonly IPlaneRepository _planeRep;
        public PlaneGetService(IPlaneRepository planeRep)
        {
            _planeRep = planeRep;
        }
        public async Task<IEnumerable<PlaneDTO>> GetAllPlanesAsync()
        {
            var result = await _planeRep.GetAllPlanes();

            if (!result.Any())
            {
                return Enumerable.Empty<PlaneDTO>();
            }
            return result;
        }
    }
}
