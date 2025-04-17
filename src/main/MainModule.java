package main;

import dao.IPolicyService;
import dao.InsuranceServiceImpl;
import entity.Policy;
import exception.PolicyNotFoundException;

import java.util.Collection;
import java.util.Scanner;

public class MainModule {
    public static void main(String[] args) {
        IPolicyService policyService = new InsuranceServiceImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nInsurance Management System");
            System.out.println("1. Create Policy");
            System.out.println("2. Get Policy");
            System.out.println("3. Get All Policies");
            System.out.println("4. Update Policy");
            System.out.println("5. Delete Policy");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter Policy ID: ");
                        int policyId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter Policy Number: ");
                        String policyNumber = scanner.nextLine();
                        System.out.print("Enter Policy Type: ");
                        String policyType = scanner.nextLine();
                        System.out.print("Enter Coverage Amount: ");
                        double coverageAmount = scanner.nextDouble();
                        Policy newPolicy = new Policy(policyId, policyNumber, policyType, coverageAmount);
                        if (policyService.createPolicy(newPolicy)) {
                            System.out.println("Policy created successfully.");
                        } else {
                            System.out.println("Failed to create policy.");
                        }
                        break;

                    case 2:
                        System.out.print("Enter Policy ID: ");
                        policyId = scanner.nextInt();
                        Policy policy = policyService.getPolicy(policyId);
                        System.out.println("Policy: " + policy);
                        break;

                    case 3:
                        Collection<Policy> policies = policyService.getAllPolicies();
                        if (policies.isEmpty()) {
                            System.out.println("No policies found.");
                        } else {
                            for (Policy p : policies) {
                                System.out.println(p);
                            }
                        }
                        break;

                    case 4:
                        System.out.print("Enter Policy ID: ");
                        policyId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter New Policy Number: ");
                        policyNumber = scanner.nextLine();
                        System.out.print("Enter New Policy Type: ");
                        policyType = scanner.nextLine();
                        System.out.print("Enter New Coverage Amount: ");
                        coverageAmount = scanner.nextDouble();
                        Policy updatedPolicy = new Policy(policyId, policyNumber, policyType, coverageAmount);
                        if (policyService.updatePolicy(updatedPolicy)) {
                            System.out.println("Policy updated successfully.");
                        } else {
                            System.out.println("Failed to update policy.");
                        }
                        break;

                    case 5:
                        System.out.print("Enter Policy ID: ");
                        policyId = scanner.nextInt();
                        if (policyService.deletePolicy(policyId)) {
                            System.out.println("Policy deleted successfully.");
                        } else {
                            System.out.println("Failed to delete policy.");
                        }
                        break;

                    case 6:
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (PolicyNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }
}