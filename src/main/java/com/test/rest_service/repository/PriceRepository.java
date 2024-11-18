package com.test.rest_service.repository;

import com.test.rest_service.model.Price;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public interface PriceRepository extends JpaRepository<Price, Long> {
    //Query personalizada para buscar el precio aplicable a un producto y cadena en una fecha determinada ordenado por prioridad descendente
    List<Price> findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
            int productId, int brandId, LocalDateTime startDate, LocalDateTime endDate);
}