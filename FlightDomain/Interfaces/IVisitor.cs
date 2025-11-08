using FlightDomain.Models;

namespace FlightDomain.Interfaces
{
    public interface IVisitor
    {
        Task<int?> VisitFlight(Flight flight);
    }
}
