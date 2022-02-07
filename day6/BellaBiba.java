package day6;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import day4.Authentication;
import day4.User;

public class BellaBiba {

	static ArrayList<FileStructure> files = new ArrayList<>();
	static ArrayList<User> users = new ArrayList<>();

	public static void main(String[] args) {

		addFiles();

		Authentication au = new Authentication();
		au.createUser("Raj", "123", "Shah", 1, 1);
		au.createUser("Alice", "456", "Alice", 2, 3);
		au.createUser("Bob", "789", "Bob", 4, 2);

		au.printUsers();

		Scanner sc = new Scanner(System.in);

		int n=0;

		while (n < 3) {
			System.out.println("Select option : \n 1.Login \n 2.Create User \n 3.Exit ");
			n = sc.nextInt();
			Login: switch (n) {
			case 1:
				System.out.println("Enter username and password");
				String uid = sc.next();
				String password = sc.next();
				int op=0;
				User user = au.searchUser(uid, password);
				while (user != null && op!=3) {

					System.out.println("Select operation to perform : \n 1.Read \n 2.Write \n 3.Go Back");
					 op= sc.nextInt();
					if (op == 3)
						 break;
					System.out.println("Select file to perform operation on : ");
					listFiles();
					int num = sc.nextInt();

					FileStructure fileSelected = files.get(num);

					if ((op==1) && (fileSelected.bellaLevel <= user.bellaLevel) && (fileSelected.bibaLevel >= user.bibaLevel && op == 1)) {
						
						System.out.println("User is authorized to access the file");
						readFromFile(fileSelected.filepath);
					}
					else if((op==2) && (fileSelected.bellaLevel >= user.bellaLevel) && (fileSelected.bibaLevel <= user.bibaLevel)) {
						
						System.out.println("User is authorized to access the file");
						System.out.println("Enter text to be written in the file");
						String text = "";

						text += sc.next();
						text += sc.nextLine();
						writeToFile(fileSelected.filepath, text);
					}
					 else {
						System.out.println("Access denied");
					 }
				}
				break;
			case 2:
				System.out.println("Enter username , password , surname , biba level ,bella level");
				String uid1 = sc.next();
				String password1 = sc.next();
				String surname = sc.next();
				int biba = sc.nextInt();
				int bella = sc.nextInt();

				users.add(au.createUser(uid1, password1, surname, biba, bella));
				break Login;
			default:
				break Login;
			}
		}
		
		sc.close();
		System.out.println("Successfully exited");
	}

	public static void writeToFile(String path, String text) {
		try {

			// Open given file in append mode by creating an
			// object of BufferedWriter class
			BufferedWriter out = new BufferedWriter(new FileWriter(path, true));

			// Writing on output stream
			out.write(text);
			// Closing the connection
			out.close();
			System.out.println("File written successfully");
		}

		// Catch block to handle the exceptions
		catch (IOException e) {

			// Display message when exception occurs
			System.out.println("exception occurred" + e);
		}
	}

	public static void readFromFile(String path) {
		try {
			File myObj = new File(path);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				System.out.println(data);
			}
			myReader.close();
			System.out.println("File read successfully");
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public static void addFiles() {
		String path = "C:\\Users\\raj.shah\\eclipse-workspace\\EncryptionAlgorithms\\demofiles";

		File f = new File(path);
		int cnt=1;
		
		for (String s : f.list()) {

			String filePath = path + "\\" + s;
			FileStructure filestruct = new FileStructure(filePath, cnt, cnt+1);
			cnt++;
			files.add(filestruct);
		}
	}

	public static void listFiles() {

		for (int i = 0; i < files.size(); i++) {
			System.out.println(i + "." + files.get(i).filepath);
			//System.out.println(files.get(i).bellaLevel + " " + files.get(i).bibaLevel);
		}
	}
}
