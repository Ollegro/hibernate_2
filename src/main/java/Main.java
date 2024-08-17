import dao.GenericDAO;
import entities.*;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Main {
    Util util = new Util();
    public static void main(String[] args) {
        // Ваша логика работы с базой данных
        Main main = new Main();
        Customer customer = main.createCustomer();
        main.customerRentInventory(customer);
        main.makeNewFilm();
    }

    private Customer createCustomer() {
        Util util = new Util();
        try (Session session = util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            Store store = util.getStoreDAO().getItems(0, 1).get(0);
            City city = util.getCityDAO().getByName("Kursk");


            Address address = new Address();
            address.setAddress("Example address");
            address.setCity(city);
            address.setPhone("9999999999");
            address.setDistrict("Example distr");
            util.getAddressDAO().save(address);
            Customer customer = new Customer();
            customer.setActive(true);
            customer.setAddress(address);
            customer.setStore(store);
            customer.setEmail("example@email.com");
            customer.setFirstName("John");
            customer.setLastName("Boe");
            util.getCustomerDAO().save(customer);
            session.getTransaction().commit();
            return  customer;
        }

    }

    private void customerRentInventory(Customer customer) {

        try (Session session = util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction(); // Начало транзакции

            Film film = util.getFilmDAO().getFirstAvailableFilmForRent(); // Запрос теперь выполняется в рамках активной транзакции
            Store store = util.getStoreDAO().getItems(0, 1).get(0);

            Inventory inventory = new Inventory();
            inventory.setFilm(film);
            inventory.setStore(store);
            util.getInventoryDAO().save(inventory);

            Staff staff = store.getManagerStaff();

            Rental rental = new Rental();
            rental.setRentalDate(LocalDateTime.now());
            rental.setCustomer(customer);
            rental.setInventory(inventory);
            rental.setStaff(staff);
            util.getRentalDAO().save(rental);

            Payment payment = new Payment();
            payment.setRental(rental);
            payment.setPaymentDate(LocalDateTime.now());
            payment.setCustomer(customer);
            payment.setAmount(BigDecimal.valueOf(20.99));
            payment.setStaff(staff);
            util.getPaymentDAO().save(payment);

            session.getTransaction().commit(); // Завершение транзакции
        }
    }

    private void customerReturnRentalFilmToStore() {

        try (Session session = util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            Rental rental = util.getRentalDAO().getAnyUnReturnedRental();
            session.save(rental);
            session.getTransaction().commit();
        }
    }

    private void makeNewFilm() {

        try (Session session = util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            Language language = util.getLanguageDAO().getItems(0, 20).stream().unordered().findAny().get();
            List<Category> categories = util.getCategoryDAO().getItems(1, 5);
            List<Actor> actors = util.getActorDAO().getItems(0, 10);
            Film film = new Film();
            film.setActors(new HashSet<>(actors));
            film.setRating(Rating.NC17);
            film.setCategories(new HashSet<>(categories));
            film.setYear(Year.now());
            film.setSpecialFeatures(Set.of(SpecialFeature.TRAILERS, SpecialFeature.COMMENTARIES));
            film.setLength((short) 128);
            film.setReplacementCost(BigDecimal.TEN);
            film.setRentalRate(BigDecimal.ZERO);
            film.setLanguage(language);
            film.setDescription("Something like horror");
            film.setTitle("Tsar");
            film.setOriginalLanguage(language);
            film.setRentalDuration((byte) 20);
            util.getFilmDAO().save(film);
            FilmText filmText = new FilmText();
            filmText.setId(film.getId());
            filmText.setDescription("SciFi style");
            filmText.setFilm(film);
            filmText.setTitle("Lenin v Oktyabre");
            util.getFilmTextDAO().save(filmText);
            session.getTransaction().commit();
        }
    }



}