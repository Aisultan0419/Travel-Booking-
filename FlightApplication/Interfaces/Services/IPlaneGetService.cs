using FlightApplication.DTO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FlightApplication.Interfaces.Services
{
    public interface IPlaneGetService
    {
        Task<IEnumerable<PlaneDTO>> GetAllPlanesAsync();
    }
}
