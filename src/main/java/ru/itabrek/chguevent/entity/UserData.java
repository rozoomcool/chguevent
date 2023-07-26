package ru.itabrek.chguevent.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users_data")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserData {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "phone")
    private String phone;
    @Column(name = "age")
    private int age;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }
}
