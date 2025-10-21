# Annuaire 3-tiers — Spring Boot + React + SQLite (Build intégré)

## Prérequis
- Java 17
- Maven 3.9+
- (Rien d'autre : le plugin Maven installe Node automatiquement)

## Lancer en développement rapide
```bash
# À la racine
mvn clean package
cd api && mvn spring-boot:run
# Frontend déjà compilé dans /static (servi par Spring Boot)
# Ouvre http://localhost:8080
```

## Détails
- Le frontend (Vite React) est dans `api/frontend`.
- Lors du `mvn package`, le `frontend-maven-plugin` :
  - installe Node v20,
  - exécute `npm ci`,
  - exécute `npm run build` qui exporte vers `api/src/main/resources/static`.
- Spring Boot sert automatiquement `/index.html` et les assets.
- La redirection SPA est gérée par `SpaController` (toutes routes non `/api/**`).

## Authentification admin
- Basic Auth (in-memory) : user=`admin`, pass=`admin123` (configurable dans `application.yml`).
- Endpoints admin : `/api/admin/**`
- Endpoints publics : `/api/public/**`

## SQLite
- Fichier DB : `api/data/annuaire.db` (créé automatiquement)
- URL JDBC : `jdbc:sqlite:./data/annuaire.db`
- Dialecte : `com.annuaire.api.config.SQLiteDialect`

## Swagger
- http://localhost:8080/swagger-ui/index.html

Bon dev !
