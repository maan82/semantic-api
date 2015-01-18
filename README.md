 README
========

## Requisites
- Java
- Gradle
- Preferred IDE/Text editor
- Internet connection

## Basic commands
- `$ ./gradlew test`: run tests
- `$ ./gradlew build`: builds the project
- `$ java -jar build/libs/semantic-api-0.1.0.jar`: runs the server

Point your browser to [http://localhost:8080/](http://localhost:8080/) to get api home page. Home page shows you that path for people feed is /people and to get it point you browser to [http://localhost:8080/people](http://localhost:8080/people). It is navigable api(HATEOAS based) built around the concept of filters and mixins. On each response you get what are the other options, filters and mixins available on this resource.

##Examples
- How old is Tony Blair ?  point your browser to [http://localhost:8080/people?name=Tony Blair&mixin=age](http://localhost:8080/people?name=Tony Blair&mixin=age)
- Where was David Cameron born ?  point your browser to [http://localhost:8080/people?name=David Cameron&mixin=birth_place](http://localhost:8080/people?name=David Cameron&mixin=birth_place)
- Where was David Cameron born and how old is he ?  point your browser to [http://localhost:8080/people?name=David Cameron&mixin=birth_place&mixin=age](http://localhost:8080/people?name=David Cameron&mixin=birth_place&mixin=age)
- Filter and mixins on each resource. point your browser to [http://localhost:8080/people](http://localhost:8080/people) and see filters and mixins block at the bottom.