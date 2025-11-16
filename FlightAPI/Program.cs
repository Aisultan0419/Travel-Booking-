using FlightApplication._8assignment;
using FlightApplication.Interfaces.Observer;
using FlightApplication.Interfaces.Repo;
using FlightApplication.Interfaces.Services;
using FlightApplication.ObserverPattern;
using FlightApplication.Services.BookService;
using FlightApplication.Services.FlightServices;
using FlightApplication.Services.UserServices;
using FlightDomain.Interfaces;
using FlightDomain.Models;
using FlightInfrastructure.Database;
using FlightInfrastructure.Hashin;
using FlightInfrastructure.Repositories;
using Microsoft.EntityFrameworkCore;
var builder = WebApplication.CreateBuilder(args);



builder.Services.AddControllers();
builder.Services.AddSwaggerGen();
builder.Services.AddOpenApi();

builder.Services.AddScoped<IPlaneRepository, PlaneRepository>();
builder.Services.AddScoped<IVisitor, ConcretePlaneVisitor>();
builder.Services.AddScoped<IFlight, Flight>();
builder.Services.AddScoped<IUserRepository, UserRepository>();
builder.Services.AddScoped<IUserRegService, UserRegService>();
builder.Services.AddScoped<IUnitRepository, UnitRepository>();
builder.Services.AddScoped<IPlaneGetService, PlaneGetService>();
builder.Services.AddScoped<IHashingService, HashingService>();
builder.Services.AddScoped<IFlightGetService, FlightGetService>();
builder.Services.AddScoped<IObserverService, ObserverService>();
builder.Services.AddScoped<ISubject, Subject>();
builder.Services.AddScoped<IObserver, FileObserver>();
builder.Services.AddScoped<IBookService, BookService>();
builder.Services.AddScoped<IObserver, ConsoleObserver>();
builder.Services.AddDbContext<FlightDbContext>(options =>
options.UseNpgsql(builder.Configuration.GetConnectionString("Default")));

var app = builder.Build();

//builder.Services.AddCors(options =>
//{
//    options.AddPolicy("AllowAll", policy =>
//    {
//        policy.AllowAnyHeader()
//              .AllowAnyMethod()
//              .AllowCredentials()
//              .WithOrigins("http://localhost:3000");
//    });
//});


app.UseCors("AllowAll");
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI(options => {
        options.SwaggerEndpoint("/swagger/v1/swagger.json", "Weather API");
    });
}

app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();

app.Run();
