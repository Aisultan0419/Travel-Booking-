using FlightDomain.Models;
using FlightApplication.DTO;
using FlightApplication.Interfaces.Repo;
using FlightApplication.Interfaces.Services;

namespace FlightApplication.Services.UserServices
{
    public class UserRegService : IUserRegService
    {

        private readonly IUnitRepository _unitRep;
        private readonly IHashingService _hashSer;
        private readonly IUserRepository _userRep;
        public UserRegService(IUnitRepository unitRep, IUserRepository userRep, IHashingService hashSer)
        {
            _unitRep = unitRep;
            _userRep = userRep;
            _hashSer = hashSer;
        }
        public async Task<bool> RegisterUserAsync(UserDTO userDTO)
        {
            if (userDTO == null)
            {
                return false;
            }
            var user = new User
            {
                Id = Guid.NewGuid(),
                Email = userDTO.Email,
                Password = _hashSer.Hash(userDTO.Password),
                FirstName = userDTO.FirstName,
                LastName = userDTO.LastName
            };

            await _userRep.AddUser(user);
            await _unitRep.SaveChanges();

            return true;
        }
    }
}
