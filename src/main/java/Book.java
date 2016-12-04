//import fr.alliancesoftware.json.JsObject;
//import fr.alliancesoftware.json.Json;
//import fr.alliancesoftware.json.mapping.Writer;
//
//import static fr.alliancesoftware.json.Syntax.$;
//
//public class Book {
//    private String title;
//    private String subTitle;
//    private String autor;
//    private String artiste;
//
//    public Book(String title, String subTitle, String autor, String artiste) {
//        this.title = title;
//        this.subTitle = subTitle;
//        this.autor = autor;
//        this.artiste = artiste;
//    }
//
//    public String getSubTitle() {
//        return subTitle;
//
//    }
//
//    public String getAutor() {
//        return autor;
//    }
//
//    public String getArtiste() {
//        return artiste;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public static Writer<Book> clientWriter = book -> Json.obj(
//            $("title", book.getTitle()),
//            $("subTitle", book.getSubTitle()),
//            $("autor", book.getAutor()),
//            $("artiste", book.getArtiste())
//    );
//
//    public JsObject toJson() {
//        return clientWriter.write(this).asObject();
//    }
//}
