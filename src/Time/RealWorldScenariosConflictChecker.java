package Time;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

public class RealWorldScenarios {
    public static void main(String[] args) {
        LocalDateTime t1 = LocalDateTime.of(2024, Month.MARCH, 15, 9,0);
        LocalDateTime t2 = LocalDateTime.of(2024, Month.MARCH, 15, 11,0);
        LocalDateTime t3 = LocalDateTime.of(2024, Month.MARCH, 15, 14,0);
        LocalDateTime proposedT = LocalDateTime.of(2024, 3, 15, 10, 45);

        boolean conflict = conflictChecker(t1, t2, t3, proposedT);
        System.out.println(conflict);

    }

    public static boolean conflictChecker(LocalDateTime t1, LocalDateTime t2, LocalDateTime t3, LocalDateTime proposedT) {
        LocalDateTime[] appointments = {t1, t2, t3};
       for (LocalDateTime existing : appointments) {

           // Check same date
           if (existing.toLocalDate().equals(proposedT.toLocalDate())) {
               long minutesDiff = Math.abs(Duration.between(existing, proposedT).toMinutes());
               if (minutesDiff <= 30) {
                   System.out.println("Conflict detected with appointment at: " + existing);
                   return true;
               }
           }
       }

       return false;
    }


}

/**
 * Key takeaway (burn this into memory)
 * ❌ Never compare times using getMinute() alone
 * ✅ Use Duration.between() for time differences
 * ✅ Think in terms of time distance, not individual fields
 */
