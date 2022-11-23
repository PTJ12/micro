package xpit.top.service;

import xpit.top.pojo.entity.Bid;
import xpit.top.pojo.entity.Product;
import xpit.top.pojo.vo.HighestBidderInfoVo;
import xpit.top.pojo.vo.ProductVo;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @Author PuTongjiao
 * @Date 2022/10/31 2:39
 */
public interface IContractService {
    /**
     * 添加商品
     * @param product
     * @return
     * @throws Exception
     */
    boolean addProduct(Product product) throws Exception;

    /**
     * 获取竞价次数
     * @param productId
     * @return
     * @throws Exception
     */
    BigInteger totalBids(long productId) throws Exception;

    /**
     * 结束竞拍
     * @param productId
     * @return
     * @throws Exception
     */
    boolean finalizeAuction(long productId) throws Exception;

    /**
     * 竞拍
     * @param bid
     * @return
     * @throws Exception
     */
    boolean bid(Bid bid) throws Exception;

    /**
     * 揭示报价
     * @param bid
     * @return
     * @throws Exception
     */
    boolean revealBid(Bid bid) throws Exception;

    /**
     * 获取最高竞价信息
     * @param productId
     * @return
     * @throws Exception
     */
    HighestBidderInfoVo highestBidderInfo(long productId) throws Exception;

    /**
     * 获取单个商品
     * @param productId
     * @return
     * @throws Exception
     */
    ProductVo getProduct(long productId) throws Exception;

    /**
     * 获取用户资产
     * @return
     * @throws IOException
     */
    BigDecimal getBalance() throws IOException;

    /**
     * 返回资金给买方
     * @param productId
     * @return
     * @throws Exception
     */
    boolean refundAmountToBuyer(long productId) throws Exception;

    /**
     * 释放资金给卖家
     * @param productId
     * @return
     * @throws Exception
     */
    boolean releaseAmountToSeller(long productId) throws Exception;
}
