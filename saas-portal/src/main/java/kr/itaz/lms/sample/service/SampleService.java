package kr.itaz.lms.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.itaz.lms.sample.mapper.SampleMapper;
import kr.itaz.lms.sample.vo.SampleVO;

public interface SampleService {

	
	public List<SampleVO> selectSampleList(); 
}
