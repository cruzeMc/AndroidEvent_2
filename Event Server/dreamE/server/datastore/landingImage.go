package datastore

type LandingImage struct {
	Image string `json:"image"`
}

func NewLandingImage(image string) *LandingImage {
	return &LandingImage{
		Image: image,
	}
}

func (l *LandingImage) SetLandingImage(image string) {
	l.Image = image
}

func (l *LandingImage) GetLandingImage() string {
	return l.Image
}
