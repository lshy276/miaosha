package com.lsy.alipay;

import com.lsy.service.AlipayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 李帅豫 on 2020/5/10.
 */
@RestController
@RequestMapping("payment")
public class PaymentController {
    @Autowired
    private AlipayService alipayService;

    @RequestMapping("pay")
    public void payMent(HttpServletResponse response, HttpServletRequest request) {
        try {
            alipayService.aliPay(response,request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
