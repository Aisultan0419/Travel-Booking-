using FlightApplication.DTO;

namespace FlightApplication.Interfaces.Repo
{
    public interface IUserRegService
    {
        Task<bool> RegisterUserAsync(UserDTO userDTO);
    }
}
