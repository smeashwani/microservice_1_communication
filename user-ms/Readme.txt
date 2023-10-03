POST- http://localhost:9999/api/departments
{
    "departmentName":"IT",
    "departmentAddress" :"INDIA",
    "departmentCode": "IT_INDIA"
}

POST - http://localhost:8888/api/users
{
	"firstName": "Guest",
	"lastName": "First",
	"email": "abc@test.com",
	"departmentId": "1"
}

GET-  http://localhost:8888/api/users/1