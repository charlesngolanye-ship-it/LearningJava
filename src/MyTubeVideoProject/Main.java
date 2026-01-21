package MyTubeVideoProject;

public class Main {
    public static void main(String[] args) {
        var video = new Video();
        video.setFileName("birthday.mp4");
        video.setTitle("Jennifer's birthday");
        video.setUser(new User("john@domain.com"));

        var processor = new VideoProcessor(new VideoEncoder(), new EmailService(), new SqlVideoDatabase());
        processor.process(video);
    }
}
