package cn.chenmuxin.wx_cet4.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import cn.chenmuxin.wx_cet4.entity.Word;

public interface WordRepository extends PagingAndSortingRepository<Word, String>{

}