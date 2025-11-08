
namespace FlightDomain.Interfaces
{
    public interface IFlight
    {
        Task<int?> Accept(IVisitor visitor);
    }
}
