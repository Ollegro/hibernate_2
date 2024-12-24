## Проект имитация backend реализации онлайн кинотеатра. Используем hibernate,p6spy, mysql, docker,spring data jpa
### Создать контейнер
#### docker run --name mysql-container-8.0.30 -e MYSQL_ROOT_PASSWORD=root -d -v mysql-volume-8.0.30:/var/lib/mysql -p 3306:3306 mysql:8.0.30
### далее схему
#### docker exec -i mysql-container-8.0.30 mysql -u root -p'root' -e "CREATE SCHEMA IF NOT EXISTS movie;"

### Заполняем базу из дампа dump-hibernate-2.sql

### Мапим таблицы на сущности в папке entities.
### В Main в методе main реализуем транзакционные методы:
#### createCustomer();
#### customerRentInventory(Customer customer); - «покупатель сходил в магазин (store) и арендовал (rental)
#### customerReturnRentalFilmToStore(); - «покупатель пошел и вернул ранее арендованный фильм»
#### makeNewFilm(); - «сняли новый фильм, и он стал доступен для аренды»

# Предложения по улучшению

## таблица "film_text"
Таблица «film_text» не связана с таблицей «film», поэтому ей необходимо получить внешний ключ;

## таблица "address"
Таблицу "address"  лучше сделать embedded;

## таблица"film"
Столбец «original_language_id» не должен иметь значение NULL, поскольку фильм всегда имеет какую то озвучку;
Столбец «special_features» имеет несколько значений для одного Film_id, лучше создать встроенную таблицу, так как один фильм может иметь несколько специальных функций;