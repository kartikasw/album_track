# Java Album Track

## Tech Stack

- Java
- Spring Boot
- PostgreSQL JDBC Driver
- Flyway
- Hibernate
- Maven

### Installation

1. **Clone the repository:**

    ```sh
    git clone https://github.com/kartikasw/album_track
    cd your-repo-name
    ```
    
2. **Run the Program:**
   
   ```sh
   ./mvnw spring-boot:run
   ```
  
## API Routes

Here is a list of existing routes available in the API:

| Method   | URL                 | Description                                     |
| :------- |:------------------- | :---------------------------------------------- |
| POST     | /api/v1/artist      | Add new artist                                  |
| GET      | /api/v1/artist      | Get artist list (parameters: name, album_name)  |
| POST     | /api/v1/album       | Add new album                                   |
| PUT      | /api/v1/album/{id}  | Update existing album                           |
| GET      | /api/v1/album       | Get album list (parameters: name, release_sort) |
| DELETE   | /api/v1/album/{id}  | Delete existing album                           |
