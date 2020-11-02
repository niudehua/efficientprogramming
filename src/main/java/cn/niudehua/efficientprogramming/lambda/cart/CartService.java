package cn.niudehua.efficientprogramming.lambda.cart;

import java.util.ArrayList;
import java.util.List;

/**
 * 购物车服务类
 *
 * @author deng
 * @datetime 2020/10/9 10:26 下午
 */
public class CartService {
    private static final List<Sku> CART_SKU_LIST = new ArrayList<Sku>() {
        {
            add(new Sku(654032, "无人机", 4999.00, 1, 4999.00, SkuCategoryEnum.ELECTRONICS));

            add(new Sku(642934, "VR一体机", 2299.00, 1, 2299.00, SkuCategoryEnum.ELECTRONICS));

            add(new Sku(645321, "纯色衬衫", 409.00, 3, 1227.00, SkuCategoryEnum.CLOTHING));

            add(new Sku(654327, "牛仔裤", 528.00, 1, 528.00, SkuCategoryEnum.CLOTHING));

            add(new Sku(675489, "跑步机", 2699.00, 1, 2699.00, SkuCategoryEnum.SPORTS));

            add(new Sku(644564, "Java编程思想", 79.80, 1, 79.80, SkuCategoryEnum.BOOKS));

            add(new Sku(678678, "Java核心技术", 149.00, 1, 149.00, SkuCategoryEnum.BOOKS));

            add(new Sku(678678, "算法", 78.20, 1, 78.20, SkuCategoryEnum.BOOKS));

            add(new Sku(696968, "TensorFlow进阶指南", 85.10, 1, 85.10, SkuCategoryEnum.BOOKS));

        }

    };

    /**
     * 获取商品信息列表
     *
     * @return 商品信息列表
     */
    public static List<Sku> getCartSkuList() {
        return CART_SKU_LIST;
    }

    /**
     * Version 1.0.0
     * 找出购物车中所有电子产品
     *
     * @param cartSkuList 购物车商品
     * @return 电子产品类商品
     */
    public static List<Sku> filterElectronicsSkus(List<Sku> cartSkuList) {
        List<Sku> result = new ArrayList<>();
        for (Sku sku : cartSkuList) {
            // 如果商品为电子产品类
            if (SkuCategoryEnum.ELECTRONICS.equals(sku.getSkuCategory())) {
                result.add(sku);
            }
        }
        return result;
    }

    /**
     * Version 2.0.0
     * 根据传入的商品类型参数，找出购物车中同种商品类型的商品列表
     *
     * @param cartSkuList  购物车商品
     * @param categoryEnum 商品类型
     * @return 同种商品类型的商品列表
     */
    public static List<Sku> filterSkusByCategory(List<Sku> cartSkuList, SkuCategoryEnum categoryEnum) {
        List<Sku> result = new ArrayList<>();
        for (Sku sku : cartSkuList) {
            if (categoryEnum.equals(sku.getSkuCategory())) {
                result.add(sku);
            }
        }
        return result;
    }

    /**
     * Version 3.0.0
     * 支持通过商品类型或总价来过滤商品
     *
     * @param cartSkuList     购物车商品
     * @param categoryEnum    商品类型
     * @param totalPrice      商品总价
     * @param categoryOrPrice 选择商品类型或价格 - true : 根据商品类型 ，false : 根据价格
     * @return 符合条件的商品列表
     */
    public static List<Sku> filterSkus(List<Sku> cartSkuList, SkuCategoryEnum categoryEnum, Double totalPrice, Boolean categoryOrPrice) {
        List<Sku> result = new ArrayList<>();

        for (Sku sku : cartSkuList) {
            // 如果根据商品类型判断，sku类型与输入类型比较
            // 如果根据商品总价判断，sku总价与输入总价比较
            boolean flag = (categoryOrPrice && categoryEnum.equals(sku.getSkuCategory())) || (!categoryOrPrice && sku.getTotalPrice() > totalPrice);
            if (flag) {
                result.add(sku);
            }

        }
        return result;

    }

    /**
     * Version 4.0.0
     * 根据不同的Sku判断标准，对Sku列表进行过滤
     *
     * @param cartSkuList  购物车商品
     * @param skuPredicate 不同的Sku判断标准策略
     * @return 符合条件的商品列表
     */
    public static List<Sku> filterSkus(List<Sku> cartSkuList, SkuPredicate skuPredicate) {
        List<Sku> result = new ArrayList<>();
        for (Sku sku : cartSkuList) {
            if (skuPredicate.test(sku)) {
                result.add(sku);
            }
        }
        return result;
    }
}
