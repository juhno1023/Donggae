package Otwos.Donggae.domain.RecruitPost.service;

import Otwos.Donggae.DTO.RecruitPost.search.SearchRequest;
import Otwos.Donggae.DTO.RecruitPost.search.SearchResponse;

public interface SearchRecruitPostService {
    public SearchResponse searchRecruitPost(SearchRequest searchRequest);
}
