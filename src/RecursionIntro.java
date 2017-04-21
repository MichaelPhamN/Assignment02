/**
 * 	Assignment 02
 *  Phuc Pham N
 *  9:30 - 10:30
 */
public class RecursionIntro {
	/**
	 *  Create function fibby
	 */
	public static long fibby(long n){		
		if(n == 0){
			return 1;
		}
		return fibby(n/4) + fibby(3*n/4);
	}
	
	/**
	 * Create function tablegen
	 * @param start
	 * @param end
	 */
	public static void tablegen(long start, long end){		
		System.out.println( start + " " + fibby(start));
		
		start = start + 1;
		
		if(start<=end){
			tablegen(start,end);
		}
	}
	
	/**
	 * Create function sparsetablegen
	 * @param start
	 * @param end
	 */
	
	public static void sparsetablegen(long start, long end){
		sparsetablegen(start, end, start);
	}
	
	/**
	 * Create function sparsetablegen
	 * @param start
	 * @param end
	 * @param first
	 */
	
	public static void sparsetablegen(long start, long end, long first){
		if(start < end){
			if(fibby(start) != fibby(start + 1)){
				System.out.println(first+" "+ fibby(first));				
					sparsetablegen(start + 1, end, start + 1);				
			}else{				
					sparsetablegen(start + 1, end, first);				
			}
		}		
		
		if(start == end){
			System.out.println(first + " " + fibby(first));
		}
	}	

	public static void main(String[] args) { 
		//System.out.println(fibby(12));
//		tablegen(80,100);
//		System.out.println(fibby(1));
		sparsetablegen(80,100);
	}

}
