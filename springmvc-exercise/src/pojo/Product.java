package pojo;

/**
 * @author: yanglh
 * @ className: Product
 * @ description: 产品类
 * @ create 2021-03-15 15:27
 **/
public class Product {
    /**
     * 产品ID
     */
    private int productId;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 产品价格
     */
    private String productPrice;

    public Product() {
    }

    public Product(int productId, String productName, String productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }
}
