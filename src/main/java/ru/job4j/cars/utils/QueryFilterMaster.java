package ru.job4j.cars.utils;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.job4j.cars.model.Ad;
import ru.job4j.cars.model.utils.FilterParams;

import java.time.LocalDateTime;

public class QueryFilterMaster implements QueryFilter<Ad> {

    private final FilterParams params;

    public QueryFilterMaster(FilterParams params) {
        this.params = params;
    }

    private String getQuery() {
        StringBuilder result = new StringBuilder();
        boolean byLastDay = params.isByLastDay();
        boolean minPrice = params.getMinPrice() > 0;
        boolean maxPrice = params.getMaxPrice() > 0;
        boolean brand = !params.getBrand().isEmpty();
        boolean model = !params.getModel().isEmpty();
        if (byLastDay || minPrice || maxPrice || brand || model) {
            result.append(" WHERE a.isSold = false");
            if (byLastDay) {
                result.append(" AND a.created >= :").append(Keys.B_L_D);
            }
            if (minPrice) {
                result.append(" AND a.price >= :").append(Keys.MIN);
            }
            if (maxPrice) {
                result.append(" AND a.price <= :").append(Keys.MAX);
            }
            if (brand) {
                result.append(" AND LOWER(v.brand) LIKE :").append(Keys.BRAND);
            }
            if (model) {
                result.append(" AND LOWER(v.model) LIKE :").append(Keys.MODEL);
            }
        }
        return result.toString();
    }

    @Override
    public Query<Ad> getQuery(Session session, String baseQuery) {
        Query<Ad> result = session.createQuery(baseQuery + " " + getQuery());
        if (params.isByLastDay()) {
            result.setParameter(Keys.B_L_D, LocalDateTime.now().minusDays(1));
        }
        if (params.getMinPrice() > 0) {
            result.setParameter(Keys.MIN, params.getMinPrice());
        }
        if (params.getMaxPrice() > 0) {
            result.setParameter(Keys.MAX, params.getMaxPrice());
        }
        if (!params.getBrand().isEmpty()) {
            result.setParameter(Keys.BRAND, params.getBrand().toLowerCase());
        }
        if (!params.getModel().isEmpty()) {
            result.setParameter(Keys.MODEL, params.getModel().toLowerCase());
        }
        return result;
    }

    private static class Keys {

        private static final String B_L_D = "bld";
        private static final String MIN = "min";
        private static final String MAX = "max";
        private static final String BRAND = "br";
        private static final String MODEL = "mod";
    }
}