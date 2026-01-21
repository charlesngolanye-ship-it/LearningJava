package part2;

public class XDocumentEncoder implements DocumentEncoder {
    @Override
    public void encode(Document document) {
        System.out.println("Encoding document: " + document.getFileName());
    }
}
