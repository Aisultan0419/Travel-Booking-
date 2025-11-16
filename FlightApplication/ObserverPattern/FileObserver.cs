
using FlightApplication.Interfaces.Observer;
using System.Runtime.CompilerServices;
using System.Security.Cryptography.X509Certificates;
namespace FlightApplication.ObserverPattern
{
    public class FileObserver : IObserver
    {
        public void Update(string message)
        {
            File.AppendAllText(message, $"{message}");
        }
    }
}
