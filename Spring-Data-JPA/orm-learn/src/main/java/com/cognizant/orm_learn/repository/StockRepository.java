package com.cognizant.orm_learn.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cognizant.orm_learn.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    // 1. Find stocks for a given code within a date range
    List<Stock> findByCodeAndDateBetween(String code, Date startDate, Date endDate);

    // 2. Find stocks for a given code with close price greater than target
    List<Stock> findByCodeAndCloseGreaterThan(String code, BigDecimal closePrice);

    // 3. Find top 3 stocks sorted by volume descending
    List<Stock> findTop3ByOrderByVolumeDesc();

    // 4. Find top 3 stocks for a given code sorted by close ascending (lowest prices)
    List<Stock> findTop3ByCodeOrderByCloseAsc(String code);
}
