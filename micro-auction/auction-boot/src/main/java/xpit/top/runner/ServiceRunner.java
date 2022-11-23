package xpit.top.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import xpit.top.constant.MicroConstants;
import xpit.top.contract.EcommerceStore;
import xpit.top.mapper.ProductMapper;
import xpit.top.pojo.entity.Product;
import xpit.top.utils.DateUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;

//@Component
public class ServiceRunner implements ApplicationRunner {

    @Autowired
    private Web3j web3j;

    private ContractGasProvider gasProvider = new DefaultGasProvider();

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void run(ApplicationArguments args) {
        new Thread(() -> {
            try {
                addProduct();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void addProduct() throws CipherException, IOException {

//        Credentials credentials = WalletUtils.loadCredentials("qwer1234", SystemVariable.PRIVATE_KEY_PATH);
        Credentials credentials = Credentials.create(MicroConstants.PRIVATE_KEY);
        EcommerceStore load = EcommerceStore.load(MicroConstants.CONTRACT_ADDRESS, web3j, credentials, gasProvider);
        EthFilter filter = new EthFilter(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST, MicroConstants.CONTRACT_ADDRESS);
        load.newProductEventFlowable(filter).subscribe(event -> {
            System.out.println("event事件触发");
            Product product1 = productMapper.selectProductById(event._productId.longValue());
            if (Objects.isNull(product1)){
                Product product = new Product();
                product.setId(event._productId.longValue());
                product.setName(event._name);
                product.setCategory(event._category);
                product.setImageLink(event._imageLink);
                product.setDescLink(event._descLink);
                product.setAuctionStartTime(DateUtils.timestampToDate(event._auctionStartTime));
                product.setAuctionEndTime(DateUtils.timestampToDate(event._auctionEndTime));
                product.setStatus("0");
                product.setConditions(String.valueOf(event._productCondition));
                product.setStartPrice(new BigDecimal(event._startPrice));
                int insert = productMapper.insertProduct(product);
                System.out.println("添加到数据库----" + insert);
            }else {
                System.out.println("该条数据已经添加过");
            }

        });
    }
}
