import Domain.ManageTrainingPackageHandler;
import Domain.TrainingPackage;
import Domain.TrainingSession;
import Domain.TrainingSessionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            String command = chooseCommand(scanner);
            if ("1".equals(command)) {
                ManageTrainingPackageHandler trainer = new ManageTrainingPackageHandler();
                String backOrExitCommand = managePackagesMenu(scanner, trainer);
                isExit = "6".equals(backOrExitCommand);
            } else if ("2".equals(command)) {
                ManageTrainingPackageHandler manageTrainingPackageHandler = new ManageTrainingPackageHandler();
                TrainingSessionHandler trainingSessionHandler = new TrainingSessionHandler();
                String backOrExitCommand = manageSessionMenu(scanner, manageTrainingPackageHandler,trainingSessionHandler);
                isExit = "6".equals(backOrExitCommand);
            }
        }
        System.out.println("Goodbye!");




    }

    private static TrainingSession getTrainingSession(String sessionId) {
        TrainingSessionHandler trainingSessionHandler = TrainingSessionHandler.getInstance();
        Map<Integer, TrainingSession> session = trainingSessionHandler.readBooking();
        return session.get(Integer.parseInt(sessionId));
    }

    private static TrainingPackage getTrainingPackage(String packageID) {
        ManageTrainingPackageHandler manageTrainingPackageHandler= ManageTrainingPackageHandler.getInstance();
        TrainingPackage packages = manageTrainingPackageHandler.getPackage(Integer.parseInt(packageID));

        return packages;
    }

    public static void welcomeMessage() {
        System.out.println("     GYM      ");
        System.out.println("    STUDIO    ");
        System.out.println("  APPLICATION ");
        System.out.println("   WELCOME  ");
    }

    public static String chooseCommand(Scanner scanner) {

        welcomeMessage();

        boolean validCommand = false;
        String command = null;
        while (!validCommand) {
            System.out.println("Enter the number of command you want to perform: ");
            System.out.println("1 - Manage Memberships");
            System.out.println("2 - Manage Training Session");
            command = scanner.nextLine();
            if (null == command) {
                System.out.println("Invalid command!");
            } else switch (command) {
                case "1":
                    validCommand = true;
                    break;
                case "2":
                    validCommand = true;
                    break;
                default:
                    System.out.println("Invalid command!");
                    break;
            }
        }
        return command;
    }

    public static String managePackagesMenu(Scanner scanner, ManageTrainingPackageHandler trainer) {
        boolean exitCommand = false;
        String command = null;
        while (!exitCommand) {
            System.out.println("Enter the number of command you want to perform: ");
            System.out.println("1 - List Packages");
            System.out.println("2 - Add Package");
            System.out.println("3 - Delete Package");
            System.out.println("4 - Back");
            System.out.println("5 - Exit");
            System.out.print("Enter option: ");
            command = scanner.nextLine();
            if ("1".equals(command)) {
                System.out.println("packageID" + "\t" +"memberName" + "\t" + "description"+  "\t" + "totalCount" + "\t" + "completedSession"  );
                Map<Integer, TrainingPackage> packages = trainer.readPackages();
                for (Integer key : packages.keySet()) {
                    TrainingPackage trainingPackage = packages.get(key);
                    System.out.println(trainingPackage.getPackageID() + "\t\t\t"  + trainingPackage.getMemberName()  + "\t\t\t" +trainingPackage.getDescription() + "\t\t\t" + trainingPackage.getTotalCount() + "\t\t\t" + trainingPackage.getCompletedSession());
                }
            }else if ("2".equals(command)) {
                System.out.println("Enter the packageID: ");
                String packageID = scanner.nextLine();
                System.out.println("Enter the total count of the package: ");
                String totalCount = scanner.nextLine();
                System.out.println("Enter the description: ");
                String description = scanner.nextLine();
                System.out.println("Enter the member name ");
                String memberName = scanner.nextLine();
                System.out.println("Enter completed sessions");
                String completedSessions = scanner.nextLine();
                TrainingPackage trainingPackage = new TrainingPackage(packageID, description, Integer.parseInt(totalCount), Integer.parseInt(completedSessions), memberName);
                trainer.addPackage(trainingPackage);
            }else if ("3".equals(command)) {
                System.out.println("Enter the packageID: ");
                String packageID = scanner.nextLine();
                trainer.deletePackage(Integer.parseInt(packageID));
            } else if ("4".equals(command)) {
                exitCommand = true;
            } else if ("5".equals(command)) {
                exitCommand = true;
            } else {
                System.out.println("Invalid command!");
            }
        }
        return command;
    }

    public static String manageSessionMenu(Scanner scanner, ManageTrainingPackageHandler manageTrainingPackageHandler, TrainingSessionHandler trainingSessionHandler) {
        boolean exitCommand = false;
        String command = null;
        while (!exitCommand) {
            System.out.println("1. Book session");
            System.out.println("2. Cancel booking");
            System.out.println("3. Modify booking");
            System.out.println("4. View booked sessions");
            System.out.println("5. Exit");
            System.out.print("Enter option: ");
            String option = scanner.nextLine();

            if (option.equals("1")) {
                System.out.print("Enter Session ID: ");
                scanner.reset();
                String sessionId = scanner.nextLine();
                System.out.print("Enter day: ");
                String day = scanner.nextLine();
                System.out.print("Enter hour: ");
                String hour = scanner.nextLine();
                System.out.print("Enter membership package ID: ");
                String packageID = scanner.next();
                TrainingPackage trainingPackage = getTrainingPackage(packageID);
                if(trainingPackage.getTotalCount()-trainingPackage.getCompletedSession()-1<1){
                    System.out.println("Paketing bitmiş");
                    continue;
                }
                trainingPackage.setCompletedSession(trainingPackage.getCompletedSession()+1);
                manageTrainingPackageHandler.savePackages(trainingPackage);
                TrainingSession trainingSession=new TrainingSession(sessionId,day,hour,packageID);
                trainingSessionHandler.saveBooking(trainingSession);
                System.out.println("kayit OK");

            } else if (option.equals("2")) {
                System.out.print("Enter session ID to cancel: ");
                String sessionIndex = scanner.nextLine();
                TrainingSession session = trainingSessionHandler.readBooking().get(Integer.parseInt(sessionIndex));

                TrainingPackage trainingPackage = getTrainingPackage(sessionIndex);
                if(trainingPackage.getTotalCount()-trainingPackage.getCompletedSession()+1>trainingPackage.getTotalCount()){
                    System.out.println("Paketing asimi");
                    continue;
                }
                trainingPackage.setCompletedSession(trainingPackage.getCompletedSession()-1);
                manageTrainingPackageHandler.savePackages(trainingPackage);
                trainingSessionHandler.deleteBooking(Integer.parseInt(sessionIndex));
                System.out.println("kayit Delete OK");
            } else if (option.equals("3")) {
                System.out.print("Enter Session ID: ");
                String sessionId = scanner.nextLine();
                System.out.print("Enter day: ");
                String day = scanner.nextLine();
                System.out.print("Enter hour: ");
                String hour = scanner.nextLine();
                TrainingSession session = trainingSessionHandler.readBooking().get(Integer.parseInt(sessionId));
                session.setDay(day);
                session.setHour(hour);
                trainingSessionHandler.saveBooking(session);
            } else if (option.equals("4")) {
                System.out.println("SessionID" + "\t" +"Day" + "\t\t\t" + "Hour"+  "\t\t" + "PackageID" );
                for(Map.Entry<Integer, TrainingSession> entry : trainingSessionHandler.readBooking().entrySet()) {
                    TrainingSession value = entry.getValue();
                    System.out.println(value.getSessionId() + "\t\t\t"  + value.getDay()  + "\t\t" +value.getHour() + "\t\t" + value.getPackageID());
                }
            } else if (option.equals("5")) {
                exitCommand=true;
            }
        }
        return command;
    }

}
