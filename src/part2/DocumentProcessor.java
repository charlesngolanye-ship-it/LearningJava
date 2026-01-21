package part2;

public class DocumentProcessor {
    private DocumentEncoder encoder;
    private DocumentDatabase database;
    private DocumentReviewer reviewer;
    private NotificationService notificationService;

    public DocumentProcessor(DocumentEncoder encoder, DocumentDatabase database, DocumentReviewer reviewer, NotificationService notificationService) {
        this.encoder = encoder;
        this.database = database;
        this.reviewer = reviewer;
        this.notificationService = notificationService;
    }

    public void process(Document document) {
        encoder.encode(document);
        database.store(document);
        reviewer.review(document);
        notificationService.notify(document.getUser());
    }


}
