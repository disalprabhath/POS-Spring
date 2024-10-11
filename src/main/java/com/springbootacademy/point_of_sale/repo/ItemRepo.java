package com.springbootacademy.point_of_sale.repo;

import com.springbootacademy.point_of_sale.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface ItemRepo extends JpaRepository<Item,Integer> {

    List<Item> findAllByItemNameEqualsAndActiveStateEquals(String itemName, boolean activeState);

    List<Item> findAllByActiveStateEquals(boolean activeStatus);

    Page<Item> findAllByActiveStateEquals(boolean activeStatus, Pageable pageable);

    int countAllByActiveStateEquals(boolean activeStatus);
}
