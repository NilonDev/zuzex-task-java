package com.nikolay.zuzextask.domain.house;

import com.nikolay.zuzextask.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "houses")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "locality")
    private String locality;
    @Column(name = "street")
    private String street;
    @Column(name = "number")
    private String number;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany
    @JoinTable(
            name = "_users_houses",
            joinColumns = @JoinColumn(name = "house_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> lodgers = new HashSet<>();

//    @PreRemove
//    public void clearLodgers() {
//        lodgers.clear();
//    }

    public void addLodger(User lodger) {
        lodgers.add(lodger);
    }

    public void removeLodger(User lodger) {
        lodgers.remove(lodger);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return Objects.equals(id, house.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", locality='" + locality + '\'' +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
