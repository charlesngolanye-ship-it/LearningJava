package part2;

public class SqlDocumentDatabase implements DocumentDatabase {
    @Override
    public void store(Document document) {
        System.out.println("Storing document: " + document.getTitle());
    }
}
