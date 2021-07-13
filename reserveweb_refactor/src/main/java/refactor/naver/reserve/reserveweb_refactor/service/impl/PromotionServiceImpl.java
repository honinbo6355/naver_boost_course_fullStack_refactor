package refactor.naver.reserve.reserveweb_refactor.service.impl;

import org.springframework.stereotype.Service;
import refactor.naver.reserve.reserveweb_refactor.dto.PromotionResponseDto;
import refactor.naver.reserve.reserveweb_refactor.repository.PromotionRepository;
import refactor.naver.reserve.reserveweb_refactor.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;

    public PromotionServiceImpl(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    @Override
    public PromotionResponseDto getPromotion() throws Exception {
        PromotionResponseDto promotionResponseDto = new PromotionResponseDto();
        promotionResponseDto.setItems(promotionRepository.findPromotion());

        return promotionResponseDto;
    }
}
