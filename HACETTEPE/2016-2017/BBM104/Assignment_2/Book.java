/**
 * @param make pages,author and publisher for book
 * @author mockingbird
 */
public class Book extends Office {
	String publisher;
	String author;
	int pages;
	
	public Book(String t,int a,double b,String c,String d,String e,String f,int h) {
		super(t,a,b,c,d);
		this.publisher=e;
		this.author=f;
		this.pages=h;
	}
	/**
	 * @return publisher for book 
	 */
	public String getpublisher(){
		return publisher;
	}
	/**
	 * @return author for book 
	 */
	public String getauthor(){
		return author;
	}
	/**
	 * @return pages for book 
	 */
	public int getpages(){
		return pages;
	}
	/**
	 * @param wrote book's info 
	 */
	void ShowInfo_Book(){


		System.out.println("-----------------------");
		System.out.println("Type: " +gettyp());
		System.out.println("Item ID: "+ getID());
		System.out.println("Price: "+ getprice()+" $");
		System.out.println("Release Date: "+getrelease_data());
		System.out.println("Title: "+getcoverT());
		System.out.println("Publisher: "+getpublisher());
		System.out.println("Author: "+getauthor());
		System.out.println("Page: "+getpages()+" pages");
		
		
	}
}
