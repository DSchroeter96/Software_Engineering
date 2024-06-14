package providers.manganese;

import model.Book;
import model.Chapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import providers.BookProvider;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * A test suite for {@link ManganeseBookProvider}.
 * <p>
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * You MUST use this file to test {@link ManganeseBookProvider}
 * You CAN edit everything in this file
 * You MUST NOT rename or delete this file
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 */
final class ManganeseProviderTest {

    private MockManganeseApi api;
    private BookProvider provider;
    @BeforeEach
    void setup() {
        api = new MockManganeseApi();
        provider = new ManganeseBookProvider(api);
    }

    @Test
    void onePlusOneIsTwo() {
        assertThat(1 + 1, is(2));
    }

    @Test
    public void providerShouldThrowIllegalArgumentExceptionNullAPI() {
        assertThrows(IllegalArgumentException.class, () -> new ManganeseBookProvider(null));
    }

    @Test
    public void findBookByTitleShouldThrowIllegalArgumentExceptionNullTitle() {
        assertThrows(IllegalArgumentException.class, () -> provider.findBooksByTitle(null));
    }

    @Test
    public void findBookByTitleShouldReturnEmptyListNotFoundBook() {
        assertThat(provider.findBooksByTitle("Not existing"), is(empty()));
    }

    @Test
    public void findBookByTitleRetrnsCorrectBookExistingTitle() {
        ManganeseApi.Manga manga = api.mangasDb.get(0);
        List<ManganeseApi.MangaChapter> mangaChapters = api.mangaIdToChaptersId.get(manga.id()).stream().map(api.chaptersDb::get).toList();
        List<Chapter> chapters = mangaChapters.stream().map(c -> new Chapter(c.id(), c.title(), c.numberOfPages())).toList();
        Book book = new Book(manga.id(), manga.title(), manga.description(), chapters);

        assertThat(provider.findBooksByTitle(manga.title()).get(0), is(book));
    }

    @Test
    public void fetchPageThrowIllegalArgumentExceptionNullChapter() {
        assertThrows(IllegalArgumentException.class, () -> provider.fetchPage(null, 1));
    }

    @Test
    public void fetchPageThrowIllegalArgumentExceptionTooLowPageNb() {
        ManganeseApi.Manga manga = api.mangasDb.get(0);
        List<ManganeseApi.MangaChapter> mangaChapters = api.mangaIdToChaptersId.get(manga.id()).stream().map(api.chaptersDb::get).toList();
        List<Chapter> chapters = mangaChapters.stream().map(c -> new Chapter(c.id(), c.title(), c.numberOfPages())).toList();
        assertThrows(IllegalArgumentException.class, () -> provider.fetchPage(chapters.get(0), 0));
    }

    @Test
    public void fetchPageThrowIllegalArgumentExceptionTooHighPageNb() {
        ManganeseApi.Manga manga = api.mangasDb.get(0);
        List<ManganeseApi.MangaChapter> mangaChapters = api.mangaIdToChaptersId.get(manga.id()).stream().map(api.chaptersDb::get).toList();
        List<Chapter> chapters = mangaChapters.stream().map(c -> new Chapter(c.id(), c.title(), c.numberOfPages())).toList();
        assertThrows(IllegalArgumentException.class, () -> provider.fetchPage(chapters.get(0), 100));
    }

    @Test
    public void fetchPageThrowIllegalArgumentExceptionNonExistingChapter() {
        assertThrows(IllegalArgumentException.class, () -> provider.fetchPage(new Chapter("id", "title", 1), 1));
    }

    @Test
    public void fetchPageReturnsCorrectPageExistingChapterCorrectPageNb() {
        ManganeseApi.Manga manga = api.mangasDb.get(0);
        List<ManganeseApi.MangaChapter> mangaChapters = api.mangaIdToChaptersId.get(manga.id()).stream().map(api.chaptersDb::get).toList();
        List<Chapter> chapters = mangaChapters.stream().map(c -> new Chapter(c.id(), c.title(), c.numberOfPages())).toList();

        assertThat(provider.fetchPage(chapters.get(0), 1), is(chapters.get(0).id() + "1"));
    }
    
    @Test
    public void askForNameReturnsCorrectName() {
        assertThat(provider.name(), is("Manganese"));
    }
}
