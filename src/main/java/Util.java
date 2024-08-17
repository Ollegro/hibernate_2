import dao.*;
import entities.*;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Getter
public class Util {
    private final SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    private final GenericDAO<Actor> actorDAO = new GenericDAO<>(Actor.class, sessionFactory);
    private final GenericDAO<Address> addressDAO = new GenericDAO<>(Address.class, sessionFactory);
    private final GenericDAO<Category> categoryDAO = new GenericDAO<>(Category.class, sessionFactory);
    private final CityDAO cityDAO = new CityDAO(sessionFactory);
    private final GenericDAO<Country> countryDAO = new GenericDAO<>(Country.class, sessionFactory);
    private final GenericDAO<Customer> customerDAO = new GenericDAO<>(Customer.class, sessionFactory);
    private final FilmDAO filmDAO = new FilmDAO(getSessionFactory());
    private final GenericDAO<FilmText> filmTextDAO = new GenericDAO<>(FilmText.class, sessionFactory);
    private final GenericDAO<Inventory> inventoryDAO = new GenericDAO<>(Inventory.class, sessionFactory);
    private final GenericDAO<Language> languageDAO = new GenericDAO<>(Language.class, sessionFactory);
    private final GenericDAO<Payment> paymentDAO = new GenericDAO<>(Payment.class, sessionFactory);
    private final GenericDAO<Rating> ratingDAO = new GenericDAO<>(Rating.class, sessionFactory);
    private final RentalDAO rentalDAO = new RentalDAO(sessionFactory);
    private final GenericDAO<SpecialFeature> specialFeatureDAO = new GenericDAO<>(SpecialFeature.class, sessionFactory);
    private final GenericDAO<Staff> staffDAO = new GenericDAO<>(Staff.class, sessionFactory);
    private final GenericDAO<Store> storeDAO = new GenericDAO<>(Store.class, sessionFactory);

}