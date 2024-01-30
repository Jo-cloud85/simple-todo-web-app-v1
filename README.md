# Simple Spring Boot ToDo Web App v1

## Description

This is a really simple Maven-based Todo Web App built with/using:

-   Spring Framework and Spring Boot 3 (incl Spring Security)
-   Spring Data JPA and Hibernate
-   Bootstrap 5
-   Java JDK 21
-   H2-console (if you want to experiment with in-memory database first, before MySQL)
-   MySQL via Docker

## Features

-   Users are able to read, create, update and delete a todo from a todo list

## Table of Contents

-   [Prerequisites](#prerequisites)
-   [Installation](#installation)
-   [Configuration](#configuration)
-   [Usage](#usage)
-   [Acknowledgment](#acknowledgment)

## Prerequisites

Before you begin, ensure you have the following installed:

-   [Docker Desktop](https://www.docker.com/products/docker-desktop)
-   If you are using VS code to run this project, make sure you have installed:
    -   Extension Pack for Java
    -   Spring Boot Extension Pack
    -   Install [Java](https://www.oracle.com/java/technologies/downloads/)
    -   Install [Apache Maven](https://maven.apache.org/download.cgi)
-   Alternatively, you can download the [Eclipse IDE](https://www.eclipse.org/downloads/packages/installer) and import this project as a Maven project

## Installation

1. Clone the repository:

    ```
    git clone https://github.com/your-username/your-repo.git
    cd your-repo
    ```

2. Check that you have successfully installed Docker:

    ```
    docker- --version
    ```

3. Launch MySQL using Docker:

    ```
    docker run --detach --env MYSQL_ROOT_PASSWORD=dummypassword --env MYSQL_USER=social-media-user --env MYSQL_PASSWORD=dummypassword --env MYSQL_DATABASE=social-media-database --name mysql --publish 3306:3306
    ```

    This will start your Spring Boot application and a MySQL container.

4. Verify that the containers are running:

    ```
    docker-compose ps
    ```

## Configuration

-   Open a new terminal in VS code and go to the root directory of the folder and type in 'mvn clean install'
-   Run the application

## Usage

1. Type in the URL: http://localhost:8080
2. You will be prompted to type in your password and username. For this case, as the username and password are already pre-set, type in:

-   Username: in28minutes
-   Password: dummy

## Acknowledgement

This project is based on Spring Framework & Spring Boot course from in28minutes which can be found at [Udemy in28Minutes](https://www.udemy.com/course/spring-boot-and-spring-framework-tutorial-for-beginners)
