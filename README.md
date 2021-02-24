# NerdySoft
Test task for NerdySoft

The application helps with floor planning, checks the validity of the room.
It processes some REST requests and stores valid rooms in a "database" - HashMap (for example only). The map can be easily replaced with any other database.

## Commands:
POST /addRoom (with request body) - checks whether the room is valid and writes it to the database. The request body must be an array of points in string format, Example: "(1,1),(1,2),(2,2),(2,1)". If the room is not valid, it returns an error with the text.

POST /validateRoom (with request body) - checks whether the room is valid and writes it to the database. The request body must be in JSON format (example below). If the room is not valid, it returns an error with the plaintext description.

GET /rooms - returns a list of all rooms in the database.

GET /rooms/{id} - returns a room with the index "id" from the database.

PUT /rooms/{id} (with request body) - updates the room with the index "id" in the database. The request body must be in JSON format.

DELETE /rooms/{id} - deletes a room with the index "id" from the database.

## Examples:
> POST /addRoom

(POST http://localhost:8080/addRoom)

Request body:
```
(1,1),(1,2),(2,2),(2,1)
```
Legal. It is a square.

> POST /addRoom

Request body:
```
(1,1),(1,2),(0,2),(0,1)
```
Illegal. It is infinite room.

> POST /validateRoom

Request body:
```
{
	"room" : [
		{"x" : 1, "y" : 1},
		{"x" : 1, "y" : 2},
		{"x" : 2, "y" : 2},
		{"x" : 2, "y" : 1}
	]
}
```
Legal.

> POST /validateRoom

Request body:
```
{
	"room" : [
		{"x" : 1, "y" : 1},
		{"x" : 1, "y" : 2},
		{"x" : 0, "y" : 2},
		{"x" : 0, "y" : 3},
    {"x" : 2, "y" : 3},
    {"x" : 2, "y" : 1}
  ]
}
```
Legal.

> POST /validateRoom

Request body:
```
{
	"room" : [
		{"x" : 1, "y" : 1},
		{"x" : 1, "y" : 2},
		{"x" : 0, "y" : 2},
		{"x" : 0, "y" : 3},
    {"x" : 2, "y" : 3},
    {"x" : 2, "y" : 1}
	]
}
```
Legal.

> POST /validateRoom

Request body:
```
{
	"room" : [
		{"x" : 1, "y" : 1},
		{"x" : 2, "y" : 2}
	]
}
```
Illegal.

> POST /validateRoom

Request body:
```
{
	"room" : [
		{"x" : 1, "y" : 1},
		{"x" : 1, "y" : 2},
		{"x" : 0, "y" : 2},
		{"x" : 0, "y" : 1}
	]
}
```
Illegal.

> PUT /rooms/1

Request body:
```
{
	"room" : [
		{"x" : 1, "y" : 1},
		{"x" : 1, "y" : 2},
		{"x" : 2, "y" : 2},
		{"x" : 2, "y" : 1}
	]
}
```
Legal.

> PUT /rooms/1

Request body:
```
{
	"room" : [
		{"x" : 1, "y" : 1},
		{"x" : 1, "y" : 2},
		{"x" : 0, "y" : 2},
		{"x" : 0, "y" : 1}
	]
}
```
Illegal.
