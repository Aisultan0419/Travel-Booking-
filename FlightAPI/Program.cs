using FlightApplication._8assignment;
using FlightApplication.Interfaces;
using FlightDomain.Interfaces;
using FlightInfrastructure.Database;
using FlightDomain.Models;
using FlightInfrastructure.Repositories;
using Microsoft.EntityFrameworkCore;
var builder = WebApplication.CreateBuilder(args);



builder.Services.AddControllers();
builder.Services.AddSwaggerGen();
builder.Services.AddOpenApi();

builder.Services.AddScoped<IPlaneRepository, PlaneRepository>();
builder.Services.AddScoped<IVisitor, ConcretePlaneVisitor>();
builder.Services.AddScoped<IFlight, Flight>();
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
