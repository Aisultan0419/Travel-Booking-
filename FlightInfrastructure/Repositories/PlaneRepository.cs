using FlightInfrastructure.Database;
using Microsoft.EntityFrameworkCore;
using FlightApplication.Interfaces;
using FlightDomain.Models;
using System.Runtime.CompilerServices;
using System.Xml.XPath;
namespace FlightInfrastructure.Repositories
{
    public class PlaneRepository : IPlaneRepository
    {
        private readonly FlightDbContext _db;
        public PlaneRepository(FlightDbContext db)
        {
            _db = db;
        }
        public async Task<int?> getFreeSeats(Flight flight)
        {
            var result = await _db.Flights
                    .AsNoTracking()
                    .Where(a => a.Id == flight.Id)
                    .Select(f => new
                    {
                        TotalSeats = f.plane.Seats,
                        BookedSeats = f.BookedSeates
                    })
                    .FirstOrDefaultAsync();

            if (result == null)
                return null;

            return result.TotalSeats - result.BookedSeats;
        }
        public async Task<Flight?> GetFlight(Guid Id)
        {
            var result = await _db.Flights.AsNoTracking().FirstOrDefaultAsync(a => a.Id == Id);
            return result;
        }
    }
}
