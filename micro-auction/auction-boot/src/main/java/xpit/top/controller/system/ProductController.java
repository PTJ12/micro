package xpit.top.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xpit.top.pojo.entity.Product;
import xpit.top.result.Result;
import xpit.top.service.IProductService;

import java.util.List;


/**
 * 商品Controller
 * 
 * @author PuTongjiao
 * @date 2022-10-30
 */
@RestController
@RequestMapping("/auction/product")
public class ProductController
{
    @Autowired
    private IProductService productService;

    /**
     * 查询商品列表
     */
    @GetMapping("/list")
    public Result list(Product product)
    {
        List<Product> list = productService.selectProductList(product);
        return Result.okResult(list);
    }

    /**
     * 获取商品详细信息
     */
    @GetMapping(value = "/{id}")
    public Result getInfo(@PathVariable("id") Long id)
    {
        return Result.okResult(productService.selectProductById(id));
    }

    /**
     * 新增商品
     */
    @PostMapping
    public Result add(@RequestBody Product product) throws Exception {
        return Result.okResult(productService.insertProduct(product));
    }

}
