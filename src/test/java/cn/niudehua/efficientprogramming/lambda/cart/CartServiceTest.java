package cn.niudehua.efficientprogramming.lambda.cart;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;


/**
 * CartService测试
 *
 * @author deng
 * @datetime 2020/10/23 10:56 下午
 */
@Slf4j
class CartServiceTest {

    @Test
    void filterElectronicsSkus() {
        List<Sku> cartSkuList = CartService.getCartSkuList();
        // 查找购物车中数码类商品
        List<Sku> result = CartService.filterElectronicsSkus(cartSkuList);
        log.info(JSON.toJSONString(result, true));
    }

    @Test
    void filterSkusByCategory() {
        List<Sku> cartSkuList = CartService.getCartSkuList();
        List<Sku> result = CartService.filterSkusByCategory(cartSkuList, SkuCategoryEnum.BOOKS);
        log.info(JSON.toJSONString(result, true));
    }

    @Test
    void filterSkus() {
        List<Sku> cartSkuList = CartService.getCartSkuList();
        // 根据商品总价过滤 超过2000元的商品列表
        List<Sku> result = CartService.filterSkus(cartSkuList, null, 2000.00, false);
        log.info(JSON.toJSONString(result, true));
    }

    @Test
    void filterSkus2() {
        List<Sku> cartSkuList = CartService.getCartSkuList();
        // 根据商品总价过滤 超过2000元的商品列表
        List<Sku> result = CartService.filterSkus(cartSkuList, new SkuTotalPricePredicate());
        log.info(JSON.toJSONString(result, true));
    }

    @Test
    void filterSkus3() {
        List<Sku> cartSkuList = CartService.getCartSkuList();
        // 过滤商品类型为图书类型的商品列表
        List<Sku> result = CartService.filterSkus(cartSkuList, new SkuBooksCategoryPredicate());
        log.info(JSON.toJSONString(result, true));
    }

    @Test
    void filterSkus4() {
        List<Sku> cartSkuList = CartService.getCartSkuList();

        List<Sku> result = CartService.filterSkus(cartSkuList, new SkuPredicate() {
            @Override
            // 根据商品单价过滤 超过500元的商品列表
            public boolean test(Sku sku) {
                return sku.getSkuPrice() > 500.00;
            }
        });
        log.info(JSON.toJSONString(result, true));
    }

    @Test
    void filterSkus5() {
        List<Sku> cartSkuList = CartService.getCartSkuList();

        // 根据商品单价过滤 超过500元的商品列表
        List<Sku> result = CartService.filterSkus(cartSkuList, sku -> sku.getSkuPrice() > 500.00);
        log.info(JSON.toJSONString(result, true));
    }
}