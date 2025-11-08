using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace FlightInfrastructure.Migrations
{
    /// <inheritdoc />
    public partial class TablesCreation : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<string>(
                name: "Model",
                table: "Planes",
                type: "text",
                nullable: true);

            migrationBuilder.AddColumn<int>(
                name: "Seats",
                table: "Planes",
                type: "integer",
                nullable: false,
                defaultValue: 0);
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "Model",
                table: "Planes");

            migrationBuilder.DropColumn(
                name: "Seats",
                table: "Planes");
        }
    }
}
