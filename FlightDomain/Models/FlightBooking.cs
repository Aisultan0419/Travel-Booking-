namespace FlightDomain.Models
{
    public class FlightBooking
    {
        public Guid Id { get; init; }
        public Guid UserId { get; init; }
        public Guid FlightId { get; init; }
        public DateTime BookingTime { get; init; }
        public decimal PricePaid { get; set; }
        public decimal PriceTotal { get; set; }
        public string Status { get; set; } = "Unknown";
        public Flight flight { get; set; } = null!;
    }
}
