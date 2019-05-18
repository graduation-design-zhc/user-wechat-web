package com.zhc.wechat.service.impl;

import com.user.wechat.api.dto.ProductCategoryDTO;
import com.user.wechat.api.response.Response;
import com.zhc.wechat.external.UserWechatClient;
import com.zhc.wechat.service.ProductCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhanghuachang
 * @date 2019-05-14
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Resource
    private UserWechatClient userWechatClient;

    @Override
    public List<ProductCategoryDTO> getAllCategoryList() {
        Response<List<ProductCategoryDTO>> response = userWechatClient.getProductCategoryList();
        if (response.isSuccess()) {
            return response.getData();
        }
        return null;
    }
}
