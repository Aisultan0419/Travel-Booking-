
namespace FlightApplication.DTO
{
    public class UserDTO
    {
        public string FirstName { get; init; } = string.Empty;
        public string LastName { get; init; } = string.Empty;
        public required string Email { get; set; }
        public required string Password { get; set; }
    }
}
