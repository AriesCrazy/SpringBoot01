package com.by.controller;

import cn.smart.core.exception.BizException;
import com.by.entity.Product;
import com.by.entity.ProductQuery;
import com.by.service.ProductService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//restfulApi
@RequestMapping("/api/product")
//@CrossOrigin(origins = "*") // 解决跨域问题
//@NoWapperX
public class ProductController {
    @Autowired //自动注入
    private ProductService productService;

    @GetMapping//查询 万能接口 ?id=1  ?ids=1,2,3 ?name=小米  ?categoryId=2
    public List<Product> select(ProductQuery query) {
        return productService.select(query);
    }


    @PostMapping  // 从http协议的请求体
    public int insert(@RequestBody Product  product) {
        if (product.getCategoryId()==0){
            BizException.throwBizException(666,"请选择商品分类");
        }
        return productService.insert(product);
    }
    @PutMapping
    public int update(@RequestBody Product  product) {
        productService.update(product);
        return  1;
    }
    @DeleteMapping //@RequestParam要求 url 上面一定要传参数 ?id=3
    public int delete(@RequestParam Integer id) {
        productService.delete(id);
        return  1;
    }

    @DeleteMapping ("/{id}")//@PathVariable从 url 路径的占位符上获取参数值，要求 占位符变量名称和我们方法的参数名称保持一致
    public int delete2(@PathVariable Integer id) {
        productService.delete(id);
        return  1;
    }
}
