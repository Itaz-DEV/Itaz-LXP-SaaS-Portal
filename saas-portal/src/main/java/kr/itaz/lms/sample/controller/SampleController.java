package kr.itaz.lms.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.itaz.lms.sample.service.SampleService;
import kr.itaz.lms.sample.vo.SampleVO;

@Controller
public class SampleController {
	
	@Autowired
	SampleService sampleService;
	
	@GetMapping("/sample")
    public String getSample(Model model) {
		List<SampleVO> sampleList = sampleService.selectSampleList();
        model.addAttribute("sampleList", sampleList);
        return "sample";
    }
}
