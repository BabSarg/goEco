package eco_service.Eco.filter;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import eco_service.Eco.models.QWaste;
import eco_service.Eco.models.WasteType;

import java.util.List;
import java.util.stream.Collectors;

import static com.querydsl.core.types.dsl.Expressions.allOf;

public class WasteFilter extends AbstractFilter {

    private WasteFilter(final Predicate offerPredicate) {
        super(offerPredicate);
    }

    public static WasteFilterBuilder where() {
        return new WasteFilterBuilder();
    }

    public WasteFilter and(final WasteFilter filter) {
        super.setPredicate(ExpressionUtils.allOf(this.predicate, filter.getPredicate()));
        return this;
    }

    public static class WasteFilterBuilder extends AbstractFilterBuilder<WasteFilter> {
        private BooleanExpression type;
        private BooleanExpression longitude;
        private BooleanExpression latitude;
        private BooleanExpression country;
        private BooleanExpression city;
        private BooleanExpression street;
        private BooleanExpression ecoServiceName;
        private BooleanExpression ecoServiceAddress;
        private BooleanExpression ecoServicePhoneNumber;
        private BooleanExpression ecoServiceCountry;
        private BooleanExpression ecoServiceCity;

        private WasteFilterBuilder() {
        }

        public WasteFilterBuilder type(final List<String> types) {
            ifNotNull(types, t -> {
                List<WasteType> wasteTypes = t.stream().map(WasteType::new).collect(Collectors.toList());
                wasteTypes.forEach(type -> ifNotNull(type, value -> this.type = QWaste.waste.types.contains(type)));
            });
            return this;
        }

        public WasteFilterBuilder longitude(final Double longitude) {
            ifNotNull(longitude, value -> this.longitude = QWaste.waste.longitude.eq(value));
            return this;
        }

        public WasteFilterBuilder latitude(final Double latitude) {
            ifNotNull(latitude, value -> this.latitude = QWaste.waste.latitude.eq(value));
            return this;
        }

        public WasteFilterBuilder country(final String country) {
            ifNotNull(country, value -> this.country = QWaste.waste.wasteAddress.country.toLowerCase().like(Expressions.asString("%").concat(value.toLowerCase()).concat("%")));
            return this;
        }

        public WasteFilterBuilder city(final String city) {
            ifNotNull(city, value -> this.city = QWaste.waste.wasteAddress.city.toLowerCase().like(Expressions.asString("%").concat(value.toLowerCase()).concat("%")));
            return this;
        }

        public WasteFilterBuilder street(final String street) {
            ifNotNull(street, value -> this.street = QWaste.waste.wasteAddress.street.toLowerCase().like(Expressions.asString("%").concat(value.toLowerCase()).concat("%")));
            return this;
        }

        public WasteFilterBuilder ecoServiceName(final String ecoServiceName) {
            ifNotNull(ecoServiceName, value -> this.ecoServiceName = QWaste.waste.ecoService.name.toLowerCase().like(Expressions.asString("%").concat(value.toLowerCase()).concat("%")));
            return this;
        }

        public WasteFilterBuilder ecoServiceAddress(final String ecoServiceAddress) {
            ifNotNull(ecoServiceAddress, value -> this.ecoServiceAddress = QWaste.waste.ecoService.address.toLowerCase().like(Expressions.asString("%").concat(value.toLowerCase()).concat("%")));
            return this;
        }

        public WasteFilterBuilder ecoServicePhoneNumber(final String ecoServicePhoneNumber) {
            ifNotNull(ecoServicePhoneNumber, value -> this.ecoServicePhoneNumber = QWaste.waste.ecoService.phoneNumber.toLowerCase().like(Expressions.asString("%").concat(value.toLowerCase()).concat("%")));
            return this;
        }

        public WasteFilterBuilder ecoServiceCountry(final String ecoServiceCountry) {
            ifNotNull(ecoServiceCountry, value -> this.ecoServiceCountry = QWaste.waste.ecoService.country.toLowerCase().like(Expressions.asString("%").concat(value.toLowerCase()).concat("%")));
            return this;
        }

        public WasteFilterBuilder ecoServiceCity(final String ecoServiceCity) {
            ifNotNull(ecoServiceCity, value -> this.ecoServiceCity = QWaste.waste.ecoService.city.toLowerCase().like(Expressions.asString("%").concat(value.toLowerCase()).concat("%")));
            return this;
        }

        @Override
        public WasteFilter build() {
            final BooleanExpression expression = allOf(type,
                    longitude,
                    latitude,
                    country,
                    city,
                    street,
                    ecoServiceName,
                    ecoServiceAddress,
                    ecoServicePhoneNumber,
                    ecoServiceCountry,
                    ecoServiceCity);
            return new WasteFilter(expression);
        }
    }
}
