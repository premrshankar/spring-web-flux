package com.pluralsight.springwebflux6.stockmarket.api.currencyrate;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyRate {

    private String currencyName;

    private BigDecimal rate;

}
