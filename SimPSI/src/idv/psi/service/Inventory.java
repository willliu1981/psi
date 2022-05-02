package idv.psi.service;

import idv.psi.model.Order;
import idv.psi.model.Product;

public class Inventory {

	private static Product[] products;

	private Inventory() {

	}

	public static void initialize(Integer maxProductsCount) {
		products = new Product[maxProductsCount];
	}

	//product id = products 的 index+1
	private static int getIndexWithProductID(int productID) {
		return productID - 1;
	}

	public static void setProduct(Product product) {
		int index = getIndexWithProductID(product.getId());
		products[index] = product;
	}

	public static Product getProduct(int productID) {
		return Inventory.products[getIndexWithProductID(productID)];
	}

	//取得原型
	public static Product getProductCopy(int productID) {
		Product invProduct = Inventory.products[getIndexWithProductID(
				productID)];
		//原型
		Product p;
		{
			p = new Product();
			p.setName(invProduct.getName());
			p.setId(productID);
			p.setPrice(invProduct.getPrice());
		}
		return p;
	}

	//消費庫存
	public static void consume(int productID, int count) {
		products[getIndexWithProductID(productID)].addQuantity(-count);
	}

	//是否有這項產品
	public static boolean isExistCategory(int productID) {
		if (productID < 1 || productID > products.length) {
			return false;
		}

		return products[getIndexWithProductID(productID)] != null;
	}

	public static String getDisplayForTitle() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < products.length; i++) {
			sb.append("[").append(i + 1).append(":")
					.append(Inventory.products[i] == null ? "尚未有項目"
							: Inventory.products[i].getName())
					.append("]");

			if (i < Inventory.products.length - 1) {
				sb.append(" ");
			}
		}

		return sb.toString();
	}

	public static String getDisplay() {
		StringBuilder sb = new StringBuilder();
		sb.append("庫存:").append("\n");
		for (int i = 0; i < products.length; i++) {
			boolean isEmpty = Inventory.products[i] == null;
			sb.append(i + 1).append(":");
			if (isEmpty) {
				sb.append("尚未有項");
			} else {
				sb.append(Inventory.products[i].getName()).append("  ")
						.append(products[i].getQuantity());
			}

			sb.append("\n");
		}

		return sb.toString();
	}

	public static int getItemCount() {
		return Inventory.products.length;
	}

	public static int getInventoryQuantity(int productID) {
		return Inventory.products[getIndexWithProductID(productID)]
				.getQuantity();
	}

	//回朔庫存
	public static void rollback(Order order) {
		Product[] productItems = order.getProducts();
		for (int i = 0; i < order.getItemCount(); i++) {
			Product productItem = productItems[i];
			Inventory.products[getIndexWithProductID(productItem.getId())]
					.addQuantity(productItem.getQuantity());
		}

	}

}
