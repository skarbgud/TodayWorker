package com.workerholic.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.workerholic.vo.ResultVO;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("login/")
public class LoginController {

	private static final Logger LOG = LoggerFactory.getLogger(BoardController.class);

	@RequestMapping(value = "naver-login.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ResultVO naverLogin(@RequestBody Map<String, Object> obj) {
		LOG.info("naverLogin");
		ResultVO result = new ResultVO(false, null);

		try {
			result.setSuccess(true);
		} catch (Exception e) {
			LOG.error("[Login] naverLogin : " + e.getMessage(), e);
		}

		return result;
	}

}
