package Otwos.Donggae.domain.RecruitPost.service;

import Otwos.Donggae.DTO.RecruitPost.search.SearchRequest;
import Otwos.Donggae.DTO.RecruitPost.search.SearchResponse;

public interface SearchRecruitPostService {
    SearchResponse searchRecruitPost(SearchRequest searchRequest);
    SearchResponse showRecentFourPost();
}
