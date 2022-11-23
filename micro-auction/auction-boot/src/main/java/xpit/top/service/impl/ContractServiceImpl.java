package xpit.top.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple10;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;
import xpit.top.constant.MicroConstants;
import xpit.top.contract.EcommerceStore;
import xpit.top.pojo.entity.Bid;
import xpit.top.pojo.entity.Product;
import xpit.top.pojo.vo.HighestBidderInfoVo;
import xpit.top.pojo.vo.ProductVo;
import xpit.top.service.IContractService;
import xpit.top.utils.DateUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @Author PuTongjiao
 * @Date 2022/10/31 2:37
 */
@Service
@Slf4j
public class ContractServiceImpl implements IContractService {

    @Autowired
    private Web3j web3j;

    private ContractGasProvider gasProvider = new DefaultGasProvider();

    public EcommerceStore ecommerceStore(){
        //TODO 使用登录用户的私钥
//        String privateKey = SecurityUtils.getPrivateKey();
        String privateKey = "";
        Credentials credentials = Credentials.create(privateKey);
        return EcommerceStore.load(MicroConstants.CONTRACT_ADDRESS, web3j, credentials, gasProvider);
    }

    /**
     * 添加商品
     * @param product
     * @return
     * @throws Exception
     */
    @Override
    public boolean addProduct(Product product) throws Exception {
        TransactionReceipt transactionReceipt = ecommerceStore().addProductToStore(product.getName(),product.getCategory(), product.getImageLink(), product.getDescLink(), DateUtils.dateToTimestamp(product.getAuctionStartTime()), DateUtils.dateToTimestamp(product.getAuctionEndTime()), BigInteger.valueOf(product.getStartPrice().longValue()), BigInteger.valueOf(Long.valueOf(product.getConditions()))).send();
        return transactionReceipt.isStatusOK();
    }

    /**
     * 获取竞拍次数
     * @param productId
     * @return
     * @throws Exception
     */
    @Override
    public BigInteger totalBids(long productId) throws Exception {
        BigInteger total = ecommerceStore().totalBids(BigInteger.valueOf(productId)).send();
        return total;
    }


    /**
     * 结束竞拍
     * @param productId
     * @return
     * @throws Exception
     */
    @Override
    public boolean finalizeAuction(long productId) throws Exception {
        TransactionReceipt transactionReceipt = ecommerceStore().finalizeAuction(BigInteger.valueOf(productId)).send();
        return transactionReceipt.isStatusOK();
    }

    /**
     * 竞拍
     * @param bid
     * @return
     * @throws Exception
     */
    @Override
    public boolean bid(Bid bid) throws Exception {
        BigInteger weiValue = BigInteger.valueOf(Long.parseLong(String.valueOf(Convert.toWei(String.valueOf(bid.getWeiValue()), Convert.Unit.ETHER))));
        log.info(String.valueOf(weiValue));
        TransactionReceipt transactionReceipt = ecommerceStore().bid(bid.getProductId(), bid.getAmount(), bid.getSecret(), weiValue).send();
        return transactionReceipt.isStatusOK();
    }

    /**
     * 揭示报价
     * @param bid
     * @return
     * @throws Exception
     */
    public boolean revealBid(Bid bid) throws Exception {
        TransactionReceipt transactionReceipt = ecommerceStore().revealBid(bid.getProductId(), bid.getAmount(), bid.getSecret()).send();
        return transactionReceipt.isStatusOK();
    }


    /**
     * 获取最高竞价信息
     * @param productId
     * @return
     * @throws Exception
     */
    @Override
    public HighestBidderInfoVo highestBidderInfo(long productId) throws Exception {
        Tuple3<String, BigInteger, BigInteger> tuple3 = ecommerceStore().highestBidderInfo(BigInteger.valueOf(productId)).send();
        HighestBidderInfoVo highestBidderInfoVo = new HighestBidderInfoVo(tuple3.component1(), tuple3.component2(), tuple3.component3());
        return highestBidderInfoVo;
    }

    /**
     * 返回资金给买方
     * @param productId
     * @return
     * @throws Exception
     */
    @Override
    public boolean refundAmountToBuyer(long productId) throws Exception {
        TransactionReceipt send = ecommerceStore().refundAmountToBuyer(BigInteger.valueOf(productId)).send();
        return send.isStatusOK();
    }

    /**
     * 释放资金给卖家
     * @param productId
     * @return
     * @throws Exception
     */
    @Override
    public boolean releaseAmountToSeller(long productId) throws Exception {
        TransactionReceipt send = ecommerceStore().releaseAmountToSeller(BigInteger.valueOf(productId)).send();
        return send.isStatusOK();
    }

    /**
     * 获取单个商品
     * @param productId
     * @return
     * @throws Exception
     */
    @Override
    public ProductVo getProduct(long productId) throws Exception {
        Tuple10<BigInteger, String, String, String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger> tuple10 = ecommerceStore().getProduct(BigInteger.valueOf(productId)).send();

        ProductVo productVo = new ProductVo(tuple10.component1(),
                tuple10.component2(),
                tuple10.component3(),
                tuple10.component4(),
                tuple10.component5(),
                DateUtils.timestampToDate(tuple10.component6()),
                DateUtils.timestampToDate(tuple10.component7()),
                tuple10.component8(),
                tuple10.component9(),
                tuple10.component10());

        return productVo;
    }
    //TODO 添加合約接口

    /**
     * 获取用户资产
     * @return
     * @throws IOException
     */
    @Override
    public BigDecimal getBalance() throws IOException {
        //TODO 添加用户地址
//        String address = SecurityUtils.getAddress();
        String address = "";
        EthGetBalance ethGetBalance = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
        BigInteger balance = ethGetBalance.getBalance();
        return Convert.fromWei(balance.toString(), Convert.Unit.ETHER);
    }
}
