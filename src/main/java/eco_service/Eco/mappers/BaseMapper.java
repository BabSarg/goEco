package eco_service.Eco.mappers;

import java.util.List;

public interface BaseMapper<E, D> {

    D toDTO(E e);

    List<D> toDTO(List<E> e);

    E toEntity(D d);

    List<E> toEntity(List<D> d);
}
