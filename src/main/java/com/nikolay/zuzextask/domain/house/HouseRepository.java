package com.nikolay.zuzextask.domain.house;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {
    boolean existsByLocalityAndStreetAndNumber(String locality, String street, String number);
}
