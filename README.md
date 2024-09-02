### Project: FarmCollector - Agricultural Data Collection System

---

### Overview

**FarmCollector** is an agricultural data collection system designed to track and report on the planting and harvesting activities of farms. The system captures detailed information about the types of crops planted, the expected yield, and the actual yield upon harvesting. This data is essential for analyzing farm productivity, optimizing crop selection, and improving agricultural practices.

---

# Stack

![](https://img.shields.io/badge/h2_memory_database-✓-blue.svg) 
![](https://img.shields.io/badge/java_17-✓-blue.svg)
![](https://img.shields.io/badge/spring_boot-✓-blue.svg)
![](https://img.shields.io/badge/swagger_3-✓-green.svg)

---

### Database Schema

The system utilizes a relational database schema consisting of three primary tables: **Farms**, **Plantations**, and **Harvests**. Each table is related through foreign keys to ensure data integrity and provide a comprehensive view of the farming operations.

#### Tables:

1. **Farms**:
    - `id`: Primary key, uniquely identifies each farm.
    - `name`: Name of the farm.
    - `description`: A brief description of the farm.
    - `latitude`: Geographic latitude of the farm.
    - `longitude`: Geographic longitude of the farm.
    - `created_at`: Timestamp when the farm record was created.
    - `last_modified`: Timestamp when the farm record was last modified.

2. **Plantations**:
    - `id`: Primary key, uniquely identifies each plantation.
    - `farm_id`: Foreign key referencing the farm associated with the plantation.
    - `created_at`: Timestamp when the plantation record was created.
    - `last_modified`: Timestamp when the plantation record was last modified.
    - `start_plantation`: Date when the plantation started.
    - `expected_harvest_date`: The expected date of harvest.
    - `season`: Enum representing the planting season.
    - `cropType`: The type of crop planted.
    - `planting_area_in_acres`: The area of land used for planting (in acres).
    - `expected_quantity_in_tons`: The expected quantity of the harvest (in tons).

3. **Harvests**:
    - `id`: Primary key, uniquely identifies each harvest record.
    - `farm_id`: Foreign key referencing the farm associated with the harvest.
    - `plantation_id`: Foreign key referencing the plantation associated with the harvest.
    - `created_at`: Timestamp when the harvest record was created.
    - `last_modified`: Timestamp when the harvest record was last modified.
    - `season`: Enum representing the harvest season.
    - `cropType`: The type of crop harvested.
    - `actual_quantity_in_tons`: The actual quantity of the harvested crop (in tons).

---

### Application Functionality

The application provides a set of RESTful APIs for managing farms, recording plantation and harvest data, and generating reports that compare expected and actual crop yields.

#### Key Features:

1. **Farm Management**: 
    - Create and manage farm records.
    - Record geographic data (latitude, longitude) for each farm.

2. **Plantation Data Collection**:
    - Record data on the types of crops planted, the area planted, and the expected yield.
    - Capture the season and start date of planting.

3. **Harvest Data Collection**:
    - Record the actual yield of crops harvested.
    - Link harvest data to specific plantations and farms.

4. **Reporting**:
    - Generate reports comparing expected vs. actual crop yields.
    - Reports can be filtered by farm, crop type, and season.

---

### API Endpoints

- **Farm Management**:
    - `POST /api/management/farms`: Create a new farm.
    - `GET /api/management/farms/{id}`: Retrieve details of a specific farm.
    - `GET /api/management/farms`: gets a paginated farm's details and you can search by farm name.
    - `PUT /api/management/farm/{farmId}`: Update a farm's details.
    - `DELETE /api/management/farms/{id}`: Delete a farm.

- **Plantation Management**:
    - `POST /api/management/plantation`: Record plantation data for a farm.
    - `GET /api/management/plantation/{Id}`: Retrieve plantation data by id.
    - `DELETE /api/management/plantation/{Id}`: delete a plantation data by id. 

- **Harvest Management**:
    - `POST /api/harvest/{farmId}/harvests`: Record harvest data for a farm.
    - `GET /api/harvest/{farmId}/harvests`: Retrieve harvest data for a farm.

- **Reporting**:
    - `GET /api/reports/seasonal`: Generate a report comparing expected vs. actual yields by farm and crop type.

---

### Error Handling
---

### Running the Application

1. **Clone the Repository**:
    ```
    git clone <repository-url>
    ```

2. **Build the Project**:
    Navigate to the project directory and run the following command:
    ```
    mvn clean install
    ```

3. **Run the Application**:
    Start the Spring Boot application with the following command:
    ```
    mvn spring-boot:run
    ```

4. **Access the Application**:
    The application will be available at `http://localhost:8080`.

---

### Testing

The application includes unit tests for all major functionalities, including API endpoints and service layer logic. Tests can be executed using the following command:

```
mvn test
```

---

### Conclusion

This application serves as a robust platform for collecting and analyzing farm data, providing valuable insights into agricultural operations. The combination of plantation and harvest data enables farmers and stakeholders to make informed decisions that can improve productivity and sustainability in farming.

For any issues or further development, please refer to the project's GitHub repository or contact the development team.