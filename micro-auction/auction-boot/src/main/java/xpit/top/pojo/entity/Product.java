package xpit.top.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xpit.top.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品对象 product
 * 
 * @author PuTongjiao
 * @date 2022-10-30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity
{
    /** id */
    private Long id;

    /** 名称 */
    private String name;

    /** 分类 */
    private String category;

    /** 图片链接 */
    private String imageLink;

    /** 描述 */
    private String descLink;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date auctionStartTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date auctionEndTime;

    /** 初始价格 */
    private BigDecimal startPrice;

    /** 商品状态 */
    private String conditions;

    /** 存储的状态 */
    private String status;

}
