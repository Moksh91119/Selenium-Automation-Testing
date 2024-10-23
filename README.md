# Selenium Automation Testing

This project is an automation testing framework developed using **Selenium** with **Java** (via Eclipse IDE). It focuses on automating key workflows of the [OrangeHRM website](https://opensource-demo.orangehrmlive.com/web/index.php/auth/login). The project runs 9 test cases and generates a `testresults.csv` file, indicating the pass/fail status of each test case.

## Test Cases

1. **doLoginWithInvalidCredential** – Verifies login failure with incorrect credentials.
2. **loginTestWithValidCredential** – Verifies successful login with valid credentials.
3. **addEmployee** – Automates the addition of a new employee.
4. **searchEmployeeByName** – Tests the search functionality by employee name.
5. **searchEmployeeById** – Tests the search functionality by employee ID.
6. **fileUpload** – Automates the file upload feature.
7. **applyLeave** – Automates the leave application process.
8. **listEmployees** – Verifies listing of all employees.
9. **deleteEmployee** – Automates the deletion of an employee.

## Features

- **Selenium WebDriver**: Automates web interactions for end-to-end testing.
- **JUnit/TestNG**: Utilized for test case management.
- **Apache POI**: Handles test result data export to CSV format.
- **CSV Reporting**: Automatically generates `testresults.csv`, providing a summary of test execution status.

## Getting Started

### Prerequisites

- **Java Development Kit (JDK)** – Version 8 or higher.
- **Eclipse IDE** – With Maven support.
- **Maven** – Used for project dependency management.

### Clone the Repository

```bash
git clone https://github.com/Moksh91119/Selenium-Automation-Testing.git
```

### Project Setup

1. **Install Dependencies**:
    - Open the project in Eclipse.
    - Maven will automatically download the required dependencies based on the `pom.xml` file.

2. **Configure Tests**:
    - Update the test configuration and add your desired test cases in the `TestNG` XML file.

3. **Run Tests**:
    - You can execute tests directly from Eclipse or using Maven commands.

    ```bash
    mvn test
    ```

### Generate Test Results

Once the tests have run, a file named `testresults.csv` will be generated in the root directory, showing the pass/fail status for each test case.

## Dependencies

The project uses the following libraries (defined in the `pom.xml` file):

- **Selenium Java (v4.18.1)** – Automates the testing of web applications.
- **JUnit (v3.8.1)** – Provides basic testing framework.
- **TestNG (v7.7.1)** – Advanced testing framework.
- **Apache POI (v5.2.3)** – Used to handle file generation for test reports.
- **SLF4J (v1.7.36)** – Simple Logging Facade for Java.

## Contact

If you have any questions or need further assistance, feel free to contact me:

- **Email**: [jainmoksh03@gmail.com](mailto:jainmoksh03@gmail.com)
- **Portfolio**: [itsmemoksh.in](https://itsmemoksh.in/) - Learn more about me and explore my other projects.

---

Feel free to open issues or contribute to the project. Your feedback and contributions are always welcome!
