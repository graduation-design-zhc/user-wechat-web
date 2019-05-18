package com.zhc.wechat.service.impl;

import com.user.wechat.api.dto.ProductDTO;
import com.user.wechat.api.response.Response;
import com.zhc.wechat.external.UserWechatClient;
import com.zhc.wechat.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhanghuachang
 * @date 2019-05-14
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private UserWechatClient userWechatClient;

    @Override
    public List<ProductDTO> getListByCategoryType(Integer categoryType) {
        Response<List<ProductDTO>> response = userWechatClient.getProductByCategoryType(categoryType);
        if (response.isSuccess()) {
            return response.getData();
        }
        return null;
    }

    @Override
    public ProductDTO findByProductId(String productId) {
        Response<ProductDTO> response = userWechatClient.getProductByProductId(productId);
        if (response.isSuccess()) {
            return response.getData();
        }
        return null;
    }
}
