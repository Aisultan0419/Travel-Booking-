using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace FlightInfrastructure.Migrations
{
    /// <inheritdoc />
    public partial class UserTable : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Users",
                columns: table => new
                {
                    Id = table.Column<Guid>(type: "uuid", nullable: false),
                    FirstName = table.Column<string>(type: "text", nullable: false),
                    LastName = table.Column<string>(type: "text", nullable: false),
                    Email = table.Column<string>(type: "text", nullable: false),
                    Password = table.Column<string>(type: "text", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Users", x => x.Id);
                });

            migrationBuilder.CreateIndex(
                name: "IX_FlightBooks_UserId",
                table: "FlightBooks",
                column: "UserId");

            migrationBuilder.AddForeignKey(
                name: "FK_FlightBooks_Users_UserId",
                table: "FlightBooks",
                column: "UserId",
                principalTable: "Users",
                principalColumn: "Id",
                onDelete: ReferentialAction.Restrict);
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_FlightBooks_Users_UserId",
                table: "FlightBooks");

            migrationBuilder.DropTable(
                name: "Users");

            migrationBuilder.DropIndex(
                name: "IX_FlightBooks_UserId",
                table: "FlightBooks");
        }
    }
}
