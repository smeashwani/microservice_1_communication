POST- http://localhost:8080/api/departments
{
    "departmentName":"IT",
    "departmentAddress" :"INDIA",
    "departmentCode": "IT_INDIA"
}

POST - http://localhost:8081/api/users
{
	"firstName": "Guest",
	"lastName": "First",
	"email": "abc@test.com",
	"departmentId": "1"
}

GET-  http://localhost:8081/api/users/1