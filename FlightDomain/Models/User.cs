
namespace FlightDomain.Models
{
    public class User
    {
        public Guid Id { get; init; }
        public string FirstName { get; init; } = string.Empty;
        public string LastName { get; init; } = string.Empty; 
        public required string Email { get; set; } 
        public required string Password { get; set; }

        public ICollection<FlightBooking> flightBookings { get; set; } = new List<FlightBooking>();
    }
}
