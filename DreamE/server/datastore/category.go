package datastore

type Category struct {
	Id           int    `json:"id"`
	CategoryName string `json:"category_name"`
	Image        string `json:"image"`
}

// Category constructor
func NewCategory(id int, cat, img string) *Category {
	return &Category{
		Id:           id,
		CategoryName: cat,
		Image:        img,
	}
}

// Set Category ID
func (c *Category) SetID(id int) {
	c.Id = id
}

// Set Category Name
func (c *Category) SetCatName(cat string) {
	c.CategoryName = cat
}

// Set Category Image
func (c *Category) SetImage(img string) {
	c.Image = img
}

// Get Category Id
func (c *Category) GetID() int {
	return c.Id
}

// Get Category Name
func (c *Category) GetName() string {
	return c.CategoryName
}

// Get Category Image
func (c *Category) GetImage() string {
	return c.Image
}
