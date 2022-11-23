package xpit.top.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;
import java.util.Date;

/**
 * @author Pu Tongjiao
 * @date 2022/9/27 13:13
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
public class ProductVo {
    private BigInteger id; //id

    private String name; //名称

    private String category; //分类

    private String imageLink; //图片

    private String descLink; //描述

    private Date auctionStartTime; // 竞拍开始时间

    private Date auctionEndTime; // 竞拍结束时间

    private BigInteger startPrice; // 价格

    private BigInteger status; // 存储的状态

    private BigInteger condition; //商品状态


}
