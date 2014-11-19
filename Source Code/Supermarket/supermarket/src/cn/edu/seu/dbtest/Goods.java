package cn.edu.seu.dbtest;

public class Goods{  
    private String name;  
    private String price ; 
    private String barcode;
    private String quantity;
    public void setBarcode(String o_barcode)
    {
    	barcode=o_barcode;
    }
    public String getBarcode()
    {
    	return barcode;
    }
    public String getName() {  
        return name;  
    }  
    public void setName(String o_name) {  
        name = o_name;  
    }  
    public String getPrice() {  
        return price;  
    }  
    public void setPrice(String o_price) {  
        price = o_price;  
    }
    public String getQuantity() {  
        return quantity;  
    }  
    public void setQuantity(String o_quantity) {  
        quantity = o_quantity;  
    } 
    public Goods(String o_barcode,String o_name, String o_price,String o_quantity) {  
    	barcode=o_barcode;
        name = o_name;  
        price = o_price;  
        quantity=o_quantity;
    }  
    public Goods(Goods another) {  
    	barcode=another.getBarcode();
        name = another.getName();  
        price = another.getPrice();  
        quantity=another.getQuantity();
    }  
} 
