
public class test {

	public static void main(String[] args) {
		
	        final String SEPARATEUR = " ";
	        String conte = "Blanche-Neige et les sept nains";
	 
	        String mots[] = conte.split(SEPARATEUR);
	        
	        for (int i = 0; i < mots.length; i++) {
	            System.out.println(mots[i]);
	        }
	        System.out.println(mots[0]);
	    ;

	}

}
