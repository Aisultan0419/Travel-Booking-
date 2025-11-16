using FlightApplication.DTO;
using FlightApplication.Interfaces.Repo;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace FlightAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UserController : ControllerBase
    {
        private readonly IUserRegService _userSer;
        public UserController(IUserRegService userSer)
        {
            _userSer = userSer;
        }
        [HttpPost("user")]
        public async Task<IActionResult> AddUser([FromBody] UserDTO userDTO)
        {
            var result = await _userSer.RegisterUserAsync(userDTO);

            if (result == false)
            {
                return BadRequest();
            }

            return Ok("User was registered successfully");
        }
    }
}
