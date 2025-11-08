
using FlightDomain.Interfaces;

namespace FlightDomain.Models
{
    public class Flight : IFlight
    {
        public Guid Id { get; init; }
        public string From { get; set; } = "";
        public string To { get; set; } = "";
        public DateTime DepartureTime { get; set; }
        public DateTime ArrivalTime { get; set; }
        public decimal Price { get; set; }
        public Guid PlaneId { get; set; }
        public int BookedSeates { get; set; }
        public ICollection<FlightBooking> flightBookings { get; set; } = new List<FlightBooking>();
        public Plane plane { get; set; } = null!;


        public async Task<int?> Accept(IVisitor visitor)
        {
           int? result =  await visitor.VisitFlight(this);
           return result;
        }
    }
}
