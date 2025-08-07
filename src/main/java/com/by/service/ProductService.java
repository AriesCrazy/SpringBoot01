package com.by.service;

import com.by.dao.ProductDao;
import com.by.entity.Product;
import com.by.entity.ProductQuery;
import com.by.enums.ProductStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //组件
public class ProductService {

    @Autowired
    private ProductDao productDao;

    //缺点:
    //1.所有方法返回的类型都是PageInfo<Product>
    //2.返回信息字段太多
    public List<Product> select(ProductQuery query) {
        List<Product> products = productDao.select(query);
        return products;
    }

    public int insert(Product product) {
        //因为用Mybatis拦截器实现了lastUpdateBy的自动注入，所以这里不需要手动设置
        /*UserSession userSession = UserContext.get();
        product.setLastUpdateBy(userSession.getNickName());*/
        return productDao.insert(product);
    }

    public int update(Product product) {
        /*UserSession userSession = UserContext.get();
        product.setLastUpdateBy(userSession.getNickName());*/
        return productDao.update(product);
    }

    public int delete(Integer id) {
        return productDao.updateStatus(id, ProductStatus.DELETE.getCode());
    }
}
