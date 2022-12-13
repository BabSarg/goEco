package eco_service.Eco.filter;


import com.querydsl.core.types.Predicate;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.function.Consumer;

public abstract class AbstractFilter {

    protected Predicate predicate;

    protected AbstractFilter(final Predicate predicate) {
        this.predicate = predicate;
    }

    public Predicate getPredicate() {
        return predicate;
    }

    protected void setPredicate(final Predicate predicate) {
        this.predicate = predicate;
    }

    public static abstract class AbstractFilterBuilder<T> {

        public abstract T build();

        protected <S> void ifNotNull(final S value,
                                     final Consumer<S> consumer) {
            if (value != null) {
                consumer.accept(value);
            }
        }

        protected <S> void ifNotEmpty(final Collection<S> collection,
                                      final Consumer<S> onlyOneElementConsumer,
                                      final Consumer<Collection<S>> moreThanOneElementConsumer) {
            if (CollectionUtils.isNotEmpty(collection)) {
                if (collection.size() == 1) {
                    onlyOneElementConsumer.accept(collection.stream().findFirst().get());
                } else {
                    moreThanOneElementConsumer.accept(collection);
                }
            }
        }

        protected <S> void ifNotEmpty(final Collection<S> collection,
                                      final Consumer<Collection<S>> consumer) {
            if (CollectionUtils.isNotEmpty(collection)) {
                consumer.accept(collection);
            }
        }
    }
}

