package Otwos.Donggae.domain.RecruitPost.service;

import Otwos.Donggae.DTO.RecruitPost.search.SearchRequest;
import Otwos.Donggae.DTO.RecruitPost.search.SearchResponse;
import Otwos.Donggae.domain.RecruitPost.Repository.RecruitPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchRecruitPostServiceImpl implements SearchRecruitPostService{
    @Autowired
    private RecruitPostRepository recruitPostRepository;

    @Override
    public SearchResponse searchRecruitPost(SearchRequest searchRequest) {

        return null;
    }
}
