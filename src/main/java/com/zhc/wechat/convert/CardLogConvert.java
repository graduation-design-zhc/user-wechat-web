package com.zhc.wechat.convert;

import com.user.wechat.api.dto.MemberCardLogDTO;
import com.zhc.wechat.dal.response.CardLogDTO;

/**
 * @author zhanghuachang
 * @date 2019-05-01
 */
public class CardLogConvert {

    public static CardLogDTO convert(MemberCardLogDTO memberCardLogDTO) {
        CardLogDTO cardLogDTO = new CardLogDTO();
        cardLogDTO.setMemberId(memberCardLogDTO.getMemberId());
        cardLogDTO.setAddBalance(memberCardLogDTO.getAddBalance().toString());
        cardLogDTO.setOperator(memberCardLogDTO.getOperator());
        cardLogDTO.setCreateTime(memberCardLogDTO.getCreateTime());
        return cardLogDTO;
    }

}
