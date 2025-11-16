using FlightApplication.DTO;
using FlightApplication.Interfaces.Repo;
using FlightDomain.Models;
using FlightInfrastructure.Database;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Storage;
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
        public async Task AddBooking(FlightBooking flightBook)
        {
            await _db.FlightBooks.AddAsync(flightBook);
        }
        public async Task<IEnumerable<FlightDTO>> GetAllFlights()
        {
            var result = await _db.Flights
                .AsNoTracking()
                .Select(a => new FlightDTO
                {
                    From = a.From,
                    To = a.To,
                    DepartureTime = a.DepartureTime,
                    ArrivalTime = a.ArrivalTime,
                    Price = a.Price,
                    BookedSeates = a.BookedSeates
                })
                .ToListAsync();

            if (result == null)
            {
                new List<FlightDTO>() { };
            }
            return result!;
        }
        public async Task<IEnumerable<PlaneDTO>> GetAllPlanes()
        {
            var result = await _db.Planes
                .AsNoTracking()
                .Select(a => new PlaneDTO
                {
                    Model = a.Model,
                    Seats = a.Seats
                })
                .ToListAsync();

            if (result == null)
            {
                new List<PlaneDTO>() { };
            }
            return result!;
        }

        public async Task<int> PriceDrop()
        {
            var flight = await _db.Flights.FirstAsync();
            return (int)((double)flight.Price * 0.8);
        }

        public async Task<Flight?> GetFlight(Guid Id)
        {
            var result = await _db.Flights.AsNoTracking().FirstOrDefaultAsync(a => a.Id == Id);
            return result;
        }
        public async Task<IDbContextTransaction> BeginTransactionAsync()
        {
            return await _db.Database.BeginTransactionAsync();
        }
    }
}
