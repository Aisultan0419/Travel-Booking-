using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FlightApplication.Interfaces.Services
{
    public interface IHashingService
    {
        string Hash(string password);
    }
}
