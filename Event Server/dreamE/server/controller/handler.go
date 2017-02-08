package controller

import (
	"../database"
	"../datastore"
	"../error_handler"
	"encoding/json"
	"fmt"
	"html"
	"io/ioutil"
	//"log"
	"net/http"
	"strings"
)

const (
	IMAGE_FILE_JPG = "C:/Users/Cruze/Documents/Project/dreamvents/app/"
	IMAGE_FILE_PNG = "C:/Users/Cruze/Documents/Project/dreamvents/app/"
)

func IndexRoute(response http.ResponseWriter, request *http.Request) {
	fmt.Fprint(response, "Hello, %q", html.EscapeString(request.URL.Path))
}

func LandingRoute(response http.ResponseWriter, request *http.Request) {
	switch request.Method {
	case "GET":
		json.NewEncoder(response).Encode(database.GetCategories())
	}
}
func LandingImageRoute(response http.ResponseWriter, request *http.Request) {
	switch request.Method {
	case "POST":
		imageDecoder := json.NewDecoder(request.Body)

		var image datastore.LandingImage
		err := imageDecoder.Decode(&image)
		error_handler.Check(err)

		if strings.HasSuffix(image.Image, ".jpg") {
			file, err := ioutil.ReadFile(IMAGE_FILE_JPG + image.Image)
			if err != nil {
				http.Error(response, err.Error(), http.StatusNotFound)
				return
			}
			response.Header().Set("Content-type", "image/jpg")
			response.Write(file)
		} else if strings.HasSuffix(image.Image, ".png") {
			file, err := ioutil.ReadFile(IMAGE_FILE_PNG + image.Image)
			if err != nil {
				http.Error(response, err.Error(), http.StatusNotFound)
				return
			}
			response.Header().Set("Content-type", "image/png")
			response.Write(file)
		} else {
			http.Error(response, "Not a valid file type", http.StatusNotFound)
			return
		}
	}
}

func RegistrationRouter(response http.ResponseWriter, request *http.Request) {
	switch request.Method {
	case "PUT":
		registrationDecoder := json.NewDecoder(request.Body)

		var registration datastore.Registration

		err := registrationDecoder.Decode(&registration)
		error_handler.Check(err)
	}
}

func LoginRoute(response http.ResponseWriter, request *http.Request) {
	switch request.Method {
	case "POST":
		loginDecoder := json.NewDecoder(request.Body)

		var login datastore.Login

		err := loginDecoder.Decode(&login)
		error_handler.Check(err)

		result := database.LoginUser(login.Email, login.Password)

		var boolean = datastore.NewBoolean(result)
		if result == true {
			json.NewEncoder(response).Encode(boolean)
		} else {
			json.NewEncoder(response).Encode(boolean)
		}
	}
}