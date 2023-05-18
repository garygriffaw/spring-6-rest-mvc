package com.garygriffaw.spring6restmvc.repositories;

import com.garygriffaw.spring6restmvc.entities.Beer;
import com.garygriffaw.spring6restmvc.model.BeerStyle;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
class BeerRepositoryTest {
    @Autowired
    BeerRepository beerRepository;

    @Test
    void testSaveBeer() {
        Beer savedBeer = beerRepository.save(Beer.builder()
                        .beerName("My Beer")
                        .beerStyle(BeerStyle.PALE_ALE)
                        .upc("123")
                        .price(new BigDecimal(9.99))
                .build());

        beerRepository.flush();

        assertThat(savedBeer).isNotNull();
        assertThat(savedBeer.getId()).isNotNull();
    }

    @Test
    void testSaveBeerNameTooLong() {
        assertThrows(ConstraintViolationException.class, () -> {
            Beer savedBeer = beerRepository.save(Beer.builder()
                    .beerName("012345678901234567890123456789012345678901234567891")
                    .beerStyle(BeerStyle.PALE_ALE)
                    .upc("123")
                    .price(new BigDecimal(9.99))
                    .build());

            beerRepository.flush();
        });
    }
}