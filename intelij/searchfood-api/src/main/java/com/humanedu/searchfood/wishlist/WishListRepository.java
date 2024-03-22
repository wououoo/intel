package com.humanedu.searchfood.wishlist;

import com.humanedu.searchfood.utils.StringUtils;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class WishListRepository {

    // List 자바메모리 위시정보
    private List<WishListVO> wishListVOList = new ArrayList<>();

    // 위시 리스트 저장
    public WishListVO wishSave(WishListDto wishListDto) {
        WishListVO wishListVO = new WishListVO();

        wishListVO.setId(wishListVOList.size() + 1);
        wishListVO.setTitle(StringUtils.removeTags(wishListDto.getTitle()));
        wishListVO.setCategory(wishListDto.getCategory());
        wishListVO.setJibunAddress(wishListDto.getJibunAddress());
        wishListVO.setRoadAddress(wishListDto.getRoadAddress());
        wishListVO.setHomepageLink(wishListDto.getHomepageLink());
        wishListVO.setImageLink(wishListDto.getImageLink());
        wishListVO.setVisitIs(true);
        wishListVO.setVisitCount(1);
        wishListVO.setLastVisitDate(LocalDateTime.now());

        wishListVOList.add(wishListVO);

        return wishListVO;
    }

    public List<WishListVO> wishAll() {
        return wishListVOList;
    }

    public boolean wishDelete(Integer id) {
        // id에 해당하는 위시vo를 가져오려고 시도
        Optional<WishListVO> optionalWishListVO
                = wishListVOList.stream()
                .filter(wishListVO -> wishListVO.getId() == id)
                .findFirst();

        // 가져온 위시vo값으로 메모리에 있는 wishListVOList안의 값을 삭제
        return optionalWishListVO.isPresent() ? wishListVOList.remove(optionalWishListVO.get()) : false;
    }

    public boolean wishAddVisit(Integer id) {
        boolean isAddVisitSuccess = false;

        // id에 해당하는 위시vo를 가져오려고 시도
        Optional<WishListVO> optionalWishListVO
                = wishListVOList.stream()
                .filter(wishListVO -> wishListVO.getId() == id)
                .findFirst();

        if (optionalWishListVO.isPresent()) {
            WishListVO wishListVO = optionalWishListVO.get();
            wishListVO.setVisitCount(wishListVO.getVisitCount() + 1);

            isAddVisitSuccess = true;
        }

        return isAddVisitSuccess;
    }
}
