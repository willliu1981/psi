package idv.psi.model;

import idv.psi.service.Inventory;

public class Order {
	private static int MAX_DISPLAY_ITEM_COUNT = 10;//一個訂單 最大項目數
	private Integer orderID;//單號
	private Product[] products;//項目/產品
	private int currentItemPointer;//當前新增項目數量

	private static Integer currentOrderAutoID = 1;//目前單號

	public Order() {
		autoID();
		products = new Product[MAX_DISPLAY_ITEM_COUNT];
	}

	private void autoID() {
		this.orderID = currentOrderAutoID;
		currentOrderAutoID++;
	}

	public boolean addProductItem(Product product) {
		//庫存量
		int inventoryCount = Inventory.getProduct(product.getId())
				.getQuantity();

		if (product.getQuantity() > inventoryCount) {
			return false;
		} else {
			products[currentItemPointer] = product;
			Inventory.consume(product.getId(), product.getQuantity());

			currentItemPointer++;
		}

		return true;
	}

	public Product[] getProducts() {
		return this.products;
	}

	//取得項目數量
	public int getItemCount() {
		return this.currentItemPointer;
	}

	public Integer getOrderID() {
		return orderID;
	}

	public void setOrderID(Integer number) {
		this.orderID = number;
	}

	//取得最後一筆新增的項目
	public String getCurrentItemDisplay() {
		int lastIndex = this.getItemCount() - 1;
		return getItemDisplayWithIndex(lastIndex);
	}

	public String getItemDisplayWithIndex(int index) {
		StringBuilder sb = new StringBuilder();
		Product item = this.products[index];

		sb.append(index + 1).append(":").append(item.getName()).append(" x")
				.append(item.getQuantity());

		return sb.toString();
	}

	//取得訂單明細
	public String getOrderDisplay() {
		StringBuilder sb = new StringBuilder();
		sb.append("單號:").append(this.orderID).append("\n");

		int index = 0;
		int costTotal = 0;
		while (index < this.getItemCount()) {
			sb.append("  ").append(this.getItemDisplayWithIndex(index))
					.append("\n");

			Product item = this.products[index];
			int cost = item.getPrice() * item.getQuantity();
			costTotal += cost;
			index++;
		}
		sb.append("總計:" + costTotal);

		return sb.toString();

	}
}
