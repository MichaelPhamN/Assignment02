
public class RecursionIntro {	
	public static long fibby(long n){		
		if(n == 0){
			return 1;
		}
		return fibby(n/4) + fibby(3*n/4);
	}
	public static void tablegen(long start, long end){
		System.out.println( start+" "+ fibby(start));
		start++;
		if ( start<=end){
			tablegen( start,end);
		}
	}
	
	public static void sparsetablegen(long start, long end){
		
	}
	public static void main(String[] args) { 
		//System.out.println(fibby(12));
		tablegen(10,200);
	}

}
