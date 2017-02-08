package datastore

type Login struct {
	Email    string `json:"email"`
	Password string `json:"password"`
}

func NewLogin(email, password string) *Login {
	return &Login{
		Email:    email,
		Password: password,
	}
}

func (l *Login) GetEmail() string {
	return l.Email
}

func (l *Login) SetEmail(email string) {
	l.Email = email
}

func (l *Login) GetPassword() string {
	return l.Password
}

func (l *Login) SetPassword(password string) {
	l.Password = password
}
