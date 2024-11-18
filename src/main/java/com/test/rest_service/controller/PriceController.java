package com.test.rest_service.controller;

import com.test.rest_service.model.Price;
import com.test.rest_service.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class PriceController {

    @Autowired
    private PriceService priceService;

    @GetMapping("/price")
    public Price getPrice(
            @RequestParam("applicationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,
            @RequestParam("productId") int productId,
            @RequestParam("brandId") int brandId) {
        return priceService.getApplicablePrice(productId, brandId, applicationDate);
    }
}
