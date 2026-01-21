package MyTubeVideoProject;

public class VideoProcessor {
    private Encoding encoder;
    private NotificationService notificationService;
    private DBStorage database;

    public VideoProcessor(Encoding encoder,
                          NotificationService notificationService,
                          DBStorage database) {
        this.encoder = encoder;
        this.notificationService = notificationService;
        this.database = database;
    }


    public void process(Video video) {
        encoder.encode(video);
        database.store(video);
        notificationService.sendEmail(video.getUser());
    }
}
