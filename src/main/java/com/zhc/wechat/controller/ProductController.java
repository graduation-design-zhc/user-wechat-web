package com.zhc.wechat.controller;

import com.user.wechat.api.dto.ProductDTO;
import com.zhc.wechat.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhanghuachang
 * @date 2019-05-14
 */
@RestController
@RequestMapping("wechat")
public class ProductController {

    @Resource
    private ProductService productService;

    @PostMapping("product/getByCategoryType")
    public List<ProductDTO> getProductByCategoryType(@RequestParam("categoryType") Integer categoryType) {
        return productService.getListByCategoryType(categoryType);
    }

    @GetMapping("product/getByProductId")
    public ProductDTO getProductByProductId(@RequestParam("productId") String productId) {
        return productService.findByProductId(productId);
    }
}
