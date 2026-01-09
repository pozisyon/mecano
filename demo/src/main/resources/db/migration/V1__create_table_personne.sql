CREATE TABLE personne (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          nom VARCHAR(100) NOT NULL,
                          prenom VARCHAR(100) NOT NULL,
                          email VARCHAR(150) NOT NULL UNIQUE,
                          adresse VARCHAR(255),
                          age INT,
                          telephone VARCHAR(20)
);
