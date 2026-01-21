package part2;

public class EmailService implements NotificationService {
    @Override
    public void notify(User user){
        System.out.println("Sending email to: " + user.getEmail());
    }
}
