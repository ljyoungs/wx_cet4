package cn.chenmuxin.wx_cet4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import cn.chenmuxin.wx_cet4.entity.Listen;
import cn.chenmuxin.wx_cet4.entity.ListenProjection;


@PreAuthorize("hasRole('USER')")
@RepositoryRestResource(excerptProjection=ListenProjection.class)
public interface ListenRepository extends PagingAndSortingRepository<Listen, String>{
	
	public List<Listen> findByAudio(@Param("audio") String audio);
	
//	@Query(value="select l from Listen l where l.audio=?1")
//	public List<Listen> (@Param("audio") String audio);
	
	
}

