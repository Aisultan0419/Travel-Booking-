using FlightApplication.DTO;
using FlightApplication._8assignment;
using Microsoft.AspNetCore.Mvc;
using FlightApplication.Interfaces.Repo;
using FlightApplication.Interfaces.Services;


namespace FlightAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class FlightController : ControllerBase
    {
        private readonly IPlaneRepository _planeRep;
        private readonly IPlaneGetService _planeGetService;
        private readonly IFlightGetService _flightGetSer;
        public FlightController(IPlaneRepository planeRep, IFlightGetService flightGetSer, IPlaneGetService planeGetService)
        {
            _planeRep = planeRep;
            _flightGetSer = flightGetSer;
            _planeGetService = planeGetService;
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
        [HttpGet("flights")]
        public async Task<ActionResult<IEnumerable<FlightDTO>>> GetFlights()
        {
            var result = await _flightGetSer.GetAllFlightsAsync();

            if (!result.Any())
            {
                return NotFound();
            }
            return Ok(result);
        }
        [HttpGet("planes")]
        public async Task<ActionResult<IEnumerable<PlaneDTO>>> GetPlanes()
        {
            var result = await _planeGetService.GetAllPlanesAsync();

            if (!result.Any())
            {
                return NotFound();
            }
            return Ok(result);
        }
        [HttpPost("book")]
        public async Task<ActionResult<ResponseDTO>> 
    }
}
