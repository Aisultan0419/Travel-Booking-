using FlightDomain.Models;

namespace FlightApplication.Interfaces.Repo
{
    public interface IUserRepository 
    {
        Task AddUser(User user);
        Task<User?> GetUser(Guid Id);
    }
}
