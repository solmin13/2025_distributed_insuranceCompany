public interface InsuranceProductList {

	public boolean delete(String productID);

	public boolean insert(InsuranceProduct insuranceProduct);

	public InsuranceProduct search(String productID);

	public InsuranceProductList searchProducts(String key,String value);

	public boolean update(InsuranceProduct insuranceProduct);

	public String generateProductID();

	public boolean checkProduct(String productName);

	public int size();

	public InsuranceProduct getProduct(int index);

	public void printAllProducts();
}