# Mini TO-DO app

### About the project

**Todoist** In the application, users should be able to register, login and create/update/delete their own to-do lists.

You can list, add, update, delete the category, todo and user.

Tech Stack:
* [Java     17](#) 
* [Spring Boot](#)
* [Spring Data](#) 
* [H2 Database](#)
* [JUnit5](#)
* [Git](#)
* [MapStruct](#) 
* [JWT](#)
* [Spring Security](#) 
* [Validation](#)
* [Exception Handling](#)
* [Swagger](#)
* [Maven](#)
* [Lombok](#)


## HTTP Client Structure

|           Controller           | Method |            Addres            |            Description             |
|:------------------------------:|:------:|:----------------------------:|:----------------------------------:|
|  **AuthenticationController**  |  POST  | localhost:8080/auth/register | user registers in the application  |
|                                |  POST  |  localhost:8080/auth/login   | the user logs into the application |

|     Controller     | Metot  |           Adres           |      Description      |
|:------------------:| :----: |:-------------------------:|:---------------------:|
| **UserController** |  GET   |   localhost:8080/users    |  Will List All users  |
|                    |  GET   | localhost:8080/users/{id} | will get user with id |
|                    |  POST  |   localhost:8080/users    |     create a user     |
|                    |  PUT   |   localhost:8080/users    |   will update user    |
|                    | DELETE | localhost:8080/users/{id} |   will delete user    |


|     Controller     | Metot  |            Adres             |      Description      |
|:------------------:| :----: |:----------------------------:|:---------------------:|
| **TodoController** |  GET   |   localhost:8080/api/todo    |  will List All todos  |
|                    |  GET   | localhost:8080/api/todo/{id} | will get todo with id |
|                    |  POST  |   localhost:8080/api/todo    |     create a todo     |
|                    |  PUT   |   localhost:8080/api/todo    |   will update todo    |
|                    | DELETE | localhost:8080/api/todo/{id} |   will delete todo    |




|       Controller       | Metot  |                Adres                 |                      Description                      |
|:----------------------:|:------:|:------------------------------------:|:-----------------------------------------------------:|
| **CategoryController** |  GET   |      localhost:8080/categories       |               Will List All categories                |
|                        |  GET   |    localhost:8080/categories/{id}    |               will get category with id               |
|                        |  POST  |      localhost:8080/categories       |                   create a category                   |
|                        |  PUT   |      localhost:8080/categories       |                 will update category                  |
|                        | DELETE |    localhost:8080/categories/{id}    |             will delete category with id              |
|                    |  GET   | localhost:8080/categories/users/{id} | returns the list of the categories of a selected user |
|                    |  GET   | localhost:8080/categories/todos/{id} |  returns the list of the Todo of a selected category  |

