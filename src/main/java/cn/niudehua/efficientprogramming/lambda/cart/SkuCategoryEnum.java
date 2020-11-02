package cn.niudehua.efficientprogramming.lambda.cart;

/**
 * 商品类型枚举
 *
 * @author: deng
 * @datetime: 2020/10/9 10:30 下午
 */
public enum SkuCategoryEnum {
    /**
     * 服装类
     */
    CLOTHING(10, "服装类"),
    /**
     * 数码类
     */
    ELECTRONICS(20, "数码类"),
    /**
     * 运动类
     */
    SPORTS(30, "运动类"),
    /**
     * 图书类
     */
    BOOKS(40, "图书类");
    /**
     * 商品类型的编号
     */
    private Integer code;
    /**
     * 商品类型的名称
     */
    private String name;

    /**
     * 构造函数
     *
     * @param code 商品类型的编号
     * @param name 商品类型的名称
     */
    SkuCategoryEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
