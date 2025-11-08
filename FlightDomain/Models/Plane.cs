
namespace FlightDomain.Models
{
    public class Plane
    {
        public Guid Id { get; }
        public string? Model { get; }
        public int Seats { get; }
        public int BookedSeats { get; set; }
    }
}
