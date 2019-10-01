package com.example.ssm.controller;

import com.example.ssm.domain.SysLog;
import com.example.ssm.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;

    private Date startTime; // 开始时间
    private Class clazz; // 访问的类
    private Method method; // 访问的方法


    // 前置通知 主要获取开始时间，执行的类是哪一个， 访问的是哪一个方法
    @Before("execution(* com.example.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        startTime = new Date(); // 当前时间就是开始访问的时间
        clazz = jp.getTarget().getClass(); // 具体要访问的类对象
        String methodName = jp.getSignature().getName(); // 获取访问的方法的名称
        Object[] args = jp.getArgs(); // 获取访问的方法的参数

        // 获取具体执行的method对象
        if (args == null || args.length == 0) {
            method = clazz.getMethod(methodName); // 只能获取无参数的方法
        } else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName, classArgs);
        }
    }

    // 后置通知
    @After("execution(* com.example.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        long time = new Date().getTime() - startTime.getTime(); // 获取访问时长

        String url = null;
        //获取url
        if (clazz != null && method != null && clazz != LogAop.class) {
            // 1.获取类上的@RequestMapping注解的值
            RequestMapping annotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (annotation != null) {
                String[] classValue = annotation.value();

                // 如果是查询日志操作，则不用记录
                if ("/sysLog".equals(classValue[0])) {
                    return;
                }

                // 2.获取方法上的@RequestMapping注解的值
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0] + methodValue[0];
                }
            }
        }

        // 获取访问的ip地址
        String ip = request.getRemoteAddr();

        //获取当前操作的用户
        SecurityContext context = SecurityContextHolder.getContext(); // 从上下文中获取当前登陆的用户
        User user = (User) context.getAuthentication().getPrincipal();
        String username = user.getUsername();

        // 将日志相关信息封装到SysLog对象里
        SysLog sysLog = new SysLog();
        sysLog.setExecutionTime(time);
        sysLog.setIp(ip);
        sysLog.setUrl(url);
        sysLog.setUsername(username);
        sysLog.setVisitTime(startTime);
        sysLog.setMethod("[类名]" + clazz.getName() + "[方法名]" + method.getName());

        // 调用service完成日志的记录操作
        sysLogService.save(sysLog);
    }
}
