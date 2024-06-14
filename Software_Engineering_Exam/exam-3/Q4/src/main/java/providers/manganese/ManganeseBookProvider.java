package providers.manganese;

import model.Book;
import model.Chapter;
import providers.BookProvider;
import providers.manganese.exceptions.ChapterNotFoundException;
import providers.manganese.exceptions.MangaNotFoundException;
import providers.manganese.exceptions.PageNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Provider for the Manganese API, which provides various mangas.
 * <p>
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * You CAN change the bodies of existing methods and constructors
 * You CAN add new `private` members
 * You MUST NOT add interface implementations or new non-`private` members, unless explicitly instructed to do so
 * You MUST NOT edit the names, parameters, checked exceptions, or return types of existing members
 * You MUST NOT delete existing members
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 */
public class ManganeseBookProvider implements BookProvider {

    private static final String NAME = "Manganese";
    private final ManganeseApi api;

    /**
     * Return a new Manganese provider based on a given Api
     *
     * @param api Api that this provider will use
     */
    public ManganeseBookProvider(ManganeseApi api) {
        if (api == null) {
            throw new IllegalArgumentException();
        }
        this.api = api;
    }

    @Override
    public List<Book> findBooksByTitle(String title) {
        if (title == null) {
            throw new IllegalArgumentException("Title should not be null");
        }
        var list = new ArrayList<Book>();

        List<ManganeseApi.Manga> mangaList = this.api.searchManga(title);

        for (ManganeseApi.Manga manga : mangaList) {
            try {
                List<Chapter> chapters =
                        this.api.fetchChapters(manga.id())
                                .stream()
                                .map(mangaChapter ->
                                        new Chapter(mangaChapter.id(), mangaChapter.title(),
                                                mangaChapter.numberOfPages())).toList();

                list.add(new Book(manga.id(), manga.title(), manga.description(), chapters));
            } catch (MangaNotFoundException ignored) {}
        }

        return list;
    }

    @Override
    public String fetchPage(Chapter chapter, int pageNumber) {
        if (chapter == null) {
            throw new IllegalArgumentException("Chapter cannot be null");
        }
        if (pageNumber < 1 || pageNumber > chapter.numberOfPages()) {
            throw new IllegalArgumentException("page number is out of bound");
        }

        try {
            return this.api.fetchPage(chapter.id(), pageNumber);
        } catch (ChapterNotFoundException | PageNotFoundException e) {
            throw new IllegalArgumentException("The page cound not be found");
        }
    }

    @Override
    public String name() {
        return NAME;
    }
}
