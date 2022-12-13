package eco_service.Eco.filter;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import eco_service.Eco.models.QEcoService;

import static com.querydsl.core.types.dsl.Expressions.allOf;

public class EcoServiceFilter extends AbstractFilter {

    private EcoServiceFilter(final Predicate offerPredicate) {
        super(offerPredicate);
    }

    public static EcoServiceFilterBuilder where() {
        return new EcoServiceFilterBuilder();
    }

    public EcoServiceFilter and(final EcoServiceFilter filter) {
        super.setPredicate(ExpressionUtils.allOf(this.predicate, filter.getPredicate()));
        return this;
    }

    public static class EcoServiceFilterBuilder extends AbstractFilterBuilder<EcoServiceFilter> {
        private BooleanExpression name;
        private BooleanExpression address;

        private BooleanExpression phoneNumber;
        private BooleanExpression country;
        private BooleanExpression city;


        private EcoServiceFilterBuilder() {
        }

        public EcoServiceFilterBuilder name(final String name) {
            ifNotNull(name, value -> this.name = QEcoService.ecoService.name.toLowerCase().like(Expressions.asString("%").concat(value.toLowerCase()).concat("%")));
            return this;
        }

        public EcoServiceFilterBuilder address(final String address) {
            ifNotNull(address, value -> this.address = QEcoService.ecoService.address.toLowerCase().like(Expressions.asString("%").concat(value.toLowerCase()).concat("%")));
            return this;
        }

        public EcoServiceFilterBuilder phoneNumber(final String phoneNumber) {
            ifNotNull(phoneNumber, value -> this.phoneNumber = QEcoService.ecoService.phoneNumber.toLowerCase().like(Expressions.asString("%").concat(value.toLowerCase()).concat("%")));
            return this;
        }

        public EcoServiceFilterBuilder country(final String country) {
            ifNotNull(country, value -> this.country = QEcoService.ecoService.country.toLowerCase().like(Expressions.asString("%").concat(value.toLowerCase()).concat("%")));
            return this;
        }

        public EcoServiceFilterBuilder city(final String city) {
            ifNotNull(city, value -> this.city = QEcoService.ecoService.city.toLowerCase().like(Expressions.asString("%").concat(value.toLowerCase()).concat("%")));
            return this;
        }


        @Override
        public EcoServiceFilter build() {
            final BooleanExpression expression = allOf(name,
                    address,
                    phoneNumber,
                    country,
                    city);
            return new EcoServiceFilter(expression);
        }
    }
}
