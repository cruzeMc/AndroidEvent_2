package routes

import (
	"../controller"
	"github.com/gorilla/mux"
	"log"
	"net/http"
)

func RunServer() {
	router := mux.NewRouter().StrictSlash(true)
	router.HandleFunc("/index", controller.IndexRoute)
	router.HandleFunc("/api/landing", controller.LandingRoute)
	router.HandleFunc("/api/landing_image", controller.LandingImageRoute)
	router.HandleFunc("/api/login", controller.LoginRoute)
	router.HandleFunc("/api/registration", controller.RegistrationRouter)
	log.Fatal(http.ListenAndServe(":9876", router))
}
