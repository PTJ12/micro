package xpit.top.service;

import xpit.top.pojo.entity.Product;

import java.util.List;

/**
 * 商品Service接口
 * 
 * @author PuTongjiao
 * @date 2022-10-30
 */
public interface IProductService 
{
    /**
     * 查询商品
     * 
     * @param id 商品主键
     * @return 商品
     */
    public Product selectProductById(Long id);

    /**
     * 查询商品列表
     * 
     * @param product 商品
     * @return 商品集合
     */
    public List<Product> selectProductList(Product product);

    /**
     * 新增商品
     * 
     * @param product 商品
     * @return 结果
     */
    public boolean insertProduct(Product product) throws Exception;


}
