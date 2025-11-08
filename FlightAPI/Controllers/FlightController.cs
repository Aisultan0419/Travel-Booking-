using FlightApplication.DTO;
using FlightApplication.Interfaces;
using FlightApplication._8assignment;
using Microsoft.AspNetCore.Mvc;

namespace FlightAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class FlightController : ControllerBase
    {
        private readonly IPlaneRepository _planeRep;
        public FlightController(IPlaneRepository planeRep)
        {
            _planeRep = planeRep;
        }
        [HttpGet("freeSeats")]
        public async Task<ActionResult<string>> GetFreeSeatsNumber([FromQuery] FlightQueryDTO flighDTO)
        {
            var flight = await _planeRep.GetFlight(flighDTO.Id);

            if (flight == null)
            {
                return NotFound($"Flight with id - {flighDTO.Id} was not found");
            }
            ConcretePlaneVisitor visitor = new ConcretePlaneVisitor(_planeRep);

            var result = await flight.Accept(visitor);

            return Ok(result);

        }
    }
}
