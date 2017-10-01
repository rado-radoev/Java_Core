package _Core_ex3_Exceptions;

public class ExceptionTester {

	public static void main(String[] args) {
		try {
			throw new ExceptionA("Exception A");
		} catch (Exception e) {
			System.out.println(e.getClass().getName());
		}
		
		try {
			throw new ExceptionB("Exception B");
		} catch (Exception e) {
			System.out.println(e.getClass().getName());
		}
		
		try {
			throw new ExceptionC("Exception C");
		}
		catch (Exception e) {
			System.out.println(e.getClass().getName());
		}
	
	}
}
