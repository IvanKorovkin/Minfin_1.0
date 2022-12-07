package TestRolesModel;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.LogType;
import org.junit.jupiter.api.*;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import pageObject.MainPage;
import pageObject.WorkingPanelPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@Story("Проверка ролевой модели куратора")
public class CuratorTest {

    @BeforeAll
    public static void setUp() {
        Configuration.timeout = 30000;
    }

    @Order(1)
    @Test
    @DisplayName("Доступ к реестру \"Перечень заявок\"")
    public void testAccessToListOfApplicationCurator() {
        new MainPage()
                .entranceToMainPage()
                .entranceButtonClick()
                .enterAdminName()
                .enterAdminPassword()
                .clickEntranceButton()
                .moveCursorToAvatar()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryIdentity()
                .openSystemRolesFilter()
                .selectInSystemRolesFilterUsers()
                .editSystemRoleOfFirstUserInList()
                .enterNewSystemRoleInModalWindow("Куратор")
                .closeModalWindowOfChangeSystemRole()
                .clickButtonManagementFirstUser()
                .clickButtonAuthUser()
                .enterAdminPasswordIdentity()
                .confirmIdentity()
                .moveToAvatar()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryListOfApplication()
                .checkNameOfRegistry("Перечень заявок");
    }

    @Order(2)
    @Test
    @DisplayName("Доступ к реестру \"Экспертный совет\"")
    public void testAccessToOESCurator() {
        new MainPage()
                .moveCursorToAvatarOnMainPage()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryOES()
                .checkNameOfRegistry("Экспертный совет");
    }

    @Order(3)
    @Test
    @DisplayName("Доступ к реестру \"Координационный комитет\"")
    public void testAccessToCoordinatingCommitteeCurator() {
        new MainPage()
                .moveCursorToAvatarOnMainPage()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryCoordinatingCommittee()
                .checkNameOfRegistry("Координационный комитет");
    }

    @Order(4)
    @Test
    @DisplayName("Доступ к реестру \"Договоры\"")
    public void testAccessToAgreementCurator() {
        new MainPage()
                .moveCursorToAvatarOnMainPage()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryAgreement()
                .checkNameOfRegistry("Договоры");
    }

    @Order(5)
    @Test
    @DisplayName("Доступ к реестру \"Отчетность по этапам\"")
    public void testAccessToReportCurator() {
        new MainPage()
                .moveCursorToAvatarOnMainPage()
                .clickToWorkingPanelInUsersMenu()
                .entranceToRegistryReport()
                .checkNameOfRegistry("Отчетность по этапам");
    }

    @Order(6)
    @Test
    @DisplayName("Отсутствие доступа к реестру \"Эксперты\"")
    public void testAccessDeniedToExpertsCurator() {
        new WorkingPanelPage()
                .followTheLinkExperts()
                .checkInaccessibilityRegistryForThisRole();
    }

    @Order(7)
    @Test
    @DisplayName("Отсутствие доступа к реестру \"Экспертиза\"")
    public void testAccessDeniedToExpertiseCurator() {
        new WorkingPanelPage()
                .followTheLinkExpertise()
                .checkInaccessibilityRegistryForThisRole();
    }

    @Order(8)
    @Test
    @DisplayName("Отсутствие доступа к реестру \"Договоры и акты с экспертами\"")
    public void testAccessDeniedToAgreementExpertCurator() {
        new WorkingPanelPage()
                .followTheLinkAgreementExpert()
                .checkInaccessibilityRegistryForThisRole();
    }

    @Order(9)
    @Test
    @DisplayName("Отсутствие доступа к реестру \"Рассмотрение итогов\"")
    public void testAccessDeniedToOESAdminCurator() {
        new WorkingPanelPage()
                .followTheLinkOESAdmin()
                .checkInaccessibilityRegistryForThisRole();
    }

    @AfterEach
    void getLogs() {
        LogEntries browserLogs = WebDriverRunner.getWebDriver().manage().logs().get(String.valueOf(LogType.BROWSER));

        for (LogEntry log: browserLogs) {
            Allure.addAttachment("Сообщения в консоли браузера", log.getMessage());
        }
    }

    @AfterAll
    public static void quitBrowser() {
        Selenide.closeWebDriver();
    }

}
