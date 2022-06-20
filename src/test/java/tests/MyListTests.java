package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListTests extends CoreTestCase {

    private static final String
            searchText = "Java",
            first_article = "Java (programming language)",
            name_of_folder = "JavaList",
            login = "GordienkoAI",
            password = "2511Qaz!";

    @Test
    public void testSaveFirstArticleToMyList() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchText);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        SearchPageObject.clickByArticleWithSubstring(first_article);

//---------------------Добавление первого элемента-------------------------------------------

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addFirstArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }

        if (Platform.getInstance().isMw()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAithButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();
            assertEquals("We are not on the same page after login",
                    first_article,
                    ArticlePageObject.getArticleTitle());

            ArticlePageObject.addArticleToMySaved();
        }

        ArticlePageObject.closeArticle();

//---------------------Удаление элемента-----------------------------------------------------

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyList();

        MyListPageObject MyListPageObject = MyListPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            MyListPageObject.openFolderByName(name_of_folder);
        }

        MyListPageObject.deleteArticleFromMyList(first_article);
    }


    //------------------------------------Ex17: Рефакторинг тестов после --------------------------------------------------------
    @Test
    public void testSaveTwoArticleToMyList() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        String searchText = "Ford",
                first_article = "Ford Mustang",
                second_article = "Ford GT",
                name_of_folder = "FordList";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchText);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        SearchPageObject.clickByArticleWithSubstring(first_article);

        //---------------------Добавление первого элемента-------------------------------------------
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addFirstArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }


        if (Platform.getInstance().isMw()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAithButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();
            assertEquals("We are not on the same page after login",
                    first_article,
                    ArticlePageObject.getArticleTitle());

            ArticlePageObject.addArticleToMySaved();
        }

        ArticlePageObject.closeArticle();

        //------------добавление второго элемента----------------------------------------------------

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchText);
        SearchPageObject.clickByArticleWithSubstring(second_article);

        if (Platform.getInstance().isMw()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAithButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();
            assertEquals("We are not on the same page after login",
                    second_article,
                    ArticlePageObject.getArticleTitle());

            ArticlePageObject.addArticleToMySaved();
        }


        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addNextArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }
        ArticlePageObject.closeArticle();

        //------------------Удаление элемента из списка-----------------------------------------------
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyList();

        MyListPageObject MyListPageObject = MyListPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            MyListPageObject.openFolderByName(name_of_folder);
        }

        MyListPageObject.deleteArticleFromMyList(first_article);


        //  Не выходя на главный экран, можно в существующем списке сделать поиск статьи которую ожидаем увидеть
        SearchPageObject.waitForSearchResult(second_article);
    }

}
