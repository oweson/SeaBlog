package top.sea521.controller.Common;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/9/21 0021 16:07
 */
@Component
/**不是业务层，dao,controller模糊不清，就用componnet*/
public class ExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView mv = new ModelAndView();
        /**内部错误会来到这里*/
        String requestURI = request.getRequestURI();
        mv.addObject("url", requestURI);
        mv.addObject("data", ex.toString());
        mv.addObject("sup", "系统更新........");
        /**已经配置前缀在视图哪里，后缀不需要*/
        mv.setViewName("error");


        return mv;
    }
}
