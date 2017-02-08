package routes

import (
	"../controller"
	"github.com/gorilla/mux"
	"log"
	"net/http"
	"path/filepath"
)

const (
	IMAGE_FILE = "C:/Project/dreamvents/app/static/background/jpg/"
)

func RunServer() {
	router := mux.NewRouter().StrictSlash(true)
	router.HandleFunc("/index", controller.IndexRoute)
	router.HandleFunc("/api/landing", controller.LandingRoute)
	router.HandleFunc("/api/landing_image", controller.LandingImageRoute)
	router.HandleFunc("/api/login", controller.LoginRoute)
	router.HandleFunc("/api/registration", controller.RegistrationRouter)
	router.PathPrefix("/api/landing/image/").Handler(http.StripPrefix("/api/landing/image/", http.FileServer(http.Dir(filepath.FromSlash(IMAGE_FILE)))))
	log.Fatal(http.ListenAndServe(":9876", router))
}
