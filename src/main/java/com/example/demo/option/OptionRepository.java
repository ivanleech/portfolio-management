package com.example.demo.option;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface OptionRepository extends JpaRepository<Option, OptionId> {
    // Custom queries can be added here if needed
    List<Option> findAllBySymbolAndTimeToMaturity(String symbol, Integer timeToMaturity);

}
