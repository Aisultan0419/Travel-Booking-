using FlightDomain.Models;
using Microsoft.EntityFrameworkCore;

namespace FlightInfrastructure.Database
{
    public class FlightDbContext : DbContext
    {
        public FlightDbContext(DbContextOptions<FlightDbContext> options) : base(options) { }


        public DbSet<Flight> Flights { get; set; }
        public DbSet<FlightBooking> FlightBooks { get; set; }
        public DbSet<Plane> Planes { get; set; }
        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);

            modelBuilder.Entity<Flight>()
                .HasMany(f => f.flightBookings)
                .WithOne(b => b.flight)
                .HasForeignKey(b => b.FlightId)
                .OnDelete(DeleteBehavior.Cascade);


            modelBuilder.Entity<Plane>()
                .HasMany(p => p.flights)
                .WithOne(f => f.plane)
                .HasForeignKey(f => f.PlaneId)
                .OnDelete(DeleteBehavior.Restrict);
        }
    }
}
