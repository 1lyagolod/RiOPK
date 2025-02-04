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

<img width="321" alt="image" src="https://github.com/user-attachments/assets/fae43ebb-e216-4815-bff9-53070bc5a4bd" />

Диаграмма развертывания программного средства

<img width="468" alt="image" src="https://github.com/user-attachments/assets/5405b9ac-65b5-497b-b512-20ce12157d83" />

Диаграмма последовательности регистрации страхового случая

<img width="468" alt="image" src="https://github.com/user-attachments/assets/aeed3a71-dd80-44e6-87c8-4c227a968977" />


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

Результат Junit-тестов

<img width="310" alt="image" src="https://github.com/user-attachments/assets/e7639c85-a1f5-4498-987b-7173cdd36ec8" />

В этом тесте проверяется функциональность сервисного слоя для работы с сущностью Content. Он включает несколько тестов для различных операций с контентом: успешное получение всех контентов (findAllSuccess), успешное получение контента по ID (findByIdSuccess), ситуация, когда контент по ID не найден (findByIdNotFound), успешное сохранение контента (saveSuccess), успешное обновление контента (updateSuccess), ситуация, когда обновляемый контент не найден (updateNotFound), успешное удаление контента (deleteSuccess) и ситуация, когда удаляемый контент не найден (deleteNotFound). Каждый тест использует Mockito для имитации поведения репозитория и проверки правильности вызовов, а также исключений в случае ошибок.

Пример интеграционного теста с использованием Spring Boot и Junit:

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.demo.model.InsurancePolicy;
import com.example.demo.service.InsurancePolicyService;
import com.example.demo.repository.InsurancePolicyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class InsurancePolicyControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InsurancePolicyRepository insurancePolicyRepository;

    @InjectMocks
    private InsurancePolicyService insurancePolicyService;

    private InsurancePolicy testPolicy;

    @BeforeEach
    public void setup() {
        // создаём тестовый страховой полис
        testPolicy = new InsurancePolicy(1L, "Test Policy", "2025-01-01", "Life Insurance", 500.0, "Active");
        insurancePolicyRepository.save(testPolicy); // сохраняем в базу данных для тестов
    }

    @Test
    void testCreateInsurancePolicy() throws Exception {
        InsurancePolicy newPolicy = new InsurancePolicy(null, "New Policy", "2025-02-01", "Car Insurance", 300.0, "Pending");

        mockMvc.perform(post("/api/policies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(newPolicy)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("New Policy")))
                .andExpect(jsonPath("$.startDate", is("2025-02-01")));
    }

    @Test
    void testGetInsurancePolicyById() throws Exception {
        mockMvc.perform(get("/api/policies/{id}", testPolicy.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(testPolicy.getName())))
                .andExpect(jsonPath("$.startDate", is(testPolicy.getStartDate())))
                .andExpect(jsonPath("$.type", is(testPolicy.getType())))
                .andExpect(jsonPath("$.premium", is(testPolicy.getPremium())))
                .andExpect(jsonPath("$.status", is(testPolicy.getStatus())));
    }

    @Test
    void testUpdateInsurancePolicy() throws Exception {
        InsurancePolicy updatedPolicy = new InsurancePolicy(testPolicy.getId(), "Updated Policy", "2025-03-01", "Home Insurance", 700.0, "Active");

        mockMvc.perform(put("/api/policies/{id}", testPolicy.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedPolicy)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Updated Policy")))
                .andExpect(jsonPath("$.startDate", is("2025-03-01")))
                .andExpect(jsonPath("$.type", is("Home Insurance")))
                .andExpect(jsonPath("$.premium", is(700.0)))
                .andExpect(jsonPath("$.status", is("Active")));
    }

    @Test
    void testDeleteInsurancePolicy() throws Exception {
        mockMvc.perform(delete("/api/policies/{id}", testPolicy.getId()))
                .andExpect(status().isNoContent());

        Optional<InsurancePolicy> deletedPolicy = insurancePolicyRepository.findById(testPolicy.getId());
        // Проверяем, что полис был удален
        assert deletedPolicy.isEmpty();
    }

    @Test
    void testInsurancePolicyNotFound() throws Exception {
        mockMvc.perform(get("/api/policies/{id}", 999L))
                .andExpect(status().isNotFound());
    }
}

Этот интеграционный тест проверяет работу API для управления контентом, включая создание, чтение, обновление и удаление контента, а также обработку ошибок, связанных с отсутствием контента. Тесты выполняются с использованием Spring Boot, JUnit и MockMvc, что позволяет эмулировать HTTP-запросы и проверять взаимодействие между различными компонентами системы.


<h1>Пользовательский интерфейс</h1>

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

<img width="468" alt="image" src="https://github.com/user-attachments/assets/947eabc7-6ceb-49b9-b259-af2ae912f9d0" />

Пример JWT

<img width="468" alt="image" src="https://github.com/user-attachments/assets/b71c1c67-d54c-4c86-a1ed-ff59e05278ec" />

Создание JWT

<img width="468" alt="image" src="https://github.com/user-attachments/assets/b02c3310-2391-4e9a-90af-29eef5b12aea" />

Конфигурация BCryptPasswordEncoder

<img width="468" alt="image" src="https://github.com/user-attachments/assets/5ec008c7-4c99-40aa-b9ca-899c61ad5147" />


<h1>Развертывание</h1>

Dockerfile для сервиса UserService.

FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

COPY --from=build /app/target/userservice-*.jar userservice.jar

ENV JAVA_OPTS="-Xms128m -Xmx256m"

CMD ["sh", "-c", "java $JAVA_OPTS -jar userservice.jar"]

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

FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/content-service-*.jar content-service.jar

ENV JAVA_OPTS="-Xms128m -Xmx256m"
EXPOSE 8080
CMD ["sh", "-c", "java $JAVA_OPTS -jar content-service.jar"]

Dockerfile для frondend части приложения

<img width="372" alt="image" src="https://github.com/user-attachments/assets/c073fadd-aa06-4ba9-990e-a090c9b3d846" />

Этот Dockerfile создаёт среду, в которой React-приложение может быть собрано и запущено. Оно использует Alpine-образ для минимизации размера контейнера, устанавливает все необходимые зависимости, создает сборку приложения, а затем запускает сервер для обслуживания запросов. 
Описание docker-compose.yaml для поднятия приложения

version: "3.7"
services:
 user_service:
   image: user/user-service
   build: ./backend/user-service
   restart: always
   ports:
     - 8080:8080
   networks:
     - postgres-net
   environment:
     - spring.datasource.url=jdbc:postgresql://postgresql:5432/mediacontent
   depends_on:
     - postgresql
   volumes:
     - .m2:/root/.m2
  content_service:
   image: user/mediacontent
   build: ./backend/mediacontent
   restart: always
   ports:
     - 8081:8081
   networks:
     - postgres-net
   environment:
     - spring.datasource.url=jdbc:postgresql://postgresql:5432/mediacontent?currentSchema=content_schema
   depends_on:
     - postgresql
   volumes:
     - .m2:/root/.m2
  frond_end:
   image: resource/frontend
   build: ./client
   restart: always
   ports:
     - 3000:3000

 postgresql:
   image: postgres
   restart: always
   ports:
     - 5432:5432
   networks:
     - postgres-net
   environment:
     POSTGRES_DB : mediacontent
     POSTGRES_USER :  postgres
     POSTGRES_PASSWORD : postgres
networks:
 postgres-net:

Этот Docker Compose файл определяет настройку окружающей среды для целого приложения, включающего несколько микросервисов и PostgreSQL в контейнерах Docker. 
version: 3.7 – определяет версию Docker Compose, которая используется для парсинга файла. Используется версия 3.7, которая совместима с различными функциями управления сетями и сервисами.
Описание сервисов:
1  user_service:
 image: user/user-service — используется как базовый образ.
 build: указывает на директорию ./backend/user-service, содержащую Dockerfile для user_service сервиса.
 restart: always — сервис будет автоматически перезапущен, если выйдет из строя.
 ports: перенаправление порта 8080 контейнера на хост-машину.
 networks: подключение к внутри-контейнерной сети postgres-net для связи с другими сервисами.
 environment: устанавливает подключение к базе данных через URL JDBC для PostgreSQL.
 depends_on: позвляет сервису ожидать доступности сервиса postgresql перед стартом.
 volumes: указывает том, чтобы сохранять/кешировать зависимости Maven локально, благодаря чему скорость сборки повышается, если зависимости не изменяются.
2 content_service:
Почти идентичен user_service, с отличиями в:
 build: путь к сборке сервиса - ./backend/paper-service.
 ports: прослушивание порта 8081.
 environment: URL содержит параметр подключения к схеме papers_schema.
3 frond_end:
 image: user/frontend — образ для фронтенд-приложения.
 build: путь ./client содержит файлы сборки для фронтенда.
 ports: экспонирование порта 3000 на хосте.
4 postgresql:
 image: postgres — официальный образ PostgreSQL.
 restart: always — для автоматического перезапуска.
 ports: ремаппинг порта 5432 (стандартный порт PostgreSQL).
 networks: подключение к сети postgres-net, что позволяет другим сервисам подключаться к этой базе данных.
 environment: настройка параметров подключения к базе:
 POSTGRES_DB: название создаваемой базы данных.
 POSTGRES_USER и POSTGRES_PASSWORD: учетные данные для доступа к базе.
Networks:
postgres-net: определение внутренней сети, обеспечивающей соединение между контейнерами. Использование сети необходимо для обеспечения ускоренной и защищенной связи между сервисами.
























