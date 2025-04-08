create database examen_depense;
use examen_depense;
create table credit (
    id int primary key auto_increment,
    libelle varchar(255) not null,
    montant DECIMAL(15,2) NOT NULL DEFAULT 0.00,
    reste DECIMAL(15,2) NOT NULL DEFAULT 0.00
);

create table depense (
    id int primary key auto_increment,
    idCredit INT,
    depense DECIMAL(15,2) NOT NULL DEFAULT 0.00,
    FOREIGN KEY (idCRedit) REFERENCES credit(id)
);




-- Insertion de credit
INSERT INTO credit (libelle, montant, reste) VALUES ('Frais bus', 10000.00, 10000.00);
INSERT INTO credit (libelle, montant, reste) VALUES ('Nourriture', 10000.00, 10000.00);
INSERT INTO credit (libelle, montant, reste) VALUES ('carburant', 50000.00, 50000.00 );
INSERT INTO credit (libelle, montant, reste) VALUES ('vetement', 5000.00, 5000.00 );


