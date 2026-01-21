package part2;

public class XDocumentReviewer implements DocumentReviewer {
    @Override
    public void review(Document document) {
        System.out.println("Reviewing document: " + document.getTitle());
        System.out.println("Document is approved!\n");
    }
}
