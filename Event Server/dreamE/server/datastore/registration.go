package datastore

type Registration struct {
	Fname     string `json:"fname"`
	Lname     string `json:"lname"`
	Usersname string `json:"usersname"`
	Pic       string `json:"pic"`
	Email     string `json:"email"`
	Address   string `json:"address"`
	Password  string `json:"password"`
}

func NewRegistration(fname, lname, username, pic, email, address, password string) *Registration {
	return &Registration{
		Fname:     fname,
		Lname:     lname,
		Usersname: username,
		Pic:       pic,
		Email:     email,
		Address:   address,
		Password:  password,
	}
}

func (r *Registration) GetFname() string {
	return r.Fname
}

func (r *Registration) SetFname(fname string) {
	r.Fname = fname
}

func (r *Registration) GetLname() string {
	return r.Lname
}

func (r *Registration) SetLname(lname string) {
	r.Lname = lname
}

func (r *Registration) GetUsersname() string {
	return r.Usersname
}

func (r *Registration) SetUsersname(usersname string) {
	r.Usersname = usersname
}

func (r *Registration) GetPic() string {
	return r.Pic
}

func (r *Registration) SetPic(pic string) {
	r.Pic = pic
}

func (r *Registration) GetEmail() string {
	return r.Email
}

func (r *Registration) SetEmail(email string) {
	r.Email = email
}

func (r *Registration) GetAddress() string {
	return r.Address
}

func (r *Registration) SetAddress(address string) {
	r.Address = address
}

func (r *Registration) GetPassword() string {
	return r.Password
}

func (r *Registration) SetPassword(password string) {
	r.Password = password
}
