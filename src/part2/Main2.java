package part2;

public class Main2 {
  public static void main(String[] args) {
      var doc = new Document();
      doc.setFileName("report.pdf");
      doc.setTitle("Q1 Report");
      doc.setUser(new User("alex@example.com"));

      var processor = new DocumentProcessor(new XDocumentEncoder(), new SqlDocumentDatabase(), new XDocumentReviewer(), new EmailService());
      processor.process(doc);
  }

}
