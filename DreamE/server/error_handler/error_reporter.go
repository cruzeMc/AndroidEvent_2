package error_handler

func Check(e error) {
	if e != nil {
		panic(e.Error())
	}
}
