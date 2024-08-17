package entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.Instant;
@Setter
@Getter
@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @Column(name = "staff_id", columnDefinition = "tinyint UNSIGNED not null")
    private Short id;
    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
    @Column(name = "picture", columnDefinition = "BLOB" )
    private byte[] picture;
    @Column(name = "email", length = 50)
    private String email;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
    @Column(name = "active", nullable = false)
    private Boolean active = false;
    @Column(name = "username", nullable = false, length = 16)
    private String username;
    @Column(name = "password", length = 40)
    private String password;
    @Column(name = "last_update", nullable = false)
    private Instant lastUpdate;

}