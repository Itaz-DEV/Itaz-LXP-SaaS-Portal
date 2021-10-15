package kr.itaz.lms.sample.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.itaz.lms.sample.vo.SampleVO;

@Mapper
public interface SampleMapper {
	public List<SampleVO> selectSampleList();
}
