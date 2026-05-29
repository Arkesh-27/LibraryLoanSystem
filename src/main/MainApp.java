package main;

import java.util.Scanner;
//Main
import service.TransactionService;
import service.BookService;
import service.MemberService;
import service.ReturnService;
import service.LoanQueryService;
import service.OverdueService;

import performance.PerformanceEvaluator;

public class MainApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while(true){

            System.out.println(
            "\n===== Library System =====");

            System.out.println(
            "1.Process Loan");

            System.out.println(
            "2.Add Book");

            System.out.println(
            "3.Register Member");

            System.out.println(
            "4.Return Book");

            System.out.println(
            "5.Performance Test");

            System.out.println(
            "6.Query Loans");

            System.out.println(
            "7.Overdue Books");

            System.out.println(
            "8.Exit");

            System.out.print(
            "Enter choice: ");

            int choice =
                    sc.nextInt();

            switch(choice){

            case 1:

                System.out.print(
                "Enter Member ID: ");

                int memberId =
                        sc.nextInt();

                System.out.print(
                "Enter Book ID: ");

                int bookId =
                        sc.nextInt();

                TransactionService
                .processLoan(
                        memberId,
                        bookId);

                break;


            case 2:

                System.out.print(
                "Book ID: ");

                int id =
                        sc.nextInt();

                sc.nextLine();

                System.out.print(
                "Book Name: ");

                String title =
                        sc.nextLine();

                System.out.print(
                "ISBN: ");

                String isbn =
                        sc.nextLine();

                BookService.addBook(
                        id,
                        title,
                        isbn);

                break;


            case 3:

                System.out.print(
                "Member ID: ");

                int memberid =
                        sc.nextInt();

                sc.nextLine();

                System.out.print(
                "Member Name: ");

                String name =
                        sc.nextLine();

                MemberService.addMember(
                        memberid,
                        name);

                break;


            case 4:

                System.out.print(
                "Enter Book ID: ");

                int returnBookId =
                        sc.nextInt();

                ReturnService.returnBook(
                        returnBookId);

                break;


            case 5:

                PerformanceEvaluator
                .testInsertPerformance();

                break;


            case 6:

                System.out.print(
                "Enter Member ID: ");

                int queryMember =
                        sc.nextInt();

                LoanQueryService
                .showLoans(
                        queryMember);

                break;


            case 7:

                OverdueService
                .showOverdueBooks();

                break;


            case 8:

                System.out.println(
                        "Program Ended");

                sc.close();

                System.exit(0);

                break;


            default:

                System.out.println(
                        "Invalid Choice");
            }
        }
    }
}