


<h1 align="center">
  <br>
<a  href="https://spring.io/"  target="_blank"  rel="noreferrer"> <img  src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg"  alt="spring"  width="180"  height="180"/> </a>
  <br>
  Spring Data [Learning]
  <br>
  <br>
</h1>

<p align="center">
  <a href="#project-description">Project Description</a> |
  <a href="#tech-stack-and-libraries">Tech Stack and Libraries</a> |
  <a href="#how-it-works">How it Works</a> |
  <a href="#code-examples">Code Examples</a> |
  <a href="#acknowledgements">Acknowledgements</a>
</p>



<div id="project-description"></div>

## 🚀 Project Description
Spring-data-learning is a project based on the "Working with data chapter" of the "Spring in action" book. The project demonstrates how to use Spring's JdbcTemplate, create Spring Data JDBC repositories, and declare JPA repositories with Spring Data. The project enables data persistence for the Taco Cloud application, allowing users to maintain information about ingredients, tacos, and orders.

<div id="tech-stack-and-libraries"></div>

## 🛠️ Tech Stack and Libraries
The project is built using Java and the Spring Framework. The following libraries and tools were used in the project:
- Java Spring Framework
- Spring Boot
- Spring Data JDBC
- Spring Data JPA
- H2 Database

<div id="how-it-works"></div>

## ⚙️ How it Works

The project is a basic web application that allows users to view and manage taco ingredients, tacos, and orders. It uses Spring's JdbcTemplate to interact with the database and Spring Data JDBC repositories to eliminate boilerplate code. Then, it reworks the data repositories to work with JPA, which eliminates even more code. The H2 database is used for data persistence, and Lombok is used to generate boilerplate code.

[![Screenshot-from-2023-03-21-09-44-46.png](https://i.postimg.cc/pT8Wp8GC/Screenshot-from-2023-03-21-09-44-46.png)](https://postimg.cc/BLS9z8GP)

<div id="code-examples"></div>

## 💻 Code Examples
1.Example of a Spring Data JDBC repository for the Ingredient entity:
```java
@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, String> {
  List<Ingredient> findAll();
  Ingredient findById(String id);
  Ingredient save(Ingredient ingredient);
}
```
2.Example of a JPA repository for the Order entity:
```java
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
  List<Order> findByDeliveryZip(String deliveryZip);
}
```
3.Example of a JdbcTemplate query to retrieve all ingredients:
```java
public List<Ingredient> findAll() {
  return jdbcTemplate.query("select id, name, type from Ingredient", this::mapRowToIngredient);
}

private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
  return new Ingredient(
    rs.getString("id"),
    rs.getString("name"),
    Ingredient.Type.valueOf(rs.getString("type")));
}
```

<div id="acknowledgements"></div>

## 📚 Acknowledgements 
This project was created with the help of the book **"Spring in Action"** by **Craig Walls** and **Ryan Breidenbach**. Many of the concepts and techniques used in this project were learned from this valuable resource.

