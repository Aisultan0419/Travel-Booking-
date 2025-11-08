using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Design;
using Microsoft.Extensions.Configuration;
namespace FlightInfrastructure.Database
{
    public class DbConfiguration : IDesignTimeDbContextFactory<FlightDbContext>
    {
        public FlightDbContext CreateDbContext(string[] args)
        {
            var basePath = Directory.GetCurrentDirectory();

            var configuration = new ConfigurationBuilder()
                .SetBasePath(Directory.GetCurrentDirectory()) 
                .AddJsonFile("appsettings.json", optional: true)
                .Build();

            var connectionString = configuration.GetConnectionString("Default");

            var optionsBuilder = new DbContextOptionsBuilder<FlightDbContext>();
            optionsBuilder.UseNpgsql(connectionString);

            return new FlightDbContext(optionsBuilder.Options);
        }
    }
}
