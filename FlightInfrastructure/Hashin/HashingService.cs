using FlightApplication.Interfaces.Services;
using System.Security.Cryptography;
using System.Text;
namespace FlightInfrastructure.Hashin
{
    public class HashingService : IHashingService
    {
        public string Hash(string password)
        {
            var passwordBytes = Encoding.UTF8.GetBytes(password);

            using var sha256 = SHA256.Create();

            var result = Convert.ToBase64String(sha256.ComputeHash(passwordBytes));

            return result;
        }
    }
}
