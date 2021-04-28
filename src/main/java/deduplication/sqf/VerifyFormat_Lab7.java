/*
 * From instructions:
 * (per input file)
 *  - Records given:2000
 *  - Attributes checked:X,Y,Z      <- needs to be exact as given in the first row of CSV file
 *  - Duplicates found:100 
 *  
 *  (Probe Hash Map class) NOTE THAT THIS FILE DOES NOT CHECK THIS OUTPUT
 *   - Average number of probes: XXX
 *   - Max number of probes: XXX
 *   - Load factor: XXX
 */
package deduplication.sqf;

import java.io.PrintStream;
import java.io.ByteArrayOutputStream;;

public class VerifyFormat_Lab7 {
	
	public static void main(String[] args)
	{
		String[] mainInput = {"vote_files/SWVF_1_22_short.txt"};
		String studentOut = getOutput(mainInput);
		
		System.out.println("\n*********************YOUR OUTPUT START*********************\n\n" + studentOut + "\n*********************YOUR OUTPUT END*********************\n\n");
		System.out.println("\n=====================FORMAT CHECKER MESSAGES START=====================\n");
		
		// check for presence of "Records given:"
		System.out.println("\n------------checking for the presence of \"Records given:\", \"Attributes checked:\", and \"Duplicates found:\"------------");
		if(!checkLineColonPresences(studentOut, new String[] {"Records given", "Attributes checked", "Duplicates found"}))
			System.out.println("Some additional testing cound not be done.\nPlease fix the above errors and try again.");
		else
		{
			System.out.println("Overall:\tThey all seem present!\n");
			
			// declar variables for specific lines
			String recordLine = studentOut.substring(studentOut.indexOf("Records given:")).split("\n")[0];
			String attrLine = studentOut.substring(studentOut.indexOf("Attributes checked:")).split("\n")[0];
			String duplLine = studentOut.substring(studentOut.indexOf("Duplicates found:")).split("\n")[0];
			
			// check for order
			System.out.println("\n------------checking for the order of \"Records given:\", \"Attributes checked:\", and \"Duplicates found:\"------------");
			if(!checkOrder(studentOut, new String[] {"Records given:", "Attributes checked:", "Duplicates found:"}))
				System.out.println("\"Records given:\", \"Attributes checked:\", and \"Duplicates found:\" do not seem to be in order.\nPlease make sure they are in order and try again.");
			else
				System.out.println("Overall:\tThe order seems correct!");
			
			System.out.println("\n\n------------checking the \"Records given:\" line------------");
			if(checkForNoSpaces(recordLine.substring(recordLine.indexOf("given"))))
			{ // no spaces, check the number
				boolean tryAgain = true;
				String afterColon = recordLine.substring(1 + recordLine.indexOf(":"));
				try {
		        	Integer.parseInt(afterColon);
		        	System.out.println("\t\"" + afterColon + "\" seems to be an integer!");
		        	tryAgain = false;
		        } catch (NumberFormatException nfe) {System.out.println("\t\"" + afterColon + "\" does not seem to be a number.");}
				if(!tryAgain)
					System.out.println("Overall:\t\"Records given:\" lines seems good!");
				else
					System.out.println("Overall:\t\"" + afterColon + "\" in the \"Records given:\" line does not seem to be a number.\nPlease make appropriate changes and try again.");
			}
			else // spaces are present
				System.out.println("The extra spaces do not allow some checking to be done.\nPlease make appropriate changes and try again.");
						
			System.out.println("\n\n------------checking the \"Attributes checked:\" line------------");
			//String[] options = new String[] {"year", "pct", "ser_num", "datestop", "timestop", "recstat", "inout", "trhsloc", "perobs", "crimsusp", "perstop", "typeofid", "explnstp", "othpers", "arstmade", "arstoffn", "sumissue", "sumoffen", "compyear", "comppct", "offunif", "officrid", "frisked", "searched", "contrabn", "adtlrept", "pistol", "riflshot", "asltweap", "knifcuti", "machgun", "othrweap", "pf_hands", "pf_wall", "pf_grnd", "pf_drwep", "pf_ptwep", "pf_baton", "pf_hcuff", "pf_pepsp", "pf_other", "radio", "ac_rept", "ac_inves", "rf_vcrim", "rf_othsw", "ac_proxm", "rf_attir", "cs_objcs", "cs_descr", "cs_casng", "cs_lkout", "rf_vcact", "cs_cloth", "cs_drgtr", "ac_evasv", "ac_assoc", "cs_furtv", "rf_rfcmp", "ac_cgdir", "rf_verbl", "cs_vcrim", "cs_bulge", "cs_other", "ac_incid", "ac_time", "rf_knowl", "ac_stsnd", "ac_other", "sb_hdobj", "sb_outln", "sb_admis", "sb_other	", "repcmd", "revcmd", "rf_furt", "rf_bulg", "offverb", "offshld", "forceuse", "sex", "race", "dob", "age", "ht_feet", "ht_inch", "weight", "haircolr", "eyecolor", "build", "othfeatr", "addrtyp", "rescode", "premtype", "premname", "addrnum", "stname", "stinter", "crossst", "aptnum", "city", "state", "zip", "addrpct", "sector", "beat", "post", "xcoord", "ycoord", "dettypcm", "linecm", "detailcm"};
			String[] options = new String[] {"SOS_VOTERID","COUNTY_NUMBER","COUNTY_ID","LAST_NAME","FIRST_NAME","MIDDLE_NAME","SUFFIX","DATE_OF_BIRTH","REGISTRATION_DATE","VOTER_STATUS","PARTY_AFFILIATION","RESIDENTIAL_ADDRESS1","RESIDENTIAL_SECONDARY_ADDR","RESIDENTIAL_CITY","RESIDENTIAL_STATE","RESIDENTIAL_ZIP","RESIDENTIAL_ZIP_PLUS4","RESIDENTIAL_COUNTRY","RESIDENTIAL_POSTALCODE","MAILING_ADDRESS1","MAILING_SECONDARY_ADDRESS","MAILING_CITY","MAILING_STATE","MAILING_ZIP","MAILING_ZIP_PLUS4","MAILING_COUNTRY","MAILING_POSTAL_CODE","CAREER_CENTER","CITY","CITY_SCHOOL_DISTRICT","COUNTY_COURT_DISTRICT","CONGRESSIONAL_DISTRICT","COURT_OF_APPEALS","EDU_SERVICE_CENTER_DISTRICT","EXEMPTED_VILL_SCHOOL_DISTRICT","LIBRARY","LOCAL_SCHOOL_DISTRICT","MUNICIPAL_COURT_DISTRICT","PRECINCT_NAME","PRECINCT_CODE","STATE_BOARD_OF_EDUCATION","STATE_REPRESENTATIVE_DISTRICT","STATE_SENATE_DISTRICT","TOWNSHIP","VILLAGE","WARD","PRIMARY-03/07/2000","GENERAL-11/07/2000","SPECIAL-05/08/2001","GENERAL-11/06/2001","PRIMARY-05/07/2002","GENERAL-11/05/2002","SPECIAL-05/06/2003","GENERAL-11/04/2003","PRIMARY-03/02/2004","GENERAL-11/02/2004","SPECIAL-02/08/2005","PRIMARY-05/03/2005","PRIMARY-09/13/2005","GENERAL-11/08/2005","SPECIAL-02/07/2006","PRIMARY-05/02/2006","GENERAL-11/07/2006","PRIMARY-05/08/2007","PRIMARY-09/11/2007","GENERAL-11/06/2007","PRIMARY-11/06/2007","GENERAL-12/11/2007","PRIMARY-03/04/2008","PRIMARY-10/14/2008","GENERAL-11/04/2008","GENERAL-11/18/2008","PRIMARY-05/05/2009","PRIMARY-09/08/2009","PRIMARY-09/15/2009","PRIMARY-09/29/2009","GENERAL-11/03/2009","PRIMARY-05/04/2010","PRIMARY-07/13/2010","PRIMARY-09/07/2010","GENERAL-11/02/2010","PRIMARY-05/03/2011","PRIMARY-09/13/2011","GENERAL-11/08/2011","PRIMARY-03/06/2012","GENERAL-11/06/2012","PRIMARY-05/07/2013","PRIMARY-09/10/2013","PRIMARY-10/01/2013","GENERAL-11/05/2013","PRIMARY-05/06/2014","GENERAL-11/04/2014","PRIMARY-05/05/2015","PRIMARY-09/15/2015","GENERAL-11/03/2015","PRIMARY-03/15/2016","GENERAL-06/07/2016","PRIMARY-09/13/2016","GENERAL-11/08/2016","PRIMARY-05/02/2017","PRIMARY-09/12/2017","GENERAL-11/07/2017","PRIMARY-05/08/2018","GENERAL-08/07/2018","GENERAL-11/06/2018","PRIMARY-05/07/2019","PRIMARY-09/10/2019","GENERAL-11/05/2019","PRIMARY-03/17/2020"};
			
			System.out.println("Checking your attribute line which seems to be: \"" + attrLine + "\"");

			// check for presence of spaces
			if(checkForNoSpaces(attrLine.substring(attrLine.indexOf("checked"))))
			{
				String[] attrs = attrLine.substring(1 + attrLine.indexOf(":")).split(",");
				for(String attribute : attrs)
				{
					System.out.println("\n\tLooking at attribute: \"" + attribute + "\"");
					boolean foundCSVformat = false;
					for(String option: options)
					{
						if(attribute.equals(option))
							foundCSVformat = true;
					}
					if(foundCSVformat)
						System.out.println("\t\tThe attribute \"" + attribute + "\" seems like a valid category and named as in the csv!");
					else
						System.out.println("\t\tThe attribute \"" + attribute + "\" does not seem to be a valid category.\n\t\tPlease ensure the attributes are named exactly the same as the first row of the CSV.");
				}
			}
			else
				System.out.println("The extra spaces do not allow some attributes to be checked.\nPlease make appropriate changes and try again.");
			
			System.out.println("\n\n------------checking the \"Duplicataes found:\" line------------");
			if(checkForNoSpaces(duplLine.substring(duplLine.indexOf("found"))))
			{ // no spaces, check the number
				boolean tryAgain = true;
				String afterColon = duplLine.substring(1 + duplLine.indexOf(":"));
				try {
		        	Integer.parseInt(afterColon);
		        	System.out.println("\t\"" + afterColon + "\" seems to be an integer!");
		        	tryAgain = false;
		        } catch (NumberFormatException nfe) {System.out.println("\t\"" + afterColon + "\" does not seem to be a number.");}
				if(!tryAgain)
					System.out.println("Overall:\t\"Duplicates checked:\" line seems good!");
				else
					System.out.println("Overall:\t\"" + afterColon + "\" in the \"Duplicates checked:\" line does not seem to be a number.\nPlease make appropriate changes and try again.");
			}
			else // spaces are present
				System.out.println("The extra spaces do not allow some checking to be done.\nPlease make appropriate changes and try again.");
		}
		System.out.println("\n=====================FORMAT CHECKER MESSAGES END=====================\n");
	}
	
	// returns true if the given substring has a space in it, false otherwise
	public static boolean checkForNoSpaces(String subStr)
	{
		boolean noSpaces = false;
		if(subStr.indexOf(" ") == -1)
			noSpaces = true;
		else
			System.out.println("\tThere seems to be space character(s) that should not be present in the line.\nPlease make appropriate changes and run the format checker again.");
		return noSpaces;
	}
	
	// length should always be 3 for this lab
	// should have done substring..?
	public static boolean checkOrder(String stuStr, String[] toCheck)
	{
		boolean InOrder = true;
		int[] index = new int[3];
		for(int c = 0; c < 3; c++)
			index[c] = stuStr.indexOf(toCheck[c]);
		int minPos = index[0];
		int minInd = 0;
		for(int k = 0; k < 3; k++)
		{
			if(index[k] == -1)
				System.out.println("\n\t\"" + toCheck[k] + "\" does not seem to exist.");
			else if(k == 0)
			{
				System.out.println("\t\"" + toCheck[k] + "\" was found!");
				continue;
			}
			else if(index[k] > minPos)
			{
				if(minPos == -1) // in the case that first is absent
					System.out.println("Found \"" + toCheck[k] + "\"!");
				else
					System.out.println("\t\"" + toCheck[k] + "\" was found after \"" + toCheck[minInd] + "\"");
				minPos = index[k];
				minInd = k;
			}
			else
			{
				System.out.println("\t\"" + toCheck[k] + "\" does not seem to come after \"" + toCheck[minInd] + "\"");
				InOrder = false;
			}
		}
		return InOrder;
	}
	
	public static boolean checkLineColonPresences(String stuStr, String[] toCheck)
	{
		boolean line0 = checkColonPresence(stuStr, toCheck[0]);
		boolean line1 = checkColonPresence(stuStr, toCheck[1]);
		boolean line2 = checkColonPresence(stuStr, toCheck[2]);
		return line0 && line1 && line2;
	}
	
	public static boolean checkColonPresence(String stuStr, String lookFor)
	{
		boolean found = false;
		System.out.println("checking for \"" + lookFor + ":\"");
		System.out.print("\t");
		if(stuStr.indexOf(lookFor) == -1)
			System.out.println("We did not find \"" + lookFor + ":\"");
		else // found it without the colon!
		{
			if(stuStr.indexOf(lookFor + ":") == -1)
				System.out.println("We found \"" + lookFor + "\" but without a succeeding colon.");
			else if(stuStr.indexOf(lookFor + " :") != -1)
				System.out.println("We found \"" + lookFor + " :\"; there seems to be a space before the colon.");
			else if(stuStr.indexOf(lookFor + ": ") != -1)
				System.out.println("We found \"" + lookFor + ": \" but there seems to be a space after the colon.");
			else
			{
				found = true;
				System.out.println("\"" + lookFor + ":\" exists, and the colons are appropriate!\n");
			}
		}
		return found;
	}
	
	public static boolean checkPresenceInLine(String stuLine, String[] toCheck)
	{
		boolean found = false;
		for(String lookFor : toCheck)
		{
			if(stuLine.indexOf(lookFor) != -1)
			{
				found = true;
				break;
			}
		}
		return found;
	}
	
	public static String removeR(String toRemoveRFrom)
	{
		String temp = toRemoveRFrom;
		while(temp.indexOf('\r')!=-1 && temp.length() > 0)
		{
			temp = temp.substring(0, temp.indexOf("\r")) + temp.substring(temp.indexOf("\r") + 1);
		}
		return temp;
	}
	
	public static String getOutput(String[] mainArgs)
	{
		ByteArrayOutputStream storage = new ByteArrayOutputStream();
		PrintStream orig = System.out;
		PrintStream stu = new PrintStream(storage);
		System.setOut(stu);
		
		Main.main(mainArgs);
		
		System.out.flush();
		System.setOut(orig);
		
		return removeR(storage.toString());
	}
}
