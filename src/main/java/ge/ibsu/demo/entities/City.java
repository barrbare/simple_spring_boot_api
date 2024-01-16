package ge.ibsu.demo.entities;

import jakarta.persistence.*;

public class City {
    @Id
    @SequenceGenerator(name = "city_city_id_seq", sequenceName = "city_city_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "city_customer_id_seq")
    @Column(name = "city_id")
    private Long id;
    @Column(name = "city")
    private String city;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getCity() { return city; }

    public void setCity(String city) {this.city = city;}
}
