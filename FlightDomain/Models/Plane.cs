
namespace FlightDomain.Models
{
    public class Plane
    {
        public Guid Id { get; set; }
        public string? Model { get; init; }
        public int Seats { get; init;  }
        public ICollection<Flight> flights { get; set; } = new List<Flight>();
    }
}
