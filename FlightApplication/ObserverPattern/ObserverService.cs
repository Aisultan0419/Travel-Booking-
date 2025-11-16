
using FlightApplication.Interfaces.Observer;
using FlightApplication.Interfaces.Repo;
using System.Runtime.CompilerServices;

namespace FlightApplication.ObserverPattern
{
    public class ObserverService : IObserverService
    {
        private readonly ISubject _subject;
        private readonly IObserver _observer;
        private readonly IPlaneRepository _planeRep;
        public ObserverService(
           ISubject subject,
           IObserver observer,
           IPlaneRepository planeRep)
        {
            _subject = subject;
            _observer = observer;
            _planeRep = planeRep;
        }
        public async Task Alert(int choose)
        {
            var observer = choose == 0 ? (IObserver)new ConsoleObserver() : new FileObserver();

            int priceDrop = await _planeRep.PriceDrop();

            _subject.Attach(observer);

            _subject.Notify($"Price has dropped to {priceDrop}");
        }
    }
}
