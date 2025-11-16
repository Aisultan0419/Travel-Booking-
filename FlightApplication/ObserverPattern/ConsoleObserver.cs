using FlightApplication.Interfaces.Observer;
namespace FlightApplication.ObserverPattern
{
    public class ConsoleObserver : IObserver
    {
        public void Update(string message)
        {
            Console.WriteLine($"{message}");
        }
    }
}
