package refactor.naver.reserve.reserveweb_refactor.mapper;

public interface EntityMapper <D, E> {
    D toDto(E entity);
    E toEntity(D dto);
}
