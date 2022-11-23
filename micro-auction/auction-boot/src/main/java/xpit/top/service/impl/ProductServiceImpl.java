package xpit.top.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xpit.top.mapper.ProductMapper;
import xpit.top.pojo.entity.Product;
import xpit.top.service.IContractService;
import xpit.top.service.IProductService;

import java.util.List;

/**
 * 商品Service业务层处理
 * 
 * @author PuTongjiao
 * @date 2022-10-30
 */
@Service
public class ProductServiceImpl implements IProductService
{
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private IContractService contractService;


    /**
     * 查询商品
     * 
     * @param id 商品主键
     * @return 商品
     */
    @Override
    public Product selectProductById(Long id)
    {
        return productMapper.selectProductById(id);
    }

    /**
     * 查询商品列表
     * 
     * @param product 商品
     * @return 商品
     */
    @Override
    public List<Product> selectProductList(Product product)
    {
        return productMapper.selectProductList(product);
    }

    /**
     * 新增商品
     * 
     * @param product 商品
     * @return 结果
     */
    @Override
    public boolean insertProduct(Product product) throws Exception {
        return contractService.addProduct(product);
    }

}
