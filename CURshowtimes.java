package files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

import allData.DataCinema;
import allData.DataCineplex;
import allData.DataMovie;
import allData.DataShowtime;
import allframe.Movie;
import allframe.Seat;
import allframe.Showtime;


public class CURshowtimes {

	public void show() {
		int choice;
		Scanner sc = new Scanner(System.in);
		
		do{
			System.out.println();
			System.out.println("ShowTimes Management");
			System.out.println("--------------------");
			System.out.println("1) List ShowTimes");
			System.out.println("2) Create ShowTimes");
			System.out.println("3) Manage ShowTimes");
			System.out.println("4) Back");

			System.out.print("Please enter your choice: ");
			choice = sc.nextInt();
			System.out.println();

			switch(choice) {
			case 1:
				listShowTimes();
				System.out.println();
				break;

			case 2:
				createShowTimes();
				System.out.println();
				break;

			case 3:
				manageShowTimes();
				System.out.println();
				break;

			default:
				break;
			}
		}while(choice != 4);
	}
	
	DataMovie datamovie = DataMovie.getInstance();
	DataCinema datacinema = DataCinema.getInstance();
	DataCineplex datacineplex = DataCineplex.getInstance();
	DataShowtime datashowtime = DataShowtime.getInstance();
	Scanner sc = new Scanner(System.in);
	List<Showtime> loadedshowtime;
	
	
	public void listShowTimes() {
		
		//int index = 0;
		loadedshowtime = new ArrayList<Showtime>();
		System.out.println();
		System.out.println("ShowTimes");
		System.out.println("---------");
		for(int i=1; i<datacineplex.getcineplexes().size()+1; i++) {//could use for each loop too. but need alter getmovie function in DataCineplex.
			List<Movie> movies = datacineplex.getMovies(i);  //i is the index of cineplex's attribute. starts from 1. *is not array
			System.out.println("Cineplex: " + datacineplex.getcineplexes().get(i-1).getCode() + " -- " + datacineplex.getcineplexes().get(i-1).getLocation()); //get(i-1), here is array.
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			if(movies.size() == 0) {
				System.out.println("No listing from this cineplex.");
				System.out.println();
				continue;
			}
			for(Movie movie: movies) {
				List<Showtime> stime = datashowtime.getShowTimesFromEachCineplex(i, movie);
				if(stime.size() == 0) {
					System.out.println("No showtime yet have movie in cineplex (If this prints sth is wrong).");
					System.out.println();
					continue;
				}
				System.out.println(movie.getTitle() + " (" +movie.getGuidance() + ")");
				System.out.println("-----------------------------------");
				Hashtable<String, List<Showtime>> showTimeHashTable = new Hashtable<String, List<Showtime>>();
				for(Showtime st: stime) {
					String date = new SimpleDateFormat("d M, E").format(st.getDate());
					if(showTimeHashTable.get(date) == null) {
						showTimeHashTable.put(date, new ArrayList<Showtime>());
					}
					showTimeHashTable.get(date).add(st);
				}
				for(Enumeration<String> eum = showTimeHashTable.keys(); eum.hasMoreElements();) {
					String datestr = eum.nextElement();
					System.out.print(datestr);
					
					for(Showtime showt: showTimeHashTable.get(datestr)) {
						loadedshowtime.add(showt);
						
						String time = new SimpleDateFormat("HH:mm").format(showt.getDate());
						System.out.print("\t[" + (showt.getIndex()) + "] " + time);
						//index++;
					}
					System.out.println();
				}
				System.out.println();
			}
		}

	}
	
	
	public void createShowTimes() {
		int newIndexOfShow = datashowtime.getShowTimes().size();
		System.out.println();
		System.out.println("Which cinema to create show time? ");
		System.out.println("----------------------------------");
		for(int i=0; i<datacinema.getCinemas().size(); i++) {
			System.out.println(datacinema.getCinemas().get(i).getIndex() + ") " + datacinema.getCinemas().get(i).getCinemaName() + " (" + datacinema.getCinemas().get(i).getCinemaClass() + ") "); 
		}
		System.out.print("Select cinema: ");
		int choiceOfCinema = sc.nextInt(); //later create -1
		System.out.println();
		System.out.println("Which movie to create? (Only Now Showing movie(s) is/are shown)");
		System.out.println("----------------------------------------------------------------");
		for (int i=0; i < datamovie.getMovies().size(); i++) {
			if (datamovie.getMovies().get(i).getShowStatus().equals("Now Showing"))
			System.out.println(datamovie.getMovies().get(i).getIndex() + ") " +  datamovie.getMovies().get(i).getTitle() + " (" + datamovie.getMovies().get(i).getGuidance() + ")" );
		}
		System.out.print("Select movie: ");
		int choiceOfMovie = sc.nextInt();
		System.out.println();
		Seat[] s = new Seat[48];
		int count = 1;
		for(int r = 'A'; r<='F'; r++) {
			for(int c = '1'; c<='8'; c++) {
			s[count-1] = new Seat(count, false, (char)r, (char)c);
			count++;
			}
		}
		System.out.print("Input the Date of show (dd/mm/yyyy) : ");
		String choiceOfDate = sc.next();
		System.out.println();
		System.out.print("Input the Time of show (hh:mm) : ");
		String choiceOfTime = sc.next();
		SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy HH:mm");
		Date date;
		try {
			date = sdf.parse(choiceOfDate + " " + choiceOfTime);
			Showtime showtime = new Showtime(newIndexOfShow, datacinema.getCinemas().get(choiceOfCinema-1), datamovie.getMovies().get(choiceOfMovie-1), date, s);
			datashowtime.createShowTime(showtime);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Success!");
	}
	
	public void manageShowTimes() {
		String newtime;
		
		listShowTimes();
		System.out.print("Select showtime (numbers in [ ]) : ");
		int selIndex = sc.nextInt();
		
		System.out.println("1) Update ShowTime");
		System.out.println("2) Delete ShowTime");
		System.out.println("3) Back");
		System.out.print("Select choice: ");
		int choice = sc.nextInt();
		switch(choice) {
		case 1:
			System.out.print("Enter BOTH new date and time in this format (dd/mm/yyyy hh:mm): ");
			sc.nextLine();
			newtime = sc.nextLine();
			datashowtime.updateShowTime(selIndex, newtime);
			System.out.println("Success!");
			break;
			
		case 2:
			datashowtime.deleteShowTime(selIndex);
			System.out.println("Success!");
			break;
		}
		
	}

	
	
	
	
}
