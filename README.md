# Creating Databases Based on Extracted Information from Websites

## Project Description
The **Creating Databases Based on Extracted Information from Websites** project is an advanced tool that enables automatic data retrieval related to books from various websites. This tool utilizes web scraping technology to gather information about books, such as titles, prices, availability, and ratings, without the need for manual website browsing.

## System Architecture
The project is built on a classic client-server architecture. There are three main layers:

### Client Layer
- The client layer is what users see and interact with using a web browser.
- Technologies used include HTML, CSS, Thymeleaf, and JavaScript.
- The website is dynamically generated on the server and sent to the browser.

### Server Layer
- The server layer contains the code and logic that handles client requests, processes data, and manages communication between the client and the database.
- Technologies used include Java, Spring, and the H2 database.

### Business Layer
- The business layer contains the application logic that processes data and performs business-related operations.
- Example operations include adding books, genres, user authentication, and more.

## Project Modules
The project is divided into several modules, each with its unique tasks:

- **User Module:** Handles registration, login, and user account management.
- **Book Module:** Manages data related to books, such as title, price, availability, and ratings.
- **Rating Module:** Allows users to rate books and displays average ratings.
- **Administrative Module:** Provides administrators with tools to manage users and content.

## Key Implementation Elements
Key elements in the project include:

- **Models:** Classes representing the main entities in the application, such as Book, Genre, and User.
- **Repositories:** Repository interfaces for CRUD operations on entities in the database.
- **Services:** Service classes implementing the application's business logic.
- **Controllers:** Controllers handling HTTP requests from clients.
- **Views:** Thymeleaf HTML files creating the application's views.
- **Security Configuration:** Application security configuration.
- **application.properties or application.yml file:** Application configuration settings.
- **pom.xml:** Maven project file containing dependencies and build configurations.

## Features and Capabilities
1. **Web Scraper**
   - Advanced feature for automatically retrieving data related to books from various websites.
   - Utilizes web scraping technology to extract information about books, such as titles, prices, availability, and ratings.
   - Automates the data gathering process, saving time and effort.

2. **Export Data to CSV File**
   - Feature allowing the saving of book information to a CSV file.
   - A CSV file is a tabular data format that can be easily imported into other data analysis applications.

3. **Search Books Based on Query**
   - Users can search the database of books using various criteria, such as title, author, genre, etc.
   - Search results are displayed in a user-friendly manner, enabling users to find books of interest.

4. **Book Ratings and Reviews**
   - Users can rate books and leave reviews.
   - Average book ratings are displayed to assist other users in making decisions.

## Installation and Running
To run the project:

1. Clone the repository to your local machine.
2. Set up Java 11 or newer and Maven on your system.
3. Create a database and adjust settings in the `application.properties` or `application.yml` file.
4. Run the application using the `mvn spring-boot:run` command.
5. Open a web browser and navigate to http://localhost:8080.

## System Requirements
- Java 11 or newer
- Maven
- Web browser

## Authors
- Damian Jóźwiak
- damjoz1990@gmail.com

## Acknowledgments
- **Społeczna Akademia Nauk:** I am deeply thankful to Społeczna Akademia Nauk for providing the necessary academic support and resources that have been invaluable in bringing this project to fruition.

- **Dr. Konrad Grzanek:** I extend my heartfelt thanks to Dr. Konrad Grzanek for his exceptional guidance, expertise, and unwavering support throughout the journey of this project. His mentorship has been truly inspirational.

## Contact
Created by damjoz1990@gmail.com - feel free to contact me!
