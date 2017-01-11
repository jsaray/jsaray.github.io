import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Main {

	static HashMap<String, ArrayList<Journal>> listProprietaire = new HashMap<String, ArrayList<Journal>>();
	
	static int columnJournal = 10;

	static JSONArray jsonArrayGlobal = new JSONArray();


	static JSONObject root = new JSONObject();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			FileInputStream file = new FileInputStream(
					new File(
							"E:/iheb/study/Master Data Science/Data Visualization/Projet/Book1.xlsx"));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();

				Journal journal = new Journal();
				boolean findProprietaire = false;
				String proprietaire = "";

				for (int cn = 0; cn < columnJournal; cn++) {
					Cell cell = row.getCell(cn);
					if (cell == null) {
						// This cell is empty/blank/un-used, handle as needed
					} else {

						// Check the cell type and format accordingly
						switch (cell.getCellType()) {

						case Cell.CELL_TYPE_STRING:
							String nom = cell.getStringCellValue() ;
							if (!findProprietaire)
								proprietaire = nom;
							findProprietaire = true;

							System.out.print(cell.getStringCellValue() + ""
									+ "s ; ");
							cn++;
							cell = row.getCell(cn);

							if (cell.getCellType() != Cell.CELL_TYPE_STRING) {
								System.out.print(cell.getNumericCellValue()
										+ "" + "n ; ");

								Pair p = new Pair(cell.getNumericCellValue(),
										nom);

								journal.getListProprietaire().add(p);
							}

							break;

						default:
						}
					}

				}

				System.out.print("  "
						+ row.getCell(columnJournal).getStringCellValue() + ""
						+ " j ; ");
				// if(j==columnJournal) {
				journal.setJournal(row.getCell(columnJournal)
						.getStringCellValue());
				journal.setType(row.getCell(columnJournal + 1)
						.getStringCellValue());
				journal.setAudience(row.getCell(columnJournal+3).getNumericCellValue());
				
				journal.setEtranger(row.getCell(columnJournal+4).getStringCellValue());
				// }

				
				
				if (!listProprietaire.containsKey(proprietaire)) {
					listProprietaire
							.put(proprietaire, new ArrayList<Journal>());
				}

				listProprietaire.get(proprietaire).add(journal);
				System.out.println("");
			}
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		insert(listProprietaire, jsonArrayGlobal);

		root.put("children", jsonArrayGlobal);

		try (FileWriter file = new FileWriter("file1.txt")) {
			file.write(root.toJSONString());
			System.out.println("Successfully Copied JSON Object to File...");
			// System.out.println("\nJSON Object: " + obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static  void insert(HashMap<String, ArrayList<Journal>> listProprietaire , JSONArray jsonArray){
		
		for (String nomProp : listProprietaire.keySet()) {
			ArrayList<Journal> l = listProprietaire.get(nomProp);
		//	JSONObject jsonObject = new JSONObject();
			//JSONArray listJournaux = new JSONArray();

			/*jsonObject.put("proprietaire", nomProp);
			jsonObject.put("nbrJournaux", l.size());
*/
			for (Journal journal : l) {

				double pourcentage = 1;
				for (Pair p : journal.getListProprietaire()) {
					pourcentage *= p.getPourcentage();
				}

				JSONObject journalJson = new JSONObject();
				journalJson.put("nomJournal", journal.getJournal());
				journalJson.put("pourcentage", pourcentage);
				journalJson.put("type", journal.getType());
				journalJson.put("proprietaire", nomProp);
				journalJson.put("audience", journal.getAudience());
				journalJson.put("etranger", journal.getEtranger());
				
				jsonArray.add(journalJson);

			}
		//	jsonObject.put("children", listJournaux);

		//	jsonArray.add(jsonObject);
		}
	}

}
