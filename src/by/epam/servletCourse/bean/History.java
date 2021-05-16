package by.epam.servletCourse.bean;

public class History {
    private String orderDate;
    private String productName;
    protected String imagePath;

    public History() {
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public History(String orderDate, String productName, String imagePath) {
        this.orderDate = orderDate;
        this.productName = productName;
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "History{" +
                "orderDate='" + orderDate + '\'' +
                ", productName='" + productName + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
