using FlightApplication.Interfaces.Repo;
using FlightDomain.Models;
using FlightInfrastructure.Database;
using Microsoft.EntityFrameworkCore;
namespace FlightInfrastructure.Repositories
{
    public class UserRepository : IUserRepository
    {
        private readonly FlightDbContext _db;
        public UserRepository(FlightDbContext db)
        {
            _db = db;
        }

        public async Task<User?> GetUser(Guid Id)
        {
            var result = await _db.Users.AsNoTracking().FirstOrDefaultAsync(a => a.Id == Id);
            return result;
        }
        public async Task AddUser(User user)
        {
            await _db.AddAsync(user);
        }
    }
}
