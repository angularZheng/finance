package com.zhengbing.controller;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import com.zhengbing.entity.Order;
import com.zhengbing.entity.Product;
import com.zhengbing.entity.User;
import com.zhengbing.service.IOrderService;
import com.zhengbing.service.IProductService;
import com.zhengbing.util.Constants;
import com.zhengbing.util.StringUtil;
import com.zhengbing.util.web.ParameterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by zhengbing on 2017/12/3.
 */
@Controller
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IProductService productService;

    @RequestMapping(value = "order/{productId}")
    public String order(@PathVariable Integer productId, HttpServletRequest request, Model model){

        HttpSession session = request.getSession();
        User user  = (User)session.getAttribute( "user" );
        Product product = productService.findById(productId);
        // 生成应用内订单
        Order order = new Order();
        order.setUserId( user.getId());
        order.setProductId(product.getId());
        order.setProduct( product );
        order.setOrderNo( StringUtil.ganerateOrderNo() );
        order.setAmount( product.getPrice());
        order.setDescription( "");
        order.setStatus( Constants.ORDER_STATUS_INIT );
        order.setCreateBy(user.getNickname());
        order .setCreateTime( new Date(  ) );

        order = orderService.save( order );
        model.addAttribute("order",order);
        return "order_confirm";
    }
}
