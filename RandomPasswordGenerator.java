import java.io.*;
import java.util.*;

public class RandomPasswordGenerator{

	public static final String CONTEST_NAME = "kz.iitu.train.2009.all";
	
	public static String getNexRandomPassword(int maxSymbols){
	
		int randInt = 0;
		String newPassword = "";
		String nextSymbol = "";
		Random rand = new Random();
		for (int j = 0 ; j < maxSymbols; j++){
			randInt = rand.nextInt(3);	

			if (randInt == 0){
				nextSymbol = String.valueOf(rand.nextInt(10));
			}
			
			if (randInt == 1){
				nextSymbol = String.valueOf((char)(rand.nextInt(26)+65));
			}
			if (randInt == 2){
				nextSymbol = String.valueOf((char)(rand.nextInt(26)+97));
			}
			newPassword+=nextSymbol;
		}
		return newPassword;
	}
	
	//STUDENTS
	public static String getStudentHeader(String mainID){
	
		String result = "";
			result +="<site" + "\n";
			result +="\tname = \"students\"" + "\n";
			result +="\tdefault-contest-id  = \"" + mainID + "\" " + "\n";
			result +="\tsession-id-prefix   = \"" + mainID + "\" " + "\n";
			result +="\treport-test-number = \"true\" " + "\n";
			result +="> " + "\n";
			result +="\t<language  " + "\n";
			result +="\t\tid = \"cpp.gnu.3.2.1\" " + "\n";
			result +="\t\tname = \"C++\" " + "\n";
			result +="\t\tsources-mask = \"*.cpp\" " + "\n"; 
			result +="\t/> " + "\n";
			result +="\t<language " + "\n";
			result +="\t\tid = \"java.sun.1.4\" " + "\n";
			result +="\t\tname = \"Sun Java\" " + "\n";
			result +="\t\tsources-mask = \"*.java\" " + "\n";
			result +="\t/>" + "\n";

		return result + "\n";
	}
	
	public static String getStudentFooter(){
	
		return "\n</site>";
	}
	
	//contest
	public static String getContestHeader(String mainID){
	
		String result = "";
			result +="<contest" + "\n";
			result +="\tid  = \"" + mainID + "\" " + "\n";
			result +="\tname = \"IITU contest 2009 for all\"" + "\n";
			result +="\n\txmlai-process = \"http://neerc.ifmo.ru/develop/pcms2/xmlai/default-rules.xml\"" + "\n";
			result +="> " + "\n";
		return result + "\n";
	}
	
	public static String getContestFooter(){
	
		return "\n</contest>";
	}
	
	//parties
	public static String getPartiesHeader(String mainID){
	
		String result = "<?xml version=\"1.0\" encoding=\"windows-1251\" ?>\n\n";
			result +="<parties" + "\n";
			result +="\tid  = \""+ mainID + "\" " + "\n";
			result +="\n\txmlai-process = \"http://neerc.ifmo.ru/develop/pcms2/xmlai/default-rules.xml\"" + "\n";
			result +="> " + "\n";
		return result + "\n";
	}
	
	public static String getPartiesFooter(){
	
		return "\n</parties>";
	}
	
	//sessions
	public static String getSessionsHeader(String mainID){
	
		String result = "";
			result +="<sessions" + "\n";
			result +="\tid  = \""+ mainID + "\" " + "\n";
			result +="\tchallenge-id  = \""+ mainID + "\" " + "\n";
			result +="\tclock-id  = \""+ mainID + "\" " + "\n";
			result +="\tparty-id  = \""+ mainID + "\" " + "\n";
			
			result +="\n\txmlai-process = \"http://neerc.ifmo.ru/develop/pcms2/xmlai/default-rules.xml\"" + "\n";
			result +="> " + "\n";
		return result + "\n";
	}
	
	public static String getSessionsFooter(){
	
		return "\n</sessions>";
	}
	
	
	public static void main(String [] args){
	
		try{
			PrintWriter xmlStudents = new PrintWriter(new File("students.xml"));
			PrintWriter xmlContest = new PrintWriter(new File("contest.xml"));
			PrintWriter xmlSessions = new PrintWriter(new File("sessions.xml"));
			PrintWriter xmlParties = new PrintWriter(new File("parties.xml"));
			
			//headers
			xmlStudents.println(getStudentHeader(CONTEST_NAME));
			xmlContest.println(getContestHeader(CONTEST_NAME));
			xmlSessions.println(getSessionsHeader(CONTEST_NAME));
			xmlParties.println(getPartiesHeader(CONTEST_NAME));
			
			String newPassword = "";
			
			String outLine ="";
			
			String number = "";
			for (int i = 0 ; i < 50 ; i++){
				if ( i < 9){
					number = "0" + String.valueOf(i+1);
				}else{
					number = String.valueOf(i+1);
				}
				outLine = "\t<login login=\"S" + number + "\" password=\"" +  getNexRandomPassword(8) + "\" session-id=\"A" + number + "\"/>";    
				xmlStudents.println(outLine);
				outLine = "\t<session-ref id = \"A" + number + "\" />";
				xmlContest.println(outLine);
				outLine = "\t<session id = \"A" + number + "\" />";
				xmlSessions.println(outLine);
				outLine = "\t<party id = \"A" + number+ "\" name = \"\"             />";
				xmlParties.println(outLine);
			}
			//footers
			xmlStudents.print(getStudentFooter());
			xmlContest.print(getContestFooter());
			xmlSessions.print(getSessionsFooter());
			xmlParties.print(getPartiesFooter());
			
			//close
			xmlStudents.close();
			xmlContest.close();
			xmlSessions.close();
			xmlParties.close();
		}catch(Exception ex){
		
			ex.printStackTrace();
		}
	}
}