package datastore

type Boolean struct {
	Success bool `json:"success"`
}

func NewBoolean(success bool) *Boolean {
	return &Boolean{
		Success: success,
	}
}

func (b *Boolean) GetSuccess() bool {
	return b.Success
}

func (b *Boolean) SetSuccess(success bool) {
	b.Success = success
}