package org.zerock.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TimeMapper {

	@Select("SELECT sysdate from dual")
	public String getTime();
	public String getTime2();
}
