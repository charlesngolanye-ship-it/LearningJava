public class Employee {

    private int baseSalary;

    private int hourlyRate;

    public static int numberOfEmployees;

    public Employee(int baseSalary){
        this(baseSalary, 0); //use "this" and parenthesis to call the constructor as reference to the current constructor.
    }

    public Employee(int baseSalary, int hourlyRate){
        setBaseSalary(baseSalary);
        setHourlyRate(hourlyRate);
        numberOfEmployees++;
    }

    public static void printNumberOfEmployees(){
        System.out.println(numberOfEmployees);
    }

    public int calculateWage(int extraHours){
            return baseSalary + (hourlyRate * extraHours);
        }

    public int calculateWage(){
        return calculateWage(0);
    }

    private void setBaseSalary(int baseSalary) {
        if (baseSalary <= 0)
            throw new IllegalArgumentException("Salary cannot be 0 or less");
        this.baseSalary = baseSalary;
    }

    private int getBaseSalary() {
        return baseSalary;
    }

    private int getHourlyRate() {
        return hourlyRate;
    }

    private void setHourlyRate(int hourlyRate) {
        if (hourlyRate < 0)
            throw new IllegalArgumentException("Rate cannot be 0.");
        this.hourlyRate = hourlyRate;
    }

}
