using FlightApplication.Interfaces.Observer;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore.ChangeTracking.Internal;
using System.Runtime.CompilerServices;

namespace FlightAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ObserverController : ControllerBase
    {
        private readonly IObserverService _observerService;
        public ObserverController(IObserverService observerService)
        {
            _observerService = observerService;
        }
        [HttpPost("subscribe")]
        public async Task Subscribe([FromQuery] int number)
        {
            await _observerService.Alert(number);
        }
    }
}
