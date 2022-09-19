package ru.job4j.cars.model.utils;

import java.util.Objects;

public class FilterParams {

    private boolean byLastDay = false;
    private double minPrice;
    private double maxPrice;
    private String minPriceAsString = "";
    private String maxPriceAsString = "";
    private String brand = "";
    private String model = "";

    public FilterParams() {
    }

    public FilterParams(boolean byLastDay, double minPrice, double maxPrice, String brand, String model) {
        this.byLastDay = byLastDay;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.brand = brand;
        this.model = model;
    }

    public boolean isByLastDay() {
        return byLastDay;
    }

    public void setByLastDay(boolean byLastDay) {
        this.byLastDay = byLastDay;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getMinPriceAsString() {
        return minPriceAsString;
    }

    public void setMinPriceAsString(String minPriceAsString) {
        this.minPriceAsString = minPriceAsString;
        minPrice = minPriceAsString.isEmpty() ? 0
                : Double.parseDouble(minPriceAsString);
    }

    public String getMaxPriceAsString() {
        return maxPriceAsString;
    }

    public void setMaxPriceAsString(String maxPriceAsString) {
        this.maxPriceAsString = maxPriceAsString;
        maxPrice = maxPriceAsString.isEmpty() ? 0
                : Double.parseDouble(maxPriceAsString);
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof FilterParams)) {
            return false;
        }
        FilterParams that = (FilterParams) o;
        return byLastDay == that.byLastDay
                && Double.compare(that.minPrice, minPrice) == 0
                && Double.compare(that.maxPrice, maxPrice) == 0
                && brand.equals(that.brand)
                && model.equals(that.model);
    }

    @Override
    public int hashCode() {
        return (((Objects.hash(byLastDay)
                * (31 + Objects.hashCode(minPrice)))
                * (31 + Objects.hashCode(maxPrice)))
                * (31 + brand.hashCode()))
                * (31 + model.hashCode());
    }

    @Override
    public String toString() {
        return "FilterParams{"
                + "byLastDay=" + byLastDay
                + ", minPrice=" + minPrice
                + ", maxPrice=" + maxPrice
                + ", brand='" + brand
                + '\'' + ", model='" + model
                + '\'' + '}';
    }
}