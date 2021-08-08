package refactor.naver.reserve.reserveweb_refactor.service.impl;

import org.springframework.stereotype.Service;
import refactor.naver.reserve.reserveweb_refactor.dto.*;
import refactor.naver.reserve.reserveweb_refactor.entity.*;
import refactor.naver.reserve.reserveweb_refactor.mapper.CommentMapper;
import refactor.naver.reserve.reserveweb_refactor.mapper.DisplayInfoImageMapper;
import refactor.naver.reserve.reserveweb_refactor.mapper.DisplayInfoMapper;
import refactor.naver.reserve.reserveweb_refactor.mapper.ProductImageMapper;
import refactor.naver.reserve.reserveweb_refactor.repository.*;
import refactor.naver.reserve.reserveweb_refactor.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final CustomQuerydslRepository customQuerydslRepository;
    private final DisplayInfoRepository displayInfoRepository;
    private final ProductImageRepository productImageRepository;
    private final DisplayInfoImageRepository displayInfoImageRepository;
    private final CommentRepository commentRepository;
    private final DisplayInfoMapper displayInfoMapper;
    private final ProductImageMapper productImageMapper;
    private final DisplayInfoImageMapper displayInfoImageMapper;
    private final CommentMapper commentMapper;

    public ProductServiceImpl(CustomQuerydslRepository customQuerydslRepository,
                              DisplayInfoRepository displayInfoRepository,
                              ProductImageRepository productImageRepository,
                              DisplayInfoImageRepository displayInfoImageRepository,
                              CommentRepository commentRepository,
                              DisplayInfoMapper displayInfoMapper,
                              ProductImageMapper productImageMapper,
                              DisplayInfoImageMapper displayInfoImageMapper,
                              CommentMapper commentMapper) {
        this.customQuerydslRepository = customQuerydslRepository;
        this.displayInfoRepository = displayInfoRepository;
        this.displayInfoImageRepository = displayInfoImageRepository;
        this.commentRepository = commentRepository;
        this.displayInfoMapper = displayInfoMapper;
        this.productImageRepository = productImageRepository;
        this.productImageMapper = productImageMapper;
        this.displayInfoImageMapper = displayInfoImageMapper;
        this.commentMapper = commentMapper;
    }

    @Override
    public ProductResponseDto getProduct(int categoryId, MoreViewRequestDto moreViewRequestDto) throws Exception {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setItems(customQuerydslRepository.findProduct(categoryId, moreViewRequestDto));
        productResponseDto.setTotalCount(customQuerydslRepository.findProductCount(categoryId));

        return productResponseDto;
    }

    @Override
    public DisplayInfoResponseDto getProductDetail(int displayInfoId) {
        DisplayInfoResponseDto displayInfoResponseDto = new DisplayInfoResponseDto();
        DisplayInfoDto displayInfoDto = displayInfoMapper.toDto(displayInfoRepository.findDisplayInfo(displayInfoId));
        int productId = displayInfoDto.getProductId();
        List<ProductImageDto> productImageDto = productImageMapper.toDto(productImageRepository.findProductImages(productId));
        DisplayInfoImageDto displayInfoImageDto = displayInfoImageMapper.toDto(displayInfoImageRepository.findDisplayInfoImage(displayInfoId));
        List<CommentDto> comments = commentMapper.toDto(commentRepository.findComments(productId).stream().map(this::validComment).collect(Collectors.toList()));
        Double averageScore = commentRepository.findAverageScore(productId);

        displayInfoResponseDto.setDisplayInfo(displayInfoDto);
        displayInfoResponseDto.setProductImages(productImageDto);
        displayInfoResponseDto.setDisplayInfoImage(displayInfoImageDto);
        displayInfoResponseDto.setComments(comments);
        displayInfoResponseDto.setAverageScore(averageScore);

        return displayInfoResponseDto;
    }

    private Comment validComment(Comment comment) {
        if (comment.getCommentImage() == null) {
            comment.setCommentImage(new CommentImage());
        }

        return comment;
    }
}
