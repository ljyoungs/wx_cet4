package cn.chenmuxin.wx_cet4.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import cn.chenmuxin.wx_cet4.entity.Question;

public interface QuestionRepository extends PagingAndSortingRepository<Question, String>{

}
