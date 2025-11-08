namespace FlightDomain.Models
{
    public class Flight
    {
        public Guid Id { get; init; }
        public string From { get; set; } = "";
        public string To { get; set; } = "";
        public DateTime DepartureTime { get; set; }
        public DateTime ArrivalTime { get; set; }
        public decimal Price { get; set; }
        public Guid PlaneId { get; set; }
    }
}
