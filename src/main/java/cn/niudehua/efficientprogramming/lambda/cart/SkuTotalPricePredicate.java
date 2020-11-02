package cn.niudehua.efficientprogramming.lambda.cart;

/**
 * 对Sku的商品总价是否超出2000的判断标准
 *
 * @author deng
 * @datetime 2020/10/23 11:37 下午
 */
public class SkuTotalPricePredicate implements SkuPredicate {
    @Override
    public boolean test(Sku sku) {
        return sku.getTotalPrice() > 2000.00;
    }
}
