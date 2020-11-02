package cn.niudehua.efficientprogramming.lambda.cart;

/**
 * 对Sku的商品类型为图书类的判断标准
 * @author deng
 * @datetime 2020/10/23 11:37 下午
 */
public class SkuBooksCategoryPredicate implements SkuPredicate {
    @Override
    public boolean test(Sku sku) {
        return SkuCategoryEnum.BOOKS.equals(sku.getSkuCategory());
    }
}
