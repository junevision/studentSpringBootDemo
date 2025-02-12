## studentSpringBootDemo

The project is a Spring Boot application for managing student information. Here is a brief explanation of the key components:

1. **`StudentService` Interface**:
   - Defines the contract for student-related operations such as getting a student, getting all students, adding a new student, deleting a student, and updating a student.

2. **`StudentServiceImpl` Class**:
   - Implements the `StudentService` interface.
   - Contains methods to handle CRUD operations for students.
   - Uses `StudentMapper` to interact with the database.
   - Methods include:
      - `getStudent(Long studentId)`: Retrieves a student by ID.
      - `getStudents()`: Retrieves all students.
      - `addNewStudent(StudentDto studentDto)`: Adds a new student.
      - `deleteStudent(Long studentId)`: Deletes a student by ID.
      - `updateStudent(StudentDto studentDto)`: Updates a student's information.

3. **`StudentMapper` Interface**:
   - Extends `BaseMapper` to provide CRUD operations for `Student` entities.
   - Contains custom query methods such as `findStudentByEmail(String email)`.

4. **`Student` Class**:
   - Represents the student entity with fields such as ID, name, email, and date of birth.

5. **`StudentDto` Class**:
   - Data Transfer Object for transferring student data between layers.
   - Includes fields such as ID, name, email, date of birth, and age.
   - Contains logic to calculate the age based on the date of birth.

6. **`StudentConfig` Class**:
   - Configuration class that initializes some student data using `CommandLineRunner`.
   - Truncates the `student` table at startup to reset IDs.

7. **Unit Tests**:
   - `StudentServiceImplTest` class contains unit tests for the `StudentServiceImpl` class using Mockito for mocking dependencies.

The project uses Java, Spring Boot, MyBatis Plus, and Maven for dependency management.