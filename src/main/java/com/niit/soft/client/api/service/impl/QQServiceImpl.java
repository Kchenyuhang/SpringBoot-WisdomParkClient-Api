package com.niit.soft.client.api.service.impl;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.common.ResultCode;
import com.niit.soft.client.api.domain.model.LoginAccount;
import com.niit.soft.client.api.domain.model.UserAccount;
import com.niit.soft.client.api.exception.CustomException;
import com.niit.soft.client.api.repository.LoginAccountRepository;
import com.niit.soft.client.api.repository.UserAccountRepository;
import com.niit.soft.client.api.service.QQService;
import com.niit.soft.client.api.util.JwtUtil;
import com.niit.soft.client.api.util.QQHttpClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName QQServiceImpl.java
 * @Description QQ第三方登录
 * @createTime 2020年06月17日 21:24:00
 */
@Slf4j
@Service
public class QQServiceImpl implements QQService {

    @Value("${qq.oauth.http}")
    private String http;

    @Resource
    private LoginAccountRepository loginAccountRepository;
    @Resource
    private UserAccountRepository userAccountRepository;


    @Override
    public String qqRedirect(HttpSession session) {
        //QQ互联中的回调地址
        String backUrl = http + "/connect";
        //用于第三方应用防止CSRF攻击
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        session.setAttribute("state", uuid);
//        redirect uri is illegal(100010)
//        通知: QQ互联加强网站应用回调地址校验
//        回调地址此处应该改端口
//        http://www.ntt1914866205.xyz:8080
        //Step1：获取Authorization Code
        String url = "https://graph.qq.com/oauth2.0/authorize?response_type=code" +
                "&client_id=" + QQHttpClient.APPID +
                "&redirect_uri=" + URLEncoder.encode(backUrl) +
                "&state=" + uuid;
        log.info("--------重定向的地址-------");
        log.info(url);
        return url;
    }

    @Override
    public ResponseResult connect(HttpServletRequest request) throws IOException {
//    public String connect(HttpServletRequest request) throws IOException {
        //此处本想用8080端口，但因为备案时用的是域名备案，无端口号，所以回调地址只能是 http://www.ntt1914866205.xyz/connect
        HttpSession session = request.getSession();
        //qq返回的信息
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        String uuid = (String) session.getAttribute("state");
        log.info("code>>>>>>>>>>" + code);
        log.info("state>>>>>>>>>>" + code);
        log.info("uuid>>>>>>>>>>" + uuid);
        if (uuid != null) {
            if (!uuid.equals(state)) {
                throw new CustomException("QQ,state错误", ResultCode.DATA_IS_WRONG);
            }
        }

        //Step2：通过Authorization Code获取Access Token
        String backUrl = http + "/connect";
        String url = "https://graph.qq.com/oauth2.0/token?grant_type=authorization_code" +
                "&client_id=" + QQHttpClient.APPID +
                "&client_secret=" + QQHttpClient.APPKEY +
                "&code=" + code +
                "&redirect_uri=" + backUrl;

        String access_token = QQHttpClient.getAccessToken(url);
        log.info(access_token);
        //Step3: 获取回调后的 openid 值
        url = "https://graph.qq.com/oauth2.0/me?access_token=" + access_token;
        log.info("获取回调后的 openid 值>>>>>>>>" + url);
        String openid = QQHttpClient.getOpenID(url);
//        openid可以唯一标识一个用户
        //先在用户账号表里查，如果有，则返回数据

        LoginAccount loginAccount = loginAccountRepository.getLoginAccountByQqOpenIdEquals(openid);
        //如果有数据，则在用户表里查到该用户
        if (loginAccount != null) {
            UserAccount userAccount = userAccountRepository.findUserAccountByInfo(loginAccount.getJobNumber());
//            Map map = new HashedMap();
//            map.put("UserAccount",userAccount);
//            map.put("token", JwtUtil.sign(userAccount.getUserAccount(), userAccount.getPassword()));
//            log.info("生成的token{}", map.get("token"));
            String token = JwtUtil.sign(userAccount.getUserAccount(), userAccount.getPassword());
            //登录成功，重定向到首页，可以带上token，前端首页创建时，如果判断地址有token，带上token请求数据
            //重定向跳回客户端
            ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            assert sra != null;
            HttpServletResponse response = sra.getResponse();
//            此处为首页地址 先报个错，明天从这里开始改
            log.info("token{}", token);
            response.sendRedirect(http + "?token=" + token);
            //既然已经重定向了，就不用返回什么数据了
            return null;
        }
        //用户不存在，未绑定账号
        return ResponseResult.failure(ResultCode.RESULT_CODE_DATA_NONE, "该账号未绑定");

//        //Step4：获取QQ用户信息
//        url = "https://graph.qq.com/user/get_user_info?access_token=" + access_token +
//                "&oauth_consumer_key=" + QQHttpClient.APPID +
//                "&openid=" + openid;

//        JSONObject jsonObject = QQHttpClient.getUserInfo(url);
//        System.out.println("jsonObject" + jsonObject.toJSONString());
//
//        //获取用户部分信息修改头像和昵称。算了不修改了。没必要，那上面就没必要获取了。
//        session.setAttribute("openid", openid);  //openid,用来唯一标识qq用户
//        session.setAttribute("nickname", (String) jsonObject.get("nickname")); //QQ名
//        session.setAttribute("figureurl_qq_2", (String) jsonObject.get("figureurl_qq_2")); //大小为100*100像素的QQ头像URL


//        //重定向
//        return "redirect:/home";
    }
}
