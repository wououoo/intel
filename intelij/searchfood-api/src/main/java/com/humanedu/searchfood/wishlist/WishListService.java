package com.humanedu.searchfood.wishlist;

import com.humanedu.searchfood.naver.NaverAPIClient;
import com.humanedu.searchfood.naver.dto.SearchImageRequestDto;
import com.humanedu.searchfood.naver.dto.SearchImageResponseDto;
import com.humanedu.searchfood.naver.dto.SearchRegionRequestDto;
import com.humanedu.searchfood.naver.dto.SearchRegionResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListService {
    @Autowired
    private NaverAPIClient naverAPIClient;

    @Autowired
    private WishListRepository wishListRepository;

    public WishListDto search(String paramQuery) {
        WishListDto wishListDto = new WishListDto();

        // 1. NaverAPI 지역검색 호출해서 dto 값 매핑
        SearchRegionRequestDto searchRegionRequestDto = new SearchRegionRequestDto();
        searchRegionRequestDto.setQuery(paramQuery);

        SearchRegionResponseDto searchRegionResponseDto
                = naverAPIClient.searchRegion(searchRegionRequestDto);
        List<SearchRegionResponseDto.SearchRegionItem> searchRegionItemList
                = searchRegionResponseDto.getItems();
        if(searchRegionItemList != null && searchRegionItemList.size() > 0) {
            SearchRegionResponseDto.SearchRegionItem searchRegionItem = searchRegionItemList.get(0);

            wishListDto.setTitle(searchRegionItem.getTitle());
            wishListDto.setCategory(searchRegionItem.getCategory());
            wishListDto.setJibunAddress(searchRegionItem.getAddress());
            wishListDto.setRoadAddress(searchRegionItem.getRoadAddress());
            wishListDto.setHomepageLink(searchRegionItem.getLink());
        }

        // 2. NaverAPI 이미지검색 호출해서 dto값 매핑
        SearchImageRequestDto searchImageRequestDto = new SearchImageRequestDto();
        searchImageRequestDto.setQuery(paramQuery);

        SearchImageResponseDto searchImageResponseDto
                = naverAPIClient.searchImage(searchImageRequestDto);
        List<SearchImageResponseDto.SearchImageItem> searchImageItemList
                = searchImageResponseDto.getItems();
        if (searchImageItemList != null && searchImageItemList.size() > 0) {
            SearchImageResponseDto.SearchImageItem searchImageItem = searchImageItemList.get(0);

            wishListDto.setImageLink(searchImageItem.getLink());
        }

        return wishListDto;
    }

    public WishListVO addWish(WishListDto wishListDto) {
        // Storage(DB, Memory etc)에 위시정보 저장
        return wishListRepository.wishSave(wishListDto);
    }

    public List<WishListVO> allWish() {
        List<WishListVO> wishListVOList = wishListRepository.wishAll();
        return wishListVOList;
    }

    public boolean deleteWish(Integer id) {
        return wishListRepository.wishDelete(id);
    }

    public boolean addVisitWish(Integer id) {
        return wishListRepository.wishAddVisit(id);
    }
}
