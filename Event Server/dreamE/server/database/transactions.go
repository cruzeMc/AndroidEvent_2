package database

import (
	"../datastore"
	"../error_handler"
	"database/sql"
	"fmt"
	_ "github.com/lib/pq"
	//"net/http"
	//"encoding/json"
	//"log"
)

const (
	DB_HOST     = "localhost"
	DB_PORT     = 5432
	DB_USER     = "dreamvents"
	DB_PASSWORD = "Naruto123?"
	DB_NAME     = "capstone"
)

// Function to Open Postgres database
func OpenPostgresDB() *sql.DB {
	// Database connection information
	dbinfo := fmt.Sprintf("host=%s port=%d user=%s password=%s dbname=%s sslmode=disable", DB_HOST, DB_PORT, DB_USER, DB_PASSWORD, DB_NAME)
	// Connect to database
	db, err := sql.Open("postgres", dbinfo)
	error_handler.Check(err)

	return db
}

// Function to get categories from database
func GetCategories() []*datastore.Category {
	// Connect to database
	db := OpenPostgresDB()
	defer db.Close()

	// Query database
	rows, err := db.Query("SELECT * FROM category")
	error_handler.Check(err)

	// Store categories for landing page in this array
	landingPage := make([]*datastore.Category, 0)

	// Go through list of categories returned from db
	for rows.Next() {
		var id int
		var name string
		var image string
		err = rows.Scan(&id, &name, &image)

		error_handler.Check(err)

		// Store single instance of categories
		var lpage = datastore.NewCategory(id, name, image)

		// Store above categories in landing page array
		landingPage = append(landingPage, lpage)
	}
	return landingPage
}

func RegisterNewUser(fname, lname, usersname, pic, email, address, password string) {
	db := OpenPostgresDB()
	defer db.Close()
}

func LoginUser(email, password string) bool {
	// Connect to database
	db := OpenPostgresDB()
	defer db.Close()

	// Prepare query before execution
	queryRow, err := db.Prepare("SELECT email, password FROM users WHERE email = $1 AND password = $2")
	error_handler.Check(err)

	// Query database by executing prepared statement
	var em, pass string
	err = queryRow.QueryRow(email, password).Scan(em, pass)

	// Check if there was any row
	if err == sql.ErrNoRows {
		return false
	}

	return true


}