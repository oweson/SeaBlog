package top.sea521.controller.Home;

import top.sea521.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;


import top.sea521.util.Functions;
/**
 * 用户的controller
 * Created by 言曌 on 2017/8/24.
 */
@Controller
public class UserController {

	@Autowired
	private  HttpServletRequest request;

	@Autowired
	private UserService userService;

	@Autowired
	private Functions functions;








}
