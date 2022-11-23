package xpit.top.mapper;

import org.apache.ibatis.annotations.Mapper;
import xpit.top.pojo.entity.Product;

import java.util.List;

/**
 * 商品Mapper接口
 * 
 * @author PuTongjiao
 * @date 2022-10-30
 */
@Mapper
public interface ProductMapper 
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
    public int insertProduct(Product product);

    /**
     * 修改商品
     * 
     * @param product 商品
     * @return 结果
     */
    public int updateProduct(Product product);

    /**
     * 删除商品
     * 
     * @param id 商品主键
     * @return 结果
     */
    public int deleteProductById(Long id);

    /**
     * 批量删除商品
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProductByIds(Long[] ids);
}
