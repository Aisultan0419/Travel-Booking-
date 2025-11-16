using FlightApplication.Interfaces.Repo;
using FlightInfrastructure.Database;
namespace FlightInfrastructure.Repositories
{
    public class UnitRepository : IUnitRepository
    {
        private readonly FlightDbContext _db;
        public UnitRepository(FlightDbContext db)
        {
            _db = db;
        }
        public async Task SaveChanges()
        {
            await _db.SaveChangesAsync();
        }
    }
}
