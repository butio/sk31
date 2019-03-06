package inventoryControl;

public class inventoryControlDB {
	private String vendingId;
	private String place;
	private String count;
	private String productId;
	private String product;
	private int stock;
	private int price;
	private String state;
	private String receiptDate;

	public inventoryControlDB(){
	}



public void setVendingId(String _vendingId){
	this.vendingId = _vendingId;
}
public String getVendigId(){
	return this.vendingId;
}
public void setPlace(String _place){
	this.place = _place;
}
public String getPlace(){
	return this.place;
}
public void setCount(String _count){
	this.count = _count;
}
public String getCount(){
	return this.count;
}
public void setProductId(String _productId){
	this.productId = _productId;
}
public String getProductId(){
	return this.productId;
}
public void setProduct(String _product){
	this.product = _product;
}
public String getProduct(){
	return this.product;
}
public void setStock(int _stock){
	this.stock = _stock;
}
public int getStock(){
	return this.stock;
}
public void setPrice(int _price){
	this.price = _price;
}
public int getPrice(){
	return this.price;
}
public void setState(String _state){
	this.state = _state;
}
public String getState(){
	return this.state;
}
public void setReceiptDate(String _receiptDate){
	this.receiptDate = _receiptDate;
}
public String getReceiptDate(){
	return this.receiptDate;
}

}
