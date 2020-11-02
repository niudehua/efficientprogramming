package cn.niudehua.efficientprogramming.lambda.cart;

/**
 * Sku选择谓词接口
 *
 * @author deng
 * @datetime 2020/10/23 11:30 下午
 */
public interface SkuPredicate {

    /**
     * 选择判断标准
     *
     * @param sku
     * @return
     */
    boolean test(Sku sku);
}
