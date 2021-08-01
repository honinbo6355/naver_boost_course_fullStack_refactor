package refactor.naver.reserve.reserveweb_refactor.mapper;

import java.util.List;

public interface EntityMapper <D, E> {
    D toDto(E entity);
    E toEntity(D dto);

    List<D> toDto(List<E> entityList);
    List<E> toEntity(List<D> dtoList);
}
