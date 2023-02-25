package vkir.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vkir.model.Data;

public interface DataJpaRepository extends JpaRepository<Data, Long> {
}