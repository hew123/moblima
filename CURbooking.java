package files;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

import allData.DataBooking;
import allData.DataCineplex;
import allData.DataMovie;
import allData.DataPrice;
import allData.DataPublicholiday;
import allData.DataShowtime;
import allframe.Booking;
import allframe.Cineplex;
import allframe.Movie;
import allframe.Price;
import allframe.Publicholiday;
import allframe.Seat;
import allframe.Showtime;

public class CURbooking {
	
	private DataShowtime datashowtime = DataShowtime.getInstance(); 
	private DataMovie datamovie = DataMovie.getInstance();
	Scanner sc = new Scanner(System.in);
	
	public void booking(Movie movie) {
		for(Movie m: datamovie.getMovies()){
			if (m.getIndex() == movie.getIndex() && !m.getShowStatus().equals("Now Showing")) {
				System.out.println("You have entered an invalid selection! (This ID belongs to a movie that is not showing now)");
				return;
			}
		}
		Showtime showtime = showDetailAndShowtime(movie); 
		if(showtime == null) {
			System.out.println("You selected an invalid Show Time!");
			System.out.println();
			return;
		}
		sc.nextLine();
		showSeats(showtime); //prints the seat plan for user to choose
		int validAndCount;
		String selectedSeatS;
		do {
		System.out.println();
		System.out.print("Select seat(s) (e.g. C3 C4 D5 D6 ...) (-1 to go back) : ");
		selectedSeatS = sc.nextLine();
		if(selectedSeatS.equals("-1")) return;
		validAndCount = validateSeatChosenAndCount(selectedSeatS, showtime);
		if (validAndCount == -1 || validAndCount== 0) System.out.println("Invalid seat selected/ seat(s) taken already. Choose again!");
		} while(validAndCount == -1 || validAndCount== 0);
		//if it reaches here, means selected seat(s) are available for booking. validAndCount is the number of booking called. e.g. (A1 C2) = 2 booking
		
		System.out.println();
		System.out.print("Enter your name: ");
		String name = sc.nextLine();
		System.out.print("Enter your age: ");
		int age = sc.nextInt();
		System.out.print("Enter your phone no.: ");
		sc.nextLine();
		String phone = sc.nextLine();
		System.out.print("Enter your email: ");
		String email = sc.nextLine();
		System.out.println();
		
		float eachbookingprice = determinePrice(age, showtime);
		
		System.out.println("Please confirm your booking details");
		System.out.println("-----------------------------------");
		System.out.println("Cineplex:      " + showtime.getCinema().getCineplex().getCode() + " - " + showtime.getCinema().getCineplex().getLocation());
		System.out.println("Cinema:        " + showtime.getCinema().getCinemaClass());
		System.out.println("Movie:         " + movie.getTitle() + " " + movie.getType());
		System.out.println("Guidance:      " +  movie.getGuidance());
		String s = new SimpleDateFormat("d/M/y").format(showtime.getDate());
		System.out.println("Date of show:  " + new SimpleDateFormat("d/M/y").format(showtime.getDate()) );
		System.out.println("Time of show:  " + new SimpleDateFormat("HH:mm").format(showtime.getDate()) );
		System.out.println("No. of ticket: " + validAndCount);
		System.out.println("Seat(s):       " + selectedSeatS);
		System.out.println("Name:          "+name);
		System.out.println("Age:           "+age);
		System.out.println("Phone No.:     "+phone);
		System.out.println("Email:         "+email);
		System.out.println();
		System.out.println("Please check booking.");
		System.out.printf("Total Price Payable: $%.2f \n", eachbookingprice*validAndCount);
		System.out.println("Y/y to confirm payment.");
		System.out.println("N/n to cancel booking.");
		System.out.print("Input: ");
		String confirm = sc.nextLine();
		if(!confirm.toLowerCase().equals("y")) {
			System.out.println("Booking cancelled.");
			System.out.println();
			return;
		}
		System.out.println();
		System.out.println("Payment Successful!");
		
		Date systemdate = new Date();
		String strsystemdate = new SimpleDateFormat("yMdHm").format(systemdate);
		String TID = showtime.getCinema().getCinemaCode() + strsystemdate;
		datashowtime.setSeatOccupiedThisShowTime(selectedSeatS, showtime);
		Booking newbooking = new Booking(TID, name, age, phone, email, (float)eachbookingprice*validAndCount, systemdate, showtime, selectedSeatS);
		DataBooking databooking = DataBooking.getInstance();
		databooking.createBooking(newbooking);
		System.out.println("Booking made!");
		System.out.println();
	}
	
	
	
	public Showtime showDetailAndShowtime(Movie movie) {
		String title = movie.getTitle();
		String guidance = movie.getGuidance();
		String type = movie.getType();
		String showingstatus = movie.getShowStatus();
		String synopsis = movie.getSynopsis();
		String director = movie.getDirector();
		String cast = movie.getCasts();
		
		System.out.println("Title: "+title);
		System.out.println("Guidance: "+ guidance);
		System.out.println("Type: "+ type);
		System.out.println("Showing Status: "+ showingstatus);
		System.out.println("Synopsis: "+ synopsis);
		System.out.println("Director: "+ director);
		System.out.println("Cast: "+ cast);
		System.out.println();
		
		DataCineplex datacineplex = DataCineplex.getInstance();
		System.out.println("ShowTimes");
		System.out.println("---------");
		for(Cineplex c: datacineplex.getcineplexes()) {
			System.out.println("Cineplex: " + c.getCode() + " -- " + c.getLocation());
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			if(datacineplex.isNotMovieShowing(c, movie)) { //using cineplex cus only cineplex knows if movie is listed based on showtime
				System.out.println("This cineplex does not show this movie.");
				System.out.println();
			}
			else {
				List<Showtime> stime = datashowtime.getShowTimeForThisMovie(c, movie);
				if(stime.size() == 0) {
					System.out.println("No showtime yet have movie in cineplex (If this prints sth is wrong).");
					System.out.println();
					continue;
				}
				System.out.println(movie.getTitle() + " (" +movie.getGuidance() + ")");
				System.out.println("-----------------------------------");
				Hashtable<String, List<Showtime>> showTimeHashTable = new Hashtable<String, List<Showtime>>();
				for(Showtime st: stime) {
					String date = new SimpleDateFormat("d MMM, E").format(st.getDate());
					if(showTimeHashTable.get(date) == null) {
						showTimeHashTable.put(date, new ArrayList<Showtime>());
					}
					showTimeHashTable.get(date).add(st);
				}
				for(Enumeration<String> eum = showTimeHashTable.keys(); eum.hasMoreElements();) {
					String datestr = eum.nextElement();
					System.out.print(datestr);
					
					for(Showtime showt: showTimeHashTable.get(datestr)) { //at this datestr(day), loop throw each showtime.
						
						String time = new SimpleDateFormat("HH:mm").format(showt.getDate());
						System.out.print("\t[" + (showt.getIndex()) + "] " + time);
	
					}
					System.out.println();
				}
				System.out.println();
			}
		}
		
		System.out.print("Select the index in [ ] to book: ");
		int showtimeindex = sc.nextInt();
		Showtime showtime = null;
		for(Showtime st: datashowtime.getShowTimes()) {
			if (st != null) {
				if(st.getIndex() == showtimeindex) {
					showtime = st;
					break;
				}
			}
		}
		return showtime;
	}
	
	public void showSeats(Showtime showtime) {
		System.out.println("              SCREEN         ");
		System.out.println("---------------------------------");
		System.out.println("    1  2  3  4     5  6  7  8");
		System.out.println("---------------------------------");
		char alphabet;
		int i,j;
		for(alphabet = 'A', i=0 ; alphabet <'G'; alphabet++ ) {
			System.out.print(alphabet+"  ");
			for(j=0; j<8 ; i++,j++) {
				if (i%8 == 4)
					System.out.print("   ");
				System.out.print("[");
				if(showtime.getSeat()[i].getOccupied())
					System.out.print("X");
				else
					System.out.print(" ");
				System.out.print("]");
			}
			System.out.println("");
			}
	}
	
	
	public int validateSeatChosenAndCount(String selectedSeatS, Showtime showtime) {
		String[] selectedseat = selectedSeatS.split(" ");
		int count = 0;
		for(String strseat: selectedseat) {
			char r = strseat.charAt(0);
			char c = strseat.charAt(1);
			for (Seat s: showtime.getSeat() ) {
				if(s.getRow() == r && s.getCol()== c) {
					if(s.getOccupied()==false) {
						count++;
					}
					else {
						return -1;
					}
				}
			}
		}
		return count;
	}
	
	//pricing(showingDate, movietype(dig/3d), movieclass(norm/plati), PH and age to determine)
	//a. type of movie (3D, Blockbuster, etc.),
	//b. class of cinema (e.g. Platinum Movie Suites)
	//c. age of movie-goer (e.g. adult, senior citizen, child)
	//d. day of the week or public holiday. 

	public float determinePrice(int age, Showtime st) {
		DataPrice dataprice = DataPrice.getInstance();
		DataPublicholiday datapublicholiday = DataPublicholiday.getInstance();
		
		Price prices = dataprice.getPrices();
		List<Publicholiday> publicholidays = datapublicholiday.getPublicHolidays();
		String cinemaclass = st.getCinema().getCinemaClass();
		String movietype = st.getMovie().getType();
		String moviedaydate = new SimpleDateFormat("d/M").format(st.getDate());
		int moviedaynumber = Integer.parseInt(new SimpleDateFormat("u").format(st.getDate())); //mon =1, sun =7
		
		Boolean isPH = false;
		for(Publicholiday ph: publicholidays) {
			String date = new SimpleDateFormat("d/M").format(ph.getDate());
			if(date.equals(moviedaydate)) {
				isPH = true;
			}
		}
		
		if(cinemaclass.equals("Platinum")) {
			return prices.getplatinum_price();
		}
		if(moviedaynumber >= 5 || isPH == true) {
			if(movietype.equals("Digital")) {
				return prices.getfri_sun_PH_price_d();
			}
			if(movietype.equals("3D")) {
				return prices.getfri_sun_PH_price_3d();
			}
		}
		else {
			if(age<21) {
				if(movietype.equals("Digital")) {
					return prices.getmon_thu_student_price_d();
				}
				if(movietype.equals("3D")) {
					return prices.getmon_thu_student_price_3d();
				}
			}
			if(age>=55) {
				if(movietype.equals("Digital")) {
					return prices.getmon_thu_senior_price_d();
				}
				if(movietype.equals("3D")) {
					return prices.getmon_thu_price_3d();
				}
			}
			else { //21<=age<55
				if(movietype.equals("Digital")) {
					return prices.getmon_thu_price_d();
				}
				if(movietype.equals("3D")) {
					return prices.getmon_thu_price_3d();
				}
			}
		}
		
		return -1; //if returns -1 means there are cases of prices missed out.
	}
}
