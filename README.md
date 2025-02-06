# RiOPK
С4 модель первого уровня

<img width="358" alt="image" src="https://github.com/user-attachments/assets/4e99cfb4-144c-4201-b785-aec0e3cc2527" />

Второй уровень С4- модели

<img width="590" alt="Снимок экрана 2025-02-04 в 18 42 55" src="https://github.com/user-attachments/assets/350351f5-2666-454e-aeb6-4d2a2ad1f3d0" />

С4- модель третьего уровня

<img width="468" alt="image" src="https://github.com/user-attachments/assets/46febd8e-84b9-4e5a-8b6c-9a8d174c4fb9" />

Диаграмма классов

<img width="450" alt="image" src="https://github.com/user-attachments/assets/b94c8d42-0d85-40dd-baa9-53a94591792c" />


<h1>Архитекутра</h1>

Реляционная схема базы данных

<img width="423" alt="image" src="https://github.com/user-attachments/assets/18b6564e-a2e6-47bd-927d-5e37fbe654d9" />

Схема алгоритма регистрации страхового случая

<img width="309" alt="image" src="https://github.com/user-attachments/assets/a9d1bce3-2288-4f65-af33-edc9d0dc6acf" />

Диаграмма последовательности регистрации страхового случая

<img width="468" alt="image" src="https://github.com/user-attachments/assets/53781035-da41-4ebf-acda-6596320feb34" />


<h1>Документация</h1>

Разработанная документация для сервиса UserService

<img width="471" alt="image" src="https://github.com/user-attachments/assets/4f2705d8-be3c-4bae-97b6-5941315c4760" />


<h1>Оценка качества</h1>

Результаты анализа

<img width="385" alt="image" src="https://github.com/user-attachments/assets/27fec749-d805-49d2-a1e6-606af1bfca7c" />

Конечный результат анализа

<img width="354" alt="image" src="https://github.com/user-attachments/assets/e497d0c0-cc61-4c27-a82c-bee4307bd93e" />

Оценка кода по метрике надежность

<img width="468" alt="image" src="https://github.com/user-attachments/assets/8019703e-360f-4d75-9f0c-ffb62f783962" />

Оценка кода по метрике безопасность

<img width="468" alt="image" src="https://github.com/user-attachments/assets/bff9ecf0-031c-43eb-8dd6-e04884083ea3" />

Оценка кода по метрике поддерживаемость

<img width="468" alt="image" src="https://github.com/user-attachments/assets/6dfac94c-9148-4804-b426-54242ee62ee6" />


<h1>Тестирование</h1>

package com.app.policy;

import com.app.system.exception.ObjectNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PolicyTest {

    @Mock
    private PolicyRepository repository;
    @InjectMocks
    private PolicyService service;

    List<Policy> policies = new ArrayList<>();

    @BeforeEach
    void setUp() {
        policies.add(new Policy(1L, "name1", "date1", 9, 9.99f, 9.99f, "description1", "file1"));
        policies.add(new Policy(2L, "name2", "date2", 19, 19.99f, 19.99f, "description2", "file2"));
        policies.add(new Policy(3L, "name3", "date3", 19, 19.99f, 19.99f, "description3", "file3"));
    }

    @AfterEach
    void tearDown() {
        policies.clear();
    }

    @Test
    void findAllSuccess() {
        given(repository.findAll()).willReturn(policies);

        List<Policy> actualWizards = service.findAllForTest();

        assertThat(actualWizards.size()).isEqualTo(policies.size());

        verify(repository, times(1)).findAll();
    }

    @Test
    void findByIdSuccess() {
        Policy policy = policies.get(0);

        given(repository.findById(1L)).willReturn(Optional.of(policy));

        Policy find = service.findForTest(1 + "");

        assertThat(find.getId()).isEqualTo(1);
        assertThat(find.getName()).isEqualTo(policy.getName());
        assertThat(find.getDate()).isEqualTo(policy.getDate());
        assertThat(find.getView()).isEqualTo(policy.getView());
        assertThat(find.getCtr()).isEqualTo(policy.getCtr());
        assertThat(find.getRoi()).isEqualTo(policy.getRoi());
        assertThat(find.getDescription()).isEqualTo(policy.getDescription());
        assertThat(find.getFile()).isEqualTo(policy.getFile());

        verify(repository, times(1)).findById(1L);
    }

    @Test
    void findByIdNotFound() {
        given(repository.findById(Mockito.any(Long.class))).willReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> service.findForTest(1 + ""));

        verify(repository, times(1)).findById(1L);
    }

    @Test
    void saveSuccess() {
        Policy save = policies.get(0);

        given(repository.save(save)).willReturn(save);

        Policy saved = service.saveForTest(save);

        assertThat(saved.getName()).isEqualTo(save.getName());
        assertThat(saved.getDate()).isEqualTo(save.getDate());
        assertThat(saved.getView()).isEqualTo(save.getView());
        assertThat(saved.getCtr()).isEqualTo(save.getCtr());
        assertThat(saved.getRoi()).isEqualTo(save.getRoi());
        assertThat(saved.getDescription()).isEqualTo(save.getDescription());
        assertThat(saved.getFile()).isEqualTo(save.getFile());

        verify(repository, times(1)).save(save);
    }

    @Test
    void updateSuccess() {
        Policy old = policies.get(0);
        Policy update = policies.get(1);

        given(repository.findById(1L)).willReturn(Optional.of(old));
        given(repository.save(old)).willReturn(old);

        Policy updated = service.updateForTest(1 + "", update);

        assertThat(updated.getId()).isEqualTo(1);
        assertThat(updated.getName()).isEqualTo(update.getName());
        assertThat(updated.getDate()).isEqualTo(update.getDate());
        assertThat(updated.getView()).isEqualTo(update.getView());
        assertThat(updated.getCtr()).isEqualTo(update.getCtr());
        assertThat(updated.getRoi()).isEqualTo(update.getRoi());
        assertThat(updated.getDescription()).isEqualTo(update.getDescription());
        assertThat(updated.getFile()).isEqualTo(update.getFile());

        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(old);
    }

    @Test
    void updateNotFound() {
        Policy update = policies.get(1);

        given(repository.findById(1L)).willReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> service.updateForTest(1 + "", update));

        verify(repository, times(1)).findById(1L);
    }

    @Test
    void deleteSuccess() {
        Policy wizard = policies.get(0);

        given(repository.findById(1L)).willReturn(Optional.of(wizard));
        doNothing().when(repository).deleteById(1L);

        service.deleteForTest(1 + "");

        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void deleteNotFound() {
        given(repository.findById(1L)).willReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> service.deleteForTest(1 + ""));

        verify(repository, times(1)).findById(1L);
    }


}

Результат Junit-тестов

<img width="468" alt="image" src="https://github.com/user-attachments/assets/74ab2769-4040-4802-82a1-1c4306654eba" />

В этом тесте проверяется функциональность сервисного слоя для работы с сущностью Content. Он включает несколько тестов для различных операций с контентом: успешное получение всех контентов (findAllSuccess), успешное получение контента по ID (findByIdSuccess), ситуация, когда контент по ID не найден (findByIdNotFound), успешное сохранение контента (saveSuccess), успешное обновление контента (updateSuccess), ситуация, когда обновляемый контент не найден (updateNotFound), успешное удаление контента (deleteSuccess) и ситуация, когда удаляемый контент не найден (deleteNotFound). Каждый тест использует Mockito для имитации поведения репозитория и проверки правильности вызовов, а также исключений в случае ошибок.

Пример интеграционного теста с использованием Spring Boot и Junit:

package com.app.riskanalysis;

import com.app.system.exception.ObjectNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class RiskAnalysisIntegrationTest {

    @Autowired
    private RiskAnalysisRepository repository;

    @Autowired
    private RiskAnalysisService service;

    private RiskAnalysis riskAnalysis1;
    private RiskAnalysis riskAnalysis2;
    private RiskAnalysis riskAnalysis3;

    @BeforeEach
    void setUp() {
        // Настроим объекты перед каждым тестом
        riskAnalysis1 = new RiskAnalysis(1L, "target1", "deadline1", "file1");
        riskAnalysis2 = new RiskAnalysis(2L, "target2", "deadline2", "file2");
        riskAnalysis3 = new RiskAnalysis(3L, "target3", "deadline3", "file3");

        repository.save(riskAnalysis1);
        repository.save(riskAnalysis2);
        repository.save(riskAnalysis3);
    }

    @AfterEach
    void tearDown() {
        // Очистим базу данных после каждого теста
        repository.deleteAll();
    }

    @Test
    void findAllSuccess() {
        List<RiskAnalysis> actualWizards = service.findAllForTest();

        assertThat(actualWizards).hasSize(3);
    }

    @Test
    void findByIdSuccess() {
        RiskAnalysis find = service.find("1");

        assertThat(find.getId()).isEqualTo(1);
        assertThat(find.getTarget()).isEqualTo(riskAnalysis1.getTarget());
        assertThat(find.getDeadline()).isEqualTo(riskAnalysis1.getDeadline());
        assertThat(find.getFile()).isEqualTo(riskAnalysis1.getFile());
    }

    @Test
    void findByIdNotFound() {
        assertThrows(ObjectNotFoundException.class, () -> service.find("999"));
    }

    @Test
    void saveSuccess() {
        RiskAnalysis newRisk = new RiskAnalysis(null, "target4", "deadline4", "file4");

        RiskAnalysis saved = service.saveForTest(newRisk);

        assertThat(saved.getTarget()).isEqualTo(newRisk.getTarget());
        assertThat(saved.getDeadline()).isEqualTo(newRisk.getDeadline());
        assertThat(saved.getFile()).isEqualTo(newRisk.getFile());
    }

    @Test
    void updateSuccess() {
        RiskAnalysis update = new RiskAnalysis(1L, "updatedTarget", "updatedDeadline", "updatedFile");

        RiskAnalysis updated = service.updateForTest("1", update);

        assertThat(updated.getId()).isEqualTo(1);
        assertThat(updated.getTarget()).isEqualTo(update.getTarget());
        assertThat(updated.getDeadline()).isEqualTo(update.getDeadline());
        assertThat(updated.getFile()).isEqualTo(update.getFile());
    }

    @Test
    void updateNotFound() {
        RiskAnalysis update = new RiskAnalysis(999L, "updatedTarget", "updatedDeadline", "updatedFile");

        assertThrows(ObjectNotFoundException.class, () -> service.updateForTest("999", update));
    }

    @Test
    void deleteSuccess() {
        service.deleteForTest("1");

        assertThrows(ObjectNotFoundException.class, () -> service.find("1"));
    }

    @Test
    void deleteNotFound() {
        assertThrows(ObjectNotFoundException.class, () -> service.deleteForTest("999"));
    }
}

Этот интеграционный тест проверяет работу API для управления контентом, включая создание, чтение, обновление и удаление контента, а также обработку ошибок, связанных с отсутствием контента. Тесты выполняются с использованием Spring Boot, JUnit и MockMvc, что позволяет эмулировать HTTP-запросы и проверять взаимодействие между различными компонентами системы.


<h1>Пользовательский интерфейс</h1>

<h2> User-flow диаграммы</h2>

Диаграмма user flow для роли «Управляющий»

<img width="468" alt="image" src="https://github.com/user-attachments/assets/d85c1e43-9c2e-4e2f-b02c-26a5f35bd7ca" />

Диаграмма user flow для роли «Клиент»

<img width="468" alt="image" src="https://github.com/user-attachments/assets/82b2fba2-7c1f-4a2a-bfb6-6f90f874ab4c" />

Диаграмма user flow для роли «Страховой агент»

<img width="468" alt="image" src="https://github.com/user-attachments/assets/4f9c917d-d264-47bb-8b21-441383edd13b" />

<h2>Примеры экранов UI</h2>

Страница авторизации в системе

<img width="386" alt="image" src="https://github.com/user-attachments/assets/e4bfd914-287a-47c0-a53f-4696ba362ef7" />

Страница регистрации

<img width="381" alt="image" src="https://github.com/user-attachments/assets/63b54fdd-6dc2-4db5-97fb-b2fe1f732738" />

Главная страница 

<img width="428" alt="image" src="https://github.com/user-attachments/assets/851202ff-66c4-4e37-9550-d4e22044efcb" />

Страница с видами страхования

<img width="454" alt="image" src="https://github.com/user-attachments/assets/057f8901-dcdb-4961-86b7-28654379c587" />

Страница профиля клиента

<img width="429" alt="image" src="https://github.com/user-attachments/assets/55c013e3-be53-4fb0-b3d8-fccee11ca0f7" />

Страница работы управляющего

<img width="376" alt="image" src="https://github.com/user-attachments/assets/fb33aa22-b5a8-4377-9360-d01cea28c3ca" />

Страница обращения к страховому агенту

<img width="404" alt="image" src="https://github.com/user-attachments/assets/0acf7b16-f704-4492-9f19-5a54199736b1" />


<h1>Безопасность</h1>

Конфигурация SecurityFilterChain
https://github.com/1lyagolod/RiOPK/blob/main/back/src/main/java/com/app/security/SecurityConfiguration.java
<img width="468" alt="image" src="https://github.com/user-attachments/assets/947eabc7-6ceb-49b9-b259-af2ae912f9d0" />

Создание JWT
https://github.com/1lyagolod/RiOPK/blob/main/back/src/main/java/com/app/security/JwtProvider.java
<img width="468" alt="image" src="https://github.com/user-attachments/assets/b02c3310-2391-4e9a-90af-29eef5b12aea" />

Конфигурация BCryptPasswordEncoder
https://github.com/1lyagolod/RiOPK/blob/main/back/src/main/java/com/app/security/SecurityConfiguration.java
<img width="434" alt="image" src="https://github.com/user-attachments/assets/a55d3c45-aa3f-4dd9-a89c-46e7b1bf2168" />


<h1>Развертывание</h1>

Dockerfile для сервиса UserService.
https://github.com/1lyagolod/RiOPK/blob/main/back/src/Dockerfile
<img width="468" alt="image" src="https://github.com/user-attachments/assets/95787ec8-85ad-4be6-b52e-28bb29420e84" />

Этот Dockerfile описывает процесс сборки и запуска Java приложения, используя Maven для сборки и OpenJDK для выполнения. 
Первая фаза: FROM maven:3.9.8-eclipse-temurin-21 as maven-builder
Это стадия использует образ Maven, который включает Java 21 от Eclipse Temurin. Имя этой стадии – maven-builder. Используя многоэтапную сборку, минимизируется размер итогового образа, копируя только необходимые артефакты.
Копирование исходников и POM файла:
COPY src /app/src
COPY pom.xml /app
Исходный код приложения и файл pom.xml копируются в директорию /app в контейнере. Это нужно для того, чтобы Maven мог выполнить сборку проекта.Рабочая директория и сборка с помощью Maven:
WORKDIR /app
RUN mvn clean install -U -DskipTests
Устанавливается рабочая директория /app. Команда mvn clean install устанавливает все зависимости, компилирует код, упаковывает проект в JAR-файл, но пропускает тесты благодаря -DskipTests.
Вторая фаза: Java Runtime Environment
Использование OpenJDK для выполнения:
FROM openjdk:21
Эта стадия основывается на образе OpenJDK 21. Здесь мы будем только запускать наше приложение, без всего лишнего, что используется в стадии сборки.
Копирование артефакта:
COPY --from=maven-builder /app/target /user-service-1.0.0-SNAPSHOT.jar /app/app.jar
Готовый JAR-файл копируется из предыдущей стадии в новую директорию /app под именем app.jar.
Рабочая директория:
WORKDIR /app
Устанавливает рабочую директорию на /app.
Открытие порта:
EXPOSE 8080
Указание Docker, что контейнер будет слушать на порту 8080. Это типично для Spring Boot приложений.
Команда для запуска:
CMD ["java", "-jar", "app.jar"]
Устанавливает команду, которая запускается при старте контейнера. В данном случае, это выполнение JAR-файла приложения с помощью java -jar.
Dockerfile для сервиса paper-service схож по своей структуре с файлом для user-service. 
Ниже приведен Dockerfile для сервиса PolicyService.
https://github.com/1lyagolod/RiOPK/blob/main/back/src/Dockerfile
<img width="468" alt="image" src="https://github.com/user-attachments/assets/c31e8012-64b5-47f0-9ac9-defd7a8fceee" />

Dockerfile для frondend части приложения
https://github.com/1lyagolod/RiOPK/blob/main/back/src/Dockerfile
<img width="468" alt="image" src="https://github.com/user-attachments/assets/c9e6a9ef-8720-4d49-82cf-5e651b959098" />

Этот Dockerfile создаёт среду, в которой React-приложение может быть собрано и запущено. Оно использует Alpine-образ для минимизации размера контейнера, устанавливает все необходимые зависимости, создает сборку приложения, а затем запускает сервер для обслуживания запросов. 
Описание docker-compose.yaml для поднятия приложения
https://github.com/1lyagolod/RiOPK/blob/main/back/src/docker-compose.yml
<img width="468" alt="image" src="https://github.com/user-attachments/assets/82d980b7-e03e-4a4b-972d-5adf1c9441db" />

Этот Docker Compose файл определяет настройку окружающей среды для целого приложения, включающего несколько микросервисов и PostgreSQL в контейнерах Docker. 
version: 3.7 – определяет версию Docker Compose, которая используется для парсинга файла. Используется версия 3.7, которая совместима с различными функциями управления сетями и сервисами.
Описание сервисов:
1  user_service:
image: user/user-service — используется как базовый образ.
build: указывает на директорию ./backend/user-service, содержащую Dockerfile для user_service сервиса.
restart: always — сервис будет автоматически перезапущен, если выйдет из строя.
ports: перенаправление порта 8080 контейнера на хост-машину.
networks: подключение к внутри-контейнерной сети postgres-net для связи с другими сервисами.
environment: устанавливает подключение к базе данных через URL JDBC для PostgreSQL.
depends_on: позвляет сервису ожидать доступности сервиса postgresql перед стартом.
volumes: указывает том, чтобы сохранять/кешировать зависимости Maven локально, благодаря чему скорость сборки повышается, если зависимости не изменяются.
2 policy_service:
Почти идентичен user_service, с отличиями в:
build: путь к сборке сервиса - ./backend/paper-service.
ports: прослушивание порта 8081.
environment: URL содержит параметр подключения к схеме papers_schema.
3 frond_end:
image: user/frontend — образ для фронтенд-приложения.
build: путь ./client содержит файлы сборки для фронтенда.
ports: экспонирование порта 3000 на хосте.
4 postgresql:
image: postgres — официальный образ PostgreSQL.
restart: always — для автоматического перезапуска.
ports: ремаппинг порта 5432 (стандартный порт PostgreSQL).
networks: подключение к сети postgres-net, что позволяет другим сервисам подключаться к этой базе данных.
environment: настройка параметров подключения к базе:
POSTGRES_DB: название создаваемой базы данных.
POSTGRES_USER и POSTGRES_PASSWORD: учетные данные для доступа к базе.
Networks:
postgres-net: определение внутренней сети, обеспечивающей соединение между контейнерами. Использование сети необходимо для обеспечения ускоренной и защищенной связи между сервисами.


<h1>Руководство пользователя</h1>

После успешного запуска веб-приложения, пользователь встречается со страницей авторизации (рисунок 1), на которой пользователю доступна возможность войти, при отсутсвии аккаунта (если, например, это клиент) зарегистрироваться.

Рисунок 1 – Страница авторизации

<img width="441" alt="image" src="https://github.com/user-attachments/assets/c00aa779-b40a-404b-a1c1-831a75c4a2b3" />

Также как уже было сказано можно зарегистрировать новый аккаунт. После нажатия на кнопку регистрации открывается страница регистрации (рисунок 2).

Рисунок 2 – Страница регистрации пользователя![image](https://github.com/user-attachments/assets/a53a930b-f70f-4cf4-8757-c86c282ec1d3)

<img width="381" alt="image" src="https://github.com/user-attachments/assets/26fab4a7-fd7f-47c1-8f31-48c6b39ab2ed" />

Здесь необходимо ввести логин, пароль и подтвердить пароль. После чего пользователя перекидывает на страницу. На рисунке 3 представлена главная страница.

Рисунок 3 – Главная страница

<img width="439" alt="image" src="https://github.com/user-attachments/assets/afa2cfc5-66ae-446d-b120-cb851929866f" />

На главной странице можно ознакомится с основной информацией про страхование и сайт. Пример страницы с видами страхования представ-лен на рисунке 4.

Рисунок 4 – Страница с видами страхования

<img width="454" alt="image" src="https://github.com/user-attachments/assets/f9bd9e34-b1ca-4bd0-9bee-438351c87f11" />

Здесь можно просмотреть доступные виды страхования, дополнитель-но можно перейти на каждый вид и прочитать подробнее про каждый вид. Пример страницы с профилем клиента представлен на рисунке 5.

Рисунок 5 – Страница статистики проекта

<img width="429" alt="image" src="https://github.com/user-attachments/assets/57285f6e-7187-41f8-94e4-f35ccfc73d08" />

Здесь отображается основной функционал клиента, где доступен его баланс, долги. Просмотреть подробно по каждому страхованию.
Пример страницы работы управляющего представлен на рисунке 6.

Рисунок 6 – Страница работы управляющего

<img width="414" alt="image" src="https://github.com/user-attachments/assets/b3fda2c3-cc13-4d48-b2e9-43e7b5f7290d" />

У управляющего доступен просмотр графиков организации, где за-тронуты основные метрики, также у управляющего есть доступ к ролям профилей, которые он может изменять. Главная страница за клиента приве-дена на странице 7.

Рисунок 7 – Страница обращения к страховым агента

<img width="425" alt="image" src="https://github.com/user-attachments/assets/2dc089a3-5162-484f-b4ea-39faa2fadf94" />

На данной странице, клиент может подать обращение к страховым агентам, введя имя и почту, и нажать на кнопку «Оставить заявку». После чего обращение будет страховому агенту.

Таким образом, были описаны основные экраны пользовательского интерфейса, составлено руководство пользователя.

































