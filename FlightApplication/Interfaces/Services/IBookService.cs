using FlightApplication.DTO;
using System;
using System.Collections.Generic;
using System.Text;

namespace FlightApplication.Interfaces.Services
{
    public interface IBookService
    {
        Task<ResponseDTO> BookAsync(BookQueryDTO queryDTO);
    }
}
