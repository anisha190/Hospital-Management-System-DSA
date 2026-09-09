import java.util.*;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.UUID;

public class HospitalManagementSystemDSA {
    private static LinkedList<Patient> patients = new LinkedList<>();
    private static LinkedList<Doctor> doctors = new LinkedList<>();
    private static LinkedList<Appointment> appointments = new LinkedList<>();
    private static Queue<Bill> billingQueue = new LinkedList<>();
    private static PriorityQueue<Appointment> priorityAppointments = new PriorityQueue<>(Comparator.comparing(Appointment::getDateTime));

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n========== HOSPITAL MANAGEMENT SYSTEM ==========");
            System.out.println("=== Data Structures & Algorithms Course Implementation ===");
            System.out.println("1. Add Patient (LinkedList Insertion)");
            System.out.println("2. Discharge Patient (LinkedList Deletion)");
            System.out.println("3. Add Doctor");
            System.out.println("4. Remove Doctor");
            System.out.println("5. Schedule Appointment (PriorityQueue)");
            System.out.println("6. View All Appointments");
            System.out.println("7. Generate Bill (Queue Enqueue)");
            System.out.println("8. Process Bills (Queue Dequeue)");
            System.out.println("9. View All Patients (LinkedList Traversal)");
            System.out.println("10. View All Doctors");
            System.out.println("11. Search Patient (Linear Search Algorithm)");
            System.out.println("12. Sort Patients by Name (Bubble Sort Algorithm)");
            System.out.println("13. View Next Appointment (PriorityQueue Peek)");
            System.out.println("14. Exit");
            System.out.println("==================================================");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\n=== ADD NEW PATIENT (LinkedList Insertion) ===");
                    if (patients.size() >= 100) {
                        System.out.println("Hospital capacity full!");
                        break;
                    }
                    System.out.print("Enter patient name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter patient age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter patient phone: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter patient address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter medical history: ");
                    String history = scanner.nextLine();

                    Patient newPatient = new Patient(name, age, phone, address, history);
                    patients.add(newPatient);
                    System.out.println("Patient added successfully! ID: " + newPatient.getId());
                    System.out.println("Current patient count: " + patients.size());
                    break;

                case 2:
                    System.out.println("\n=== DISCHARGE PATIENT (LinkedList Deletion) ===");
                    if (patients.isEmpty()) {
                        System.out.println("No patients in the system!");
                        break;
                    }
                    System.out.print("Enter patient ID to discharge: ");
                    String dischargeId = scanner.nextLine();

                    boolean foundPatient = false;
                    for (int i = 0; i < patients.size(); i++) {
                        if (patients.get(i).getId().equals(dischargeId)) {
                            patients.remove(i);
                            System.out.println("Patient discharged successfully!");
                            System.out.println("Remaining patients: " + patients.size());
                            foundPatient = true;
                            break;
                        }
                    }

                    if (!foundPatient) {
                        System.out.println("Patient not found!");
                    }
                    break;

                case 3:
                    System.out.println("\n=== ADD NEW DOCTOR ===");
                    if (doctors.size() >= 50) {
                        System.out.println("Doctor limit reached!");
                        break;
                    }
                    System.out.print("Enter doctor name: ");
                    String docName = scanner.nextLine();
                    System.out.print("Enter specialization: ");
                    String specialization = scanner.nextLine();
                    System.out.print("Enter experience years: ");
                    int experience = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter consultation fee: ");
                    double fee = scanner.nextDouble();
                    scanner.nextLine();

                    Doctor newDoctor = new Doctor(docName, specialization, experience, fee);
                    doctors.add(newDoctor);
                    System.out.println("Doctor added successfully! ID: " + newDoctor.getId());
                    break;

                case 4:
                    System.out.println("\n=== REMOVE DOCTOR ===");
                    if (doctors.isEmpty()) {
                        System.out.println("No doctors in the system!");
                        break;
                    }
                    System.out.print("Enter doctor ID to remove: ");
                    String removeDocId = scanner.nextLine();

                    boolean foundDoctor = false;
                    for (int i = 0; i < doctors.size(); i++) {
                        if (doctors.get(i).getId().equals(removeDocId)) {
                            doctors.remove(i);
                            System.out.println("Doctor removed successfully!");
                            foundDoctor = true;
                            break;
                        }
                    }

                    if (!foundDoctor) {
                        System.out.println("Doctor not found!");
                    }
                    break;

                case 5:
                    System.out.println("\n=== SCHEDULE APPOINTMENT (PriorityQueue) ===");
                    if (patients.isEmpty() || doctors.isEmpty()) {
                        System.out.println("Need at least one patient and one doctor!");
                        break;
                    }

                    System.out.print("Enter patient ID: ");
                    String patientId = scanner.nextLine();
                    System.out.print("Enter doctor ID: ");
                    String doctorId = scanner.nextLine();

                    Patient appointmentPatient = null;
                    for (Patient p : patients) {
                        if (p.getId().equals(patientId)) {
                            appointmentPatient = p;
                            break;
                        }
                    }

                    if (appointmentPatient == null) {
                        System.out.println("Patient not found!");
                        break;
                    }

                    Doctor appointmentDoctor = null;
                    for (Doctor d : doctors) {
                        if (d.getId().equals(doctorId)) {
                            appointmentDoctor = d;
                            break;
                        }
                    }

                    if (appointmentDoctor == null) {
                        System.out.println("Doctor not found!");
                        break;
                    }

                    System.out.print("Enter date (YYYY-MM-DD): ");
                    String date = scanner.nextLine();
                    System.out.print("Enter time (HH:MM): ");
                    String time = scanner.nextLine();
                    System.out.print("Enter priority (1-High, 2-Medium, 3-Low): ");
                    int priority = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter reason: ");
                    String reason = scanner.nextLine();

                    Appointment appointment = new Appointment(appointmentPatient, appointmentDoctor, date + " " + time, reason, priority);
                    appointments.add(appointment);
                    priorityAppointments.add(appointment);
                    System.out.println("Appointment scheduled! ID: " + appointment.getId());
                    break;

                case 6:
                    System.out.println("\n=== ALL APPOINTMENTS ===");
                    if (appointments.isEmpty()) {
                        System.out.println("No appointments scheduled!");
                        break;
                    }

                    System.out.println("Total appointments: " + appointments.size());
                    for (int i = 0; i < appointments.size(); i++) {
                        System.out.println(appointments.get(i));
                        System.out.println("--------------------");
                    }
                    break;

                case 7:
                    System.out.println("\n=== GENERATE BILL (Queue Enqueue) ===");
                    if (patients.isEmpty()) {
                        System.out.println("No patients in the system!");
                        break;
                    }

                    System.out.print("Enter patient ID: ");
                    String billPatientId = scanner.nextLine();

                    Patient billPatient = null;
                    for (Patient p : patients) {
                        if (p.getId().equals(billPatientId)) {
                            billPatient = p;
                            break;
                        }
                    }

                    if (billPatient == null) {
                        System.out.println("Patient not found!");
                        break;
                    }

                    System.out.print("Enter bill amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter services provided: ");
                    String services = scanner.nextLine();

                    Bill newBill = new Bill(billPatient, amount, services);
                    billingQueue.add(newBill);
                    System.out.println("Bill generated and added to queue! Bill ID: " + newBill.getId());
                    System.out.println("Bills in queue: " + billingQueue.size());
                    break;

                case 8:
                    System.out.println("\n=== PROCESS BILLS (Queue Dequeue) ===");
                    if (billingQueue.isEmpty()) {
                        System.out.println("No bills to process!");
                        break;
                    }

                    Bill processedBill = billingQueue.poll();
                    System.out.println("Processing bill...");
                    System.out.println("Bill ID: " + processedBill.getId());
                    System.out.println("Patient: " + processedBill.getPatient().getName());
                    System.out.println("Amount: RS." + processedBill.getAmount());
                    System.out.println("Services: " + processedBill.getServices());
                    System.out.println("Bill processed successfully!");
                    System.out.println("Remaining bills: " + billingQueue.size());
                    break;

                case 9:
                    System.out.println("\n=== ALL PATIENTS (LinkedList Traversal) ===");
                    if (patients.isEmpty()) {
                        System.out.println("No patients in the system!");
                        break;
                    }

                    System.out.println("Total patients: " + patients.size());
                    for (int i = 0; i < patients.size(); i++) {
                        System.out.println(patients.get(i));
                        System.out.println("--------------------");
                    }
                    break;

                case 10:
                    System.out.println("\n=== ALL DOCTORS ===");
                    if (doctors.isEmpty()) {
                        System.out.println("No doctors in the system!");
                        break;
                    }

                    System.out.println("Total doctors: " + doctors.size());
                    for (int i = 0; i < doctors.size(); i++) {
                        System.out.println(doctors.get(i));
                        System.out.println("--------------------");
                    }
                    break;

                case 11:
                    System.out.println("\n=== SEARCH PATIENT (Linear Search Algorithm) ===");
                    if (patients.isEmpty()) {
                        System.out.println("No patients in the system!");
                        break;
                    }

                    System.out.println("Search by:");
                    System.out.println("1. Patient ID");
                    System.out.println("2. Patient Name");
                    System.out.print("Enter choice: ");
                    int searchChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (searchChoice == 1) {
                        System.out.print("Enter Patient ID: ");
                        String searchId = scanner.nextLine();

                        int comparisons = 0;
                        boolean patientFound = false;
                        for (int i = 0; i < patients.size(); i++) {
                            comparisons++;
                            if (patients.get(i).getId().equals(searchId)) {
                                System.out.println("\nPatient Found!");
                                System.out.println(patients.get(i));
                                System.out.println("Linear Search Statistics:");
                                System.out.println("Comparisons made: " + comparisons);
                                System.out.println("Time Complexity: O(n) where n = " + patients.size());
                                patientFound = true;
                                break;
                            }
                        }

                        if (!patientFound) {
                            System.out.println("Patient not found after " + comparisons + " comparisons!");
                        }
                    }
                    else if (searchChoice == 2) {
                        System.out.print("Enter Patient Name: ");
                        String searchName = scanner.nextLine();

                        int comparisons = 0;
                        int foundCount = 0;
                        System.out.println("\nSearch Results:");
                        for (int i = 0; i < patients.size(); i++) {
                            comparisons++;
                            if (patients.get(i).getName().equalsIgnoreCase(searchName)) {
                                System.out.println(patients.get(i));
                                System.out.println("--------------------");
                                foundCount++;
                            }
                        }

                        System.out.println("Linear Search Statistics:");
                        System.out.println("Total patients: " + patients.size());
                        System.out.println("Comparisons made: " + comparisons);
                        System.out.println("Patients found: " + foundCount);
                        System.out.println("Time Complexity: O(n) where n = " + patients.size());

                        if (foundCount == 0) {
                            System.out.println("No patients found with name: " + searchName);
                        }
                    }
                    else {
                        System.out.println("Invalid choice!");
                    }
                    break;

                case 12:
                    System.out.println("\n=== SORT PATIENTS BY NAME (Bubble Sort Algorithm) ===");
                    if (patients.isEmpty()) {
                        System.out.println("No patients to sort!");
                        break;
                    }

                    System.out.println("Before sorting:");
                    for (int i = 0; i < Math.min(5, patients.size()); i++) {
                        System.out.println(patients.get(i).getName());
                    }

                    List<Patient> patientList = new ArrayList<>(patients);
                    int n = patientList.size();
                    int swapCount = 0;
                    int comparisonCount = 0;

                    System.out.println("\nSorting " + n + " patients using Bubble Sort...");

                    for (int i = 0; i < n - 1; i++) {
                        for (int j = 0; j < n - i - 1; j++) {
                            comparisonCount++;
                            if (patientList.get(j).getName().compareToIgnoreCase(patientList.get(j + 1).getName()) > 0) {
                                Patient temp = patientList.get(j);
                                patientList.set(j, patientList.get(j + 1));
                                patientList.set(j + 1, temp);
                                swapCount++;
                            }
                        }
                    }

                    patients.clear();
                    for (Patient p : patientList) {
                        patients.add(p);
                    }

                    System.out.println("\nSorting completed!");
                    System.out.println("Bubble Sort Statistics:");
                    System.out.println("Number of elements (n): " + n);
                    System.out.println("Total comparisons: " + comparisonCount);
                    System.out.println("Total swaps: " + swapCount);
                    System.out.println("Time Complexity: O(n²) = O(" + n + "²) = O(" + (n*n) + ")");
                    System.out.println("Space Complexity: O(1)");

                    System.out.println("\nAfter sorting (first 5 patients):");
                    for (int i = 0; i < Math.min(5, patients.size()); i++) {
                        System.out.println(patients.get(i).getName());
                    }
                    break;

                case 13:
                    System.out.println("\n=== VIEW NEXT APPOINTMENT (PriorityQueue Peek) ===");
                    if (priorityAppointments.isEmpty()) {
                        System.out.println("No appointments scheduled!");
                        break;
                    }

                    Appointment nextAppointment = priorityAppointments.peek();
                    System.out.println("Next appointment (earliest date/time):");
                    System.out.println(nextAppointment);

                    System.out.println("\nPriority Queue Statistics:");
                    System.out.println("Total appointments in priority queue: " + priorityAppointments.size());
                    System.out.println("Peek operation time complexity: O(1)");
                    break;

                case 14:
                    System.out.println("\nThank you for using Hospital Management System!");
                    System.out.println("Data Structures used: LinkedList, Queue, PriorityQueue");
                    System.out.println("Algorithms used: Linear Search, Bubble Sort");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 14);

        scanner.close();
    }
}

abstract class Person {
    protected String id;
    protected String name;

    Person(String name) {
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }

    @Override
    public abstract String toString();
}

class Patient extends Person {
    private int age;
    private String phone;
    private String address;
    private String medicalHistory;
    private LocalDateTime admissionDate;

    Patient(String name, int age, String phone, String address, String medicalHistory) {
        super(name);
        this.id = "PAT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.age = age;
        this.phone = phone;
        this.address = address;
        this.medicalHistory = medicalHistory;
        this.admissionDate = LocalDateTime.now();
    }

    public int getAge() { return age; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public String getMedicalHistory() { return medicalHistory; }
    public LocalDateTime getAdmissionDate() { return admissionDate; }

    @Override
    public String toString() {
        return "PATIENT DETAILS:\n" +
                "ID: " + id + "\n" +
                "Name: " + name + "\n" +
                "Age: " + age + "\n" +
                "Phone: " + phone + "\n" +
                "Address: " + address + "\n" +
                "Medical History: " + medicalHistory + "\n" +
                "Admission Date: " + admissionDate;
    }
}

class Doctor extends Person {
    private String specialization;
    private int experienceYears;
    private double consultationFee;

    Doctor(String name, String specialization, int experienceYears, double consultationFee) {
        super(name);
        this.id = "DOC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.specialization = specialization;
        this.experienceYears = experienceYears;
        this.consultationFee = consultationFee;
    }

    public String getSpecialization() { return specialization; }
    public int getExperienceYears() { return experienceYears; }
    public double getConsultationFee() { return consultationFee; }

    @Override
    public String toString() {
        return "DOCTOR DETAILS:\n" +
                "ID: " + id + "\n" +
                "Name: " + name + "\n" +
                "Specialization: " + specialization + "\n" +
                "Experience: " + experienceYears + " years\n" +
                "Consultation Fee: RS." + consultationFee;
    }
}

class Appointment implements Comparable<Appointment> {
    private String id;
    private Patient patient;
    private Doctor doctor;
    private String dateTime;
    private String reason;
    private int priority;

    Appointment(Patient patient, Doctor doctor, String dateTime, String reason, int priority) {
        this.id = "APT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.patient = patient;
        this.doctor = doctor;
        this.dateTime = dateTime;
        this.reason = reason;
        this.priority = priority;
    }

    public String getId() { return id; }
    public Patient getPatient() { return patient; }
    public Doctor getDoctor() { return doctor; }
    public String getDateTime() { return dateTime; }
    public String getReason() { return reason; }
    public int getPriority() { return priority; }

    @Override
    public int compareTo(Appointment other) {
        return this.dateTime.compareTo(other.dateTime);
    }

    @Override
    public String toString() {
        return "APPOINTMENT DETAILS:\n" +
                "ID: " + id + "\n" +
                "Patient: " + patient.getName() + " (" + patient.getId() + ")\n" +
                "Doctor: " + doctor.getName() + " (" + doctor.getId() + ")\n" +
                "Date/Time: " + dateTime + "\n" +
                "Priority: " + priority + " (1-High, 2-Medium, 3-Low)\n" +
                "Reason: " + reason;
    }
}

class Bill {
    private String id;
    private Patient patient;
    private double amount;
    private String services;
    private LocalDateTime generatedDate;

    Bill(Patient patient, double amount, String services) {
        this.id = "BILL-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.patient = patient;
        this.amount = amount;
        this.services = services;
        this.generatedDate = LocalDateTime.now();
    }

    public String getId() { return id; }
    public Patient getPatient() { return patient; }
    public double getAmount() { return amount; }
    public String getServices() { return services; }
    public LocalDateTime getGeneratedDate() { return generatedDate; }

    @Override
    public String toString() {
        return "BILL DETAILS:\n" +
                "ID: " + id + "\n" +
                "Patient: " + patient.getName() + " (" + patient.getId() + ")\n" +
                "Amount: RS." + amount + "\n" +
                "Services: " + services + "\n" +
                "Generated Date: " + generatedDate;
    }
}