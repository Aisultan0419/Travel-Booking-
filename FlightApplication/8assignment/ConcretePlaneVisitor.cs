using FlightApplication.DTO;
using FlightApplication.Interfaces;
using FlightDomain.Models;
using FlightDomain.Interfaces;
namespace FlightApplication._8assignment
{
    public class ConcretePlaneVisitor : IVisitor
    {
        private readonly IPlaneRepository _planeRep;
        public ConcretePlaneVisitor(IPlaneRepository planeRep)
        {
            _planeRep = planeRep;
        }

        public async Task<int?> VisitFlight(Flight flight)
        {
            int? result = await _planeRep.getFreeSeats(flight);
            if (result == null)
            {
                return null;
            }
            return result;
        }
    }
}
