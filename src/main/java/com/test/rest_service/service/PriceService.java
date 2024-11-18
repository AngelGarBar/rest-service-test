package com.test.rest_service.service;

import com.test.rest_service.model.Price;
import com.test.rest_service.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PriceService {

    private final PriceRepository priceRepository;


    @Autowired
    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    // Obtener el precio aplicable basado en productId, brandId y applicationDate
    public Price getApplicablePrice(int productId, int brandId, LocalDateTime applicationDate) {
        List<Price> prices = priceRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                productId, brandId, applicationDate, applicationDate);
        return prices.isEmpty() ? null : prices.get(0);
    }
}
