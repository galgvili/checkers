public class Book
{

    private String Title;
    private String AuthorFirstName;
    private String AuthorLastName;

    public Book(String title, String authorFirstName, String authorLastName) {
        Title = title;
        AuthorFirstName = authorFirstName;
        AuthorLastName = authorLastName;
    }

    public String getTitle() {
        return Title;
    }

    public String getAuthorFirstName() {
        return AuthorFirstName;
    }

    public String getAuthorLastName() {
        return AuthorLastName;
    }
}
