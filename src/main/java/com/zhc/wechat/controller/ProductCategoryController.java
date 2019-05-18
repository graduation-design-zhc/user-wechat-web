package com.zhc.wechat.controller;

import com.user.wechat.api.dto.ProductCategoryDTO;
import com.zhc.wechat.service.ProductCategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhanghuachang
 * @date 2019-05-14
 */
@RestController
@RequestMapping("wechat")
public class ProductCategoryController {

    @Resource
    private ProductCategoryService productCategoryService;


    @GetMapping("category/getList")
    public List<ProductCategoryDTO> getProductCategoryList() {
        return productCategoryService.getAllCategoryList();
    }

}
