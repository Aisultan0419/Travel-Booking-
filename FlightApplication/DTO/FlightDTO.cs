
namespace FlightApplication.DTO
{
    public class FlightDTO
    {
        public string From { get; set; } = "";
        public string To { get; set; } = "";
        public DateTime DepartureTime { get; set; }
        public DateTime ArrivalTime { get; set; }
        public decimal Price { get; set; }
        public int BookedSeates { get; set; }
    }
}
