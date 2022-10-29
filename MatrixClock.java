import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class MatrixClock {
public static ArrayList<ArrayList<Integer[][]>> matrix;
public static ArrayList<Integer[][]> m_arl;
public static void main(String[] args) {
	Scanner scan = new Scanner(System.in);
	System.out.println("**** Implementation de l'horloge matricielle ****\n");
	System.out.print("Entrer le nombre de processus : ");
	int noOfProcesses = scan.nextInt();
	matrix = new ArrayList<>();
	for(int n=0; n<noOfProcesses; n++){
		m_arl = new ArrayList<>();
		System.out.print("Entrer le nomber d'evenements du Processus P" + (n+1) + " : ");
		int noOfEvents = scan.nextInt();
		int localTimestamp = 0;
		for(int noe=0; noe<noOfEvents; noe++){
			Integer[][] m_arr = new Integer[noOfProcesses][noOfProcesses];// m_arr = matrice d'horloge
			for(Integer[] m : m_arr){ //m= vecteur de processus
				Arrays.fill(m,0);
		                }
			m_arr[n][n] = ++localTimestamp;
			m_arl.add(m_arr);
			System.out.println(Arrays.deepToString(m_arr));

		            }
		matrix.add(m_arl);
		        }
	
	System.out.print("Entrer le nombre de relations : ");
	int noOfRelations = scan.nextInt();
	for(int rel=0; rel<noOfRelations; rel++){
		System.out.println("Relation " + (rel+1) + " : Entrer le Processus et le numero de l'evenement :-");
		System.out.print("Envoie : ");
		int ps = scan.nextInt() - 1;
		int es = scan.nextInt() - 1;
		System.out.print("Reception : ");
		int pr = scan.nextInt() - 1;
		int er = scan.nextInt() - 1;
		Integer[][] sender = matrix.get(ps).get(es); 
		
		sender[ps][pr]=sender[ps][pr]+1;
		matrix.get(ps).set(es, sender);
		Integer[][] suivant;
		
		for(int l=es+1; l<matrix.get(ps).size(); l++) {
			suivant = matrix.get(ps).get(l);
			suivant[ps][pr]=suivant[ps][pr]+1;
			matrix.get(ps).set(l, suivant);
			
		}
		

	/*
             */
		Integer Hm[][]= new Integer[noOfProcesses][noOfProcesses];
		boolean deliv= false;
		int i=1;
		if(er==0) {
			for(Integer[] m : Hm){ 
				Arrays.fill(m,0);
		                }
		}
		else {
			Hm= matrix.get(pr).get(er-1);
		}
		for(int k=0; k<noOfProcesses; k++) {
			if ((k!=pr)&&(k!=ps) ) {
				
			
		if ((sender[ps][pr]==(Hm[ps][pr]+1))&&(sender[k][pr]<=Hm[k][pr])) {
			deliv = true;}}
		}
			
		while (!deliv) {
			Hm= matrix.get(pr).get(er+i);
			i++;
			
		}
		
			
		
		for(int e=er; e<matrix.get(pr).size(); e++) {
			Integer[][] receiver = matrix.get(pr).get(e);
			for (int n = 0; n < noOfProcesses; n++) {
				if (n != pr) {
					for(int n2=0; n2<noOfProcesses; n2++){
						receiver[n][n2] = Math.max(receiver[n][n2], sender[n][n2]);
		                        }
		                    }
				else{
					for (int n2=0; n2<noOfProcesses; n2++) {
						if (n2 != pr) {
							receiver[pr][n2] = Math.max(receiver[pr][n2], sender[pr][n2]);
		                            }
	                        }
	                    }
	                }
			matrix.get(pr).set(e, receiver);
			
	            }
		}
	//}
//}
			
	        //}
	Integer[][] disp;
		System.out.println("\n L'horloge : ");
		for(int p=0; p<noOfProcesses; p++){
			for(int e=0; e<matrix.get(p).size(); e++){
				System.out.println("P" + (p+1) + "E" + (e+1) + " : ");
				disp = matrix.get(p).get(e);
				for (int i = 0; i < disp.length; i++) {
				    for (int j = 0; j < disp[i].length; j++) {
				    	if (j==0) {
				    		System.out.print("        ");
						}
				        System.out.print(disp[i][j] + " ");
				    }
				    System.out.println();
				}
	            }
	        }
	    }
	}