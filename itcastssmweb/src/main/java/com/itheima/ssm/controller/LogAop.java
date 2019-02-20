package com.itheima.ssm.controller;

import com.itcast.domain.SysLog;
import com.itcast.domain.UserInfo;
import com.itheima.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.omg.IOP.ServiceContextHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    ISysLogService iSysLogService;

    private Date visitTime;//开始时间
    private Class clazz;//访问的类
    private Method method;//访问的方法

    //前置通知
    @Before("execution(* com.itheima.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {

        visitTime=new Date();//当前时间就是访问时间
        clazz=jp.getTarget().getClass();//具体要访问的类
        String methodNmae = jp.getSignature().getName();//获取访问方法的名称
        Object[] args = jp.getArgs();//获取方法的方法的参数

        //获取具体执行的方法的method对象
        if (args==null||args.length==0){
            method=clazz.getMethod(methodNmae);//只能获取无参的方法
        }else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i]=args[i].getClass();
            }
           method= clazz.getMethod(methodNmae,classArgs);
        }

    }

    //后通知
    @After("execution(* com.itheima.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp){
        String url="";
        long time=new Date().getTime()-visitTime.getTime();//获取访问的时长

        if (clazz!=null&&method!=null&&clazz!=LogAop.class){

            //获取类上的路径
           RequestMapping  clazzAnnotation =(RequestMapping) clazz.getAnnotation(RequestMapping.class);
           if (clazzAnnotation!=null){
               String[] classValue = clazzAnnotation.value();

               //获取方法上的路径
               RequestMapping methodAnntation = method.getAnnotation(RequestMapping.class);
               if (methodAnntation!=null){
                   String[] methodvalue = methodAnntation.value();
                   url=classValue[0]+methodvalue[0];
               }

           }
        }

        //获取访问的ip
        String ip=request.getRemoteAddr();

        //获取当前的用户
        SecurityContext context = SecurityContextHolder.getContext();//从上下文中获取当前登陆的用户
        User user = (User) context.getAuthentication().getPrincipal();
        String username = user.getUsername();

        //将日志相关封装到syslog

        SysLog sysLog = new SysLog();
        sysLog.setExecutionTime(time);//执行时长
        sysLog.setIp(ip);
        sysLog.setMethod("[类名]"+clazz.getName()+"[方法名]"+method.getName());
        sysLog.setUrl(url);
        sysLog.setUsername(username);
        sysLog.setVisitTime(visitTime);

        iSysLogService.save(sysLog);
    }
}
