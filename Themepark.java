package FinalProject;

import java.util.*;

public class Themepark {

    //มาร์ค (เช็คค่าว่าเป็นตัวเลขหรือไม่ ถ้าเป็น แปลงStringเป็นint) 672110147
    public static int Check1(String a) {
        int b;
        if (a.matches("-?\\d+?")) {
            b = Integer.parseInt(a);
            return b;
        } else {
            return b = 0;
        }
    }
    //มาร์ค (แปลงStringเป็นint) 672110147
    public static int isNumeric(String str) {
        return Integer.parseInt(str);
    }
    //มาร์ค (เช็คค่าว่าเป็นตัวเลขหรือไม่) 672110147
    public static boolean checkNumeric(String str) {
        return str.matches("-?\\d+?");
    }
    //มาร์ค (เช็คค่าว่สเก็บ Checkpoint ครบหรือไม่) 672110147
    public static boolean checkID(int[] x) {

        int y = 0;
        for (int i = 0; i < 10; i++) {
            y += x[i];
        }
        if (y == 10) {
            return true;
        } else {
            return false;
        }
    }
    //มาร์ค (เช็คสถานะเครื่องเล่นและแสดง ID เครื่องเล่นที่พัง) 672110147
    public static void checkride(int[] x) {
        String word = " ";
        for (int i = 0; i < 10; i++) {
            if (x[i] == 1) {
                if (!(word == " ")) {
                    word += ", " + i;
                } else {
                    word += i;
                }
            }
        }
        if (!(word.equals(" "))) {
            System.out.println("---Since rides " + word + " are currently unavailable, we will consider that you have collected the checkpoints from those rides.---");
        }
    }

    ////////////////////////////////////////// ปั้นหยา 672110235 (จัดคิวเครื่องเล่น)

    private static final String[] RIDE_NAMES = {"Tornado", "Skycoaster", "Hurricane", "Water Fun", "Lightning Bird", "Super Splash", "Bumper Cars", "Indian Ship",
            "Snow Town", "Haunted Castle"};

    private static final String[] RIDE_DESCRIPTIONS = {"A spinning roller coaster with thrilling loops!", "Experience the thrill of flying through the sky!",
            "High-speed spinning ride imitating a hurricane!", "Exciting water slides and splash zones!", "A thrilling ride mimicking a lightning-fast bird!",
            "A boat ride with a massive water splash!", "Drive and bump into friends in a fun arena!", "A swinging ship ride that mimics sailing the ocean!",
            "Experience a snowy village with fun winter activities!", "A spooky castle filled with mysteries and scares!"};

    private static final int[] RIDE_CAPACITIES = {5, 3, 4, 6, 4, 5, 6, 6, 8, 5};

    private static Queue<String>[] rideQueues = new LinkedList[RIDE_NAMES.length];

    // ปั้นหยา 672110235 (สร้างคิว)
    private static void initializeQueues() {
        for (int i = 0; i < rideQueues.length; i++) {
            rideQueues[i] = new LinkedList<>();
        }
    }
    // ปั้นหยา 672110235 (แสดงรายการเครื่องเล่น)
    private static void displayRides() {
        System.out.println("\nAvailable Rides:");
        for (int i = 0; i < RIDE_NAMES.length; i++) {
            System.out.println((i + 1) + ". " + RIDE_NAMES[i] + " - " + RIDE_DESCRIPTIONS[i]);
        }
    }
    // ปั้นหยา 672110235 (เพิ่มชื่อในคิว)
    private static void joinQueue(int rideIndex, String name) {
        rideQueues[rideIndex].add(name);
        System.out.println(name + " joined the queue for " + RIDE_NAMES[rideIndex] + ".");
    }
    // ปั้นหยา 672110235 (เริ่มเครื่องเล่น / ดึงลูกค้าออกจากคิว)
    private static void startRide(int rideIndex) {
        System.out.println("Starting the ride: " + RIDE_NAMES[rideIndex]);
        for (int i = 0; i < RIDE_CAPACITIES[rideIndex] && !rideQueues[rideIndex].isEmpty(); i++) {
            System.out.println(rideQueues[rideIndex].poll() + " is enjoying the " + RIDE_NAMES[rideIndex] + "!");
        }
        System.out.println("The ride " + RIDE_NAMES[rideIndex] + " has ended.");
    }
    // ปั้นหยา 672110235
    public static void mainmenu() {
        initializeQueues();
        Scanner sc = new Scanner(System.in);
        int totalRevenue = 0;

        while (true) {
            System.out.println("\nWelcome to Park Tarnee!");
            System.out.println("1. Join a queue for a ride");
            System.out.println("2. Check queue status");
            System.out.println("3. Start a ride");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            try {
                int choice = sc.nextInt();
                sc.nextLine(); // Clear the buffer after reading a number
                switch (choice) {
                    case 1:
                        System.out.println("Enter customer ID:");
                        String name = sc.nextLine();
                        displayRides();
                        System.out.println("Select a ride by number:");
                        int rideIndex = sc.nextInt() - 1;
                        if (rideIndex >= 0 && rideIndex < RIDE_NAMES.length) {
                            joinQueue(rideIndex, name);
                        } else {
                            System.out.println("Invalid ride selection.");
                        }
                        break;
                    case 2:
                        displayRides();
                        System.out.println("Select a ride by number to check queue status:");
                        int statusIndex = sc.nextInt() - 1;
                        if (statusIndex >= 0 && statusIndex < RIDE_NAMES.length) {
                            System.out.println("Queue status for " + RIDE_NAMES[statusIndex] + ": " + rideQueues[statusIndex].size() + " people in queue");
                        } else {
                            System.out.println("Invalid ride selection.");
                        }
                        break;
                    case 3:
                        displayRides();
                        System.out.println("Select a ride by number to start:");
                        int startIndex = sc.nextInt() - 1;
                        if (startIndex >= 0 && startIndex < RIDE_NAMES.length) {
                            startRide(startIndex);
                        } else {
                            System.out.println("Invalid ride selection.");
                        }
                        break;
                    case 4:
                        System.out.println("Thank you for visiting Park Tarnee! Total revenue collected: " + totalRevenue + " Baht");
                        return; // Exit the program
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer.");
                sc.nextLine();

            }
        }
    }

    ////////////////////////////////////////// อั้ม 672110231
    //อั้ม 672110231
    public static void Vip(int[] ID) {
        String[] vip = {"Phattadon", "Narudon", "Pacharasorn", "Poonyapa", "Nuttida"};
        while (true) {
            int x = 0;
            int y = 0;
            Scanner input = new Scanner(System.in);
            System.out.println("Please enter your name");
            String name = input.next();
            if (name.equalsIgnoreCase("exit")) {
                System.out.println("Stop working");
                break;
            }
            boolean found = false;
            for (int i = 0; i < vip.length; i++) {
                if (name.equalsIgnoreCase(vip[i])) {
                    found = true;
                    break;
                }
            }
            if (found) {
                System.out.println(name + "You have access to VIP rooms in restaurants and accommodations,Thank you\uD83D\uDE4F\uD83C\uDFFB");
                for (int i = 0; x == 1; i++) {
                    if (ID[i] == 0) {
                        y = i + 1;
                        x = 1;
                    }
                }
                break;
            } else {
                System.out.println("This name is not found in the VIP list.");
            }
        }
    }

    //อั้ม 672110231
    public static void sell(int[] ID) {
        Scanner sc = new Scanner(System.in);
        int bat1 = 300;
        int bat2 = 150;
        int total = 0;

        while (true) {
            int x = 0, y = 0;
            int price = 0;
            if (price != 0) {
                System.out.println("Enter z, to stop working.");
            }
            System.out.println("Name?");
            String name = sc.next();
            if (name.equalsIgnoreCase("z")) {
                System.out.println("Stop working");
                break;
            }
            int old;
            double tall;
            while (true) {
                System.out.println("Old?");
                sc.nextLine();
                String k = sc.nextLine();
                if (k.matches("-?\\d+?")){
                    old = Integer.parseInt(k);
                    break;
                }else {
                    System.out.println("The input does not match the specified criteria.");
                }
            }
            while (true){
                System.out.println("Tall?");
                String j = sc.nextLine();
                if (j.matches("-?\\d+(\\.\\d+)?")){
                    tall = Double.parseDouble(j);
                    break;
                }else {
                    System.out.println("The input does not match the specified criteria.");
                }
            }
            int i = 0;
            while (x == 0) {
                if (ID[i] == 0) {
                    y = i + 1;
                    System.out.println(y);
                    x = 1;
                    ID[i] = 1;
                }
                i += 1;
            }
            // ใช้ตัวแปรเพื่อตรวจสอบราคาในรอบนั้น

            if (old <= 7) {
                price = 0;
                System.out.println(price + " Baht");
                if (tall >= 151) {
                    System.out.println("Tornado, Skycoaster, Hurricane, Water Fun, Lightning Bird, Super Splash, Bumper Cars");
                }
                if (tall <= 150) {
                    System.out.println("Indian Ship, Snow Town, Haunted Castle, Haunted Castle");
                }
            } else if (old <= 15) {
                price = bat2;
                if (tall >= 151) {
                    System.out.println("Tornado, Skycoaster, Hurricane, Water Fun, Lightning Bird, Super Splash, Bumper Cars");
                }
                if (tall <= 150) {
                    System.out.println("Indian Ship, Snow Town, Haunted Castle, Haunted Castle");
                }
            }else {
                price = bat1;
                System.out.println(price + " Baht");
                if (tall >= 151) {
                    System.out.println("Tornado, Skycoaster, Hurricane, Water Fun, Lightning Bird, Super Splash, Bumper Cars");
                }
                if (tall <= 150) {
                    System.out.println("Indian Ship, Snow Town, Haunted Castle, Haunted Castle");
                }


            }

            // สะสมค่า price ลงใน total
            total += price;
            System.out.println("name: " + name + " : " + "old: " + old + ", tall: " + tall + " cm " + "ID " + y);
        }

        System.out.println("Total collected: " + total + " Baht");
    }

    ////////////////////////////////////////// พชรสร (จัดการอาหาร) 672110148
    public static String[][] foodData = {
            {"Hot Dog", "50.0", "Food"},
            {"Soda", "20.0", "Beverage"},
            {"Hamburger", "80.0", "Food"},
            {"Juice", "30.0", "Beverage"},
            {"Pizza", "150.0", "Food"},
            {"Bubble Tea", "40.0", "Beverage"}
    };
    // พชรสร (จัดการอาหาร) 672110148
    public static void foodMenuManager() {
        double totalCost = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Available food items:");
        for (int i = 0; i < foodData.length; i++) {
            System.out.println((i + 1) + " - Name: " + foodData[i][0] + ", Price: " + foodData[i][1] + " Baht, Category: " + foodData[i][2]);
        }

        while (true) {
            System.out.println("Please select food (enter food number or type '0' to finish selection):");
            String foodChoice = scanner.nextLine();

            if (foodChoice.equals("0")) {
                break;
            }

            if (foodChoice.matches("\\d+")) {
                int foodIndex = Integer.parseInt(foodChoice) - 1;
                if (foodIndex >= 0 && foodIndex < foodData.length) {
                    double price = Double.parseDouble(foodData[foodIndex][1]);
                    totalCost += price;
                    System.out.println("You selected: " + foodData[foodIndex][0] + " Price: " + price + " Baht");
                } else {
                    System.out.println("Invalid selection! Please try again.");
                }
            } else {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }

        System.out.println("Total cost: " + totalCost + " Baht");
        System.out.println();

    }
    // พชรสร (เพิ่มอาหาร) 672110148
    public static void addFood() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Food Name: ");
        String FoodName = sc.nextLine();
        double FoodPrice = 0;
        while (true) {
            System.out.print("Enter Food Price: ");
            String priceInput = sc.nextLine();

            if (priceInput.matches("\\d+")) {
                FoodPrice = Double.parseDouble(priceInput);
                if (FoodPrice < 0) {
                    System.out.println("Price cannot be negative. Please enter a valid price.");
                    continue;
                }
                break;
            } else {
                System.out.println("Invalid input! Please enter a valid number for the price.");
            }
        }
        System.out.print("Enter Food Category (*e.g. Food, Beverage): ");
        String Category = sc.nextLine();
        String[][] newFoodData = new String[foodData.length + 1][3];
        for (int i = 0; i < foodData.length; i++) {
            newFoodData[i] = foodData[i];
        }
        newFoodData[foodData.length] = new String[]{FoodName, Double.toString(FoodPrice), Category};
        foodData = newFoodData;
        System.out.println("Food added successfully!");
    }

    //////////////////////////////////////////  โฟค 672110150
    private ArrayList<String> announcements = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    // พัทธดนย์ (เพิ่มข้อความประกาศ) โฟค 150
    public void addAnnouncement(String message) {
        announcements.add(message);
    }

    //  พัทธดนย์ (โชว์ข้อความประกาศ ถ้าไม่มีจะแสดงว่าไม่มี) โฟค 672110150
    public void displayAnnouncements() {
        System.out.println("--- Carnival Announcements ---");
        if (announcements.isEmpty()) {
            System.out.println("No announcements available.");
        } else {
            for (String announcement : announcements) {
                System.out.println("- " + announcement);
            }
        }
    }

    // พัทธดนย์ (การให้คะแนน) โฟค 672110150
    public void addRating() {
        System.out.print("Please rate your satisfaction with the park (1-10): ");
        int rating = scanner.nextInt();
        scanner.nextLine(); // รับบรรทัดถัดไปเพื่อหลีกเลี่ยงปัญหา

        if (rating >= 1 && rating <= 10) {
            System.out.println("Rating recorded: " + rating);
            if (rating < 5) {
                System.out.print("We're sorry to hear that. Could you tell us why? ");
                String feedback = scanner.nextLine();
                System.out.println("Thank you for your feedback: " + feedback);
            } else if (rating > 5) {
                System.out.print("Thank you for rating. Can we get your feedback? ");
                String improvementIdeas = scanner.nextLine();
                System.out.println("We appreciate your suggestions: " + improvementIdeas);
            }
        } else {
            System.out.println("Invalid rating! Please enter a value between 1 and 10.");
        }
    }

    //  พัทธดนย์ (ฟังก์ชันหลักประกาศ) โฟค 672110150
    public void start() {
        // เพิ่มข้อความประกาศ
        addAnnouncement("Thank you for visit us !");
        addAnnouncement("We have a discount card if you spend more than 300 Baht free to use next time ! ");
        addAnnouncement("We're hope you will comeback soon! ");

        // แสดงข้อความประกาศทั้งหมด
        displayAnnouncements();

        // รับการให้คะแนน
        addRating();

        // จบการทำงาน
        System.out.println("Thank you for your feedback! We appreciate your visit.");
    }

    /////////////////////////////////////////////////////////นฤดล เมืองอุดร (มาร์ค) 672110147
    // มาร์ค 672110147
    public static String[] customer(int[] ride) {
        int[][] arrayid = new int[10000][10];
        for (int i = 0; i < 10; i++) {
            if (ride[i] == 1) {
                for (int j = 0; j < 1000; j++) {
                    arrayid[j][i] = 1;
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            arrayid[1][i] = 1;
        }

        String[] reward = {"Free entry ticket for the next visit", "100x60 mascot plush", "50x30 mascot plush", "20% discount ticket for the next visit", "25x15 mascot plush", "Mascot keychain", "Theme park pen"};
        String[] rewardID = new String[10000];
        Scanner input = new Scanner(System.in);
        int x1 = 0;
        while (x1 < 1) {
            System.out.println("================================================");
            checkride(ride);
            System.out.println("Enter 1 , to open ride list.");
            System.out.println("Enter 2 , to open food center menu.");
            System.out.println("Enter 3 , To claim the reward for collecting all checkpoints.");
            System.out.println("Enter 4 , to Return.");
            System.out.println("================================================");
            String check = input.nextLine();
            int check1 = Check1(check);
            if (check1 == 1) {
                displayRides();
            } else if (check1 == 2) {
                System.out.println();
                foodMenuManager();
            } else if (check1 == 3) {
                System.out.print("Please enter the order of your ID : ");
                String ID = input.nextLine();
                if (checkID(arrayid[isNumeric(ID)])) {
                    System.out.println("================================================");
                    System.out.println("  0.5 % get " + reward[0]);
                    System.out.println("  2 % get " + reward[1]);
                    System.out.println("  5 % get " + reward[2]);
                    System.out.println("  7.5 % get " + reward[3]);
                    System.out.println("  10 % get " + reward[4]);
                    System.out.println("  25 % get " + reward[5]);
                    System.out.println("  50 % get " + reward[6]);
                    System.out.println("================================================");
                    int randomNumber = (int) (Math.random() * 1000) + 1;
                    if (randomNumber <= 5) {
                        System.out.println("ID " + ID + " You have received " + reward[0]);
                        rewardID[isNumeric(ID)] = reward[0];
                    } else if (randomNumber <= 25) {
                        System.out.println("ID " + ID + " You have received " + reward[1]);
                        rewardID[isNumeric(ID)] = reward[1];
                    } else if (randomNumber <= 75) {
                        System.out.println("ID " + ID + " You have received " + reward[2]);
                        rewardID[isNumeric(ID)] = reward[2];
                    } else if (randomNumber <= 150) {
                        System.out.println("ID " + ID + " You have received " + reward[3]);
                        rewardID[isNumeric(ID)] = reward[3];
                    } else if (randomNumber <= 250) {
                        System.out.println("ID " + ID + " You have received " + reward[4]);
                        rewardID[isNumeric(ID)] = reward[4];
                    } else if (randomNumber <= 500) {
                        System.out.println("ID " + ID + " You have received " + reward[5]);
                        rewardID[isNumeric(ID)] = reward[5];
                    } else {
                        System.out.println("ID " + ID + " You have received " + reward[6]);
                        rewardID[isNumeric(ID)] = reward[6];
                    }

                    System.out.println("================================================");
                    Themepark system = new Themepark();
                    system.start();
                } else {
                    System.out.println("You haven't collected all the checkpoints yet.");
                }
                x1 = 1;
            } else if (check1 == 4) {
                x1 = 1;
            } else {
                System.out.println("Please enter the values as specified.");
            }
        }
        return rewardID;
    }

    /////////////////////////////////////////////////////////////////////////
    // มาร์ค 672110147
    public static int[] staff(String[] reward, int[] id) {
        int[] rides = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[][] arrayid = new int[10000][10];
        for (int i = 0; i < 10; i++) {
            if (rides[i] == 1) {
                for (int j = 0; j < 10000; j++) {
                    arrayid[j][i] = 1;
                }
            }
        }
        Scanner input = new Scanner(System.in);
        int x = 0;
        while (x < 1) {
            System.out.println("================================================");
            System.out.println("Enter 1 , to sell theme park ticket.");
            System.out.println("Enter 2 , to check VIP list.");
            System.out.println("Enter 3 , To ride management menu.");
            System.out.println("Enter 4 , To report that the ride is out of order.");
            System.out.println("Enter 5 , to check point.");
            System.out.println("Enter 6 , to check reward in ID.");
            System.out.println("Enter 7 , to Add Food Item.");
            System.out.println("Enter 8 , to Return.");
            System.out.println("================================================");
            String check = input.nextLine();
            int Check = Check1(check);
            if (Check == 4) {
                int y = 0;
                while (y < 1) {
                    System.out.println("================================================");
                    System.out.print("Enter the order of the rides : ");
                    String ride = input.nextLine();
                    System.out.println("Please specify the status of the ride");
                    System.out.println("Enter 1, to indicate the ride is operational.");
                    System.out.println("Enter 2, to indicate the ride is out of order.");
                    System.out.println("Enter 3, Return.");
                    System.out.println("================================================");
                    String status = input.nextLine();
                    int Status = Check1(status);
                    if (Status == 1) {
                        System.out.println("================================================");
                        System.out.println("Submit information: 'Ride number " + ride + " is operational.'");
                        rides[isNumeric(ride)] = 0;
                        System.out.println("================================================");
                        y = 1;
                    } else if (Status == 2) {
                        System.out.println("================================================");
                        System.out.println("Submit information: 'Ride number " + ride + " is out of order.'");
                        rides[isNumeric(ride)] = 1;
                        System.out.println("================================================");
                        y = 1;
                    } else if (Status == 3) {
                        y = 1;
                    } else {
                        System.out.println("The input is not a number, or this ride order does not exist.");
                    }
                }
            } else if (Check == 6) {
                System.out.print("Enter the order of your ID: ");
                String ID = input.nextLine();
                if (checkNumeric(ID)) {
                    System.out.println("================================================");
                    System.out.println("ID " + ID + " : " + reward[isNumeric(ID)]);
                } else {
                    System.out.println("The input is not a number, or this ID does not exist.");
                }
            } else if (Check == 1) {
                sell(id);
            } else if (Check == 2) {
                Vip(id);
            } else if (Check == 3) {
                mainmenu();
            } else if (Check == 5) {
                int y = 0;
                while (y < 1) {
                    System.out.println("================================================");
                    checkride(rides);
                    System.out.print("Please enter the order of the rides : ");
                    String point = input.nextLine();
                    System.out.print("Please enter the order of your ID : ");
                    String ID = input.nextLine();
                    if ((checkNumeric(point)) && (checkNumeric(ID))) {
                        System.out.println("================================================");
                        System.out.println("Checkpoint " + point + " ID " + ID + " complete");
                        arrayid[isNumeric(ID) - 1][isNumeric(point)] = 1;
                        System.out.println("================================================");
                        y = 1;
                        if (checkID(arrayid[isNumeric(ID)])) {
                            System.out.println("!!!!! The customer can now claim their reward !!!!!");
                            System.out.println("!!!!! The customer can now claim their reward !!!!!");
                        }
                    } else {
                        System.out.println("The input is not a number, or there is no ride order or ID provided.");
                    }
                }
            } else if (Check == 7) {
                addFood();
            } else if (Check == 8) {
                x = 1;
            } else {
                System.out.println("Please enter the values as specified.");
            }
        }
        return rides;
    }
    // มาร์ค 672110147
    public static void main(String[] args) throws Exception {
        String[] rewardID = new String[10000];
        int[] ID = new int[10000];
        int[] rides = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        Scanner input = new Scanner(System.in);
        int x = 0;
        while (x < 1) {
            System.out.println("================================================");
            System.out.println("Enter 1 , to open the customer menu.");
            System.out.println("Enter 2 , to open the staff menu.");
            System.out.println("================================================");
            String check = input.nextLine();
            int Check = Check1(check);
            if (Check == 1) {
                rewardID = customer(rides);
            } else if (Check == 2) {
                System.out.println("================================================");
                System.out.print("Enter the staff password : ");
                String staffcode = input.nextLine();
                if (Objects.equals(staffcode, "123456")) {
                    rides = staff(rewardID, ID);
                } else {
                    System.out.println("Incorrect password.");
                }
            } else {
                System.out.println("Please enter the values as specified.");
            }
        }
    }

}

