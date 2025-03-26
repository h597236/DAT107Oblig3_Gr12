-- Opprette schema til Oblig3
CREATE SCHEMA Oblig3;

SET search_path TO Oblig3;

-- Opprette tabell for Ansatt
CREATE TABLE Ansatt (
    ansatt_id SERIAL PRIMARY KEY,
    brukernavn VARCHAR(4) UNIQUE NOT NULL,
    fornavn VARCHAR(50) NOT NULL,
    etternavn VARCHAR(50) NOT NULL,
    dato_ansettelse DATE NOT NULL,
    stilling VARCHAR(50) NOT NULL,
    månedslønn NUMERIC(10,2) NOT NULL,
    avdeling_id INT
);

-- Opprette tabell for Avdeling
CREATE TABLE Avdeling (
    avdeling_id SERIAL PRIMARY KEY,
    navn VARCHAR(100) NOT NULL,
    sjef_id INT UNIQUE
);

-- Opprette tabell for Prosjekt
CREATE TABLE Prosjekt (
    prosjekt_id SERIAL PRIMARY KEY,
    navn VARCHAR(100) NOT NULL,
    beskrivelse TEXT
);

-- Opprette tabell for Prosjektdeltagelse (mange-til-mange mellom Ansatt og Prosjekt)
CREATE TABLE Prosjektdeltagelse (
    ansatt_id INT NOT NULL,
    prosjekt_id INT NOT NULL,
    rolle VARCHAR(50) NOT NULL,
    timer INT NOT NULL DEFAULT 0,
    PRIMARY KEY (ansatt_id, prosjekt_id),
    FOREIGN KEY (ansatt_id) REFERENCES Ansatt(ansatt_id) ON DELETE RESTRICT,
    FOREIGN KEY (prosjekt_id) REFERENCES Prosjekt(prosjekt_id) ON DELETE RESTRICT
);

-- Legge til fremmednøkler for å sikre relasjoner
ALTER TABLE Ansatt ADD CONSTRAINT fk_avdeling FOREIGN KEY (avdeling_id) REFERENCES Avdeling(avdeling_id) ON DELETE RESTRICT;
ALTER TABLE Avdeling ADD CONSTRAINT fk_sjef FOREIGN KEY (sjef_id) REFERENCES Ansatt(ansatt_id) ON DELETE RESTRICT;

-- Testdata
INSERT INTO Avdeling (navn) VALUES ('Utvikling'), ('Salg'), ('HR');

INSERT INTO Ansatt (brukernavn, fornavn, etternavn, dato_ansettelse, stilling, månedslønn, avdeling_id)
VALUES
    ('abc', 'Ola', 'Nordmann', '2022-01-10', 'Utvikler', 60000, 1),
    ('def', 'Kari', 'Hansen', '2021-06-15', 'Selger', 55000, 2),
    ('ghi', 'Per', 'Jensen', '2023-03-20', 'HR-leder', 50000, 3);

INSERT INTO Prosjekt (navn, beskrivelse) VALUES ('Ny nettside', 'Utvikling av ny nettside for selskapet');
INSERT INTO Prosjekt (navn, beskrivelse) VALUES ('CRM-system', 'Implementering av nytt CRM-system');

INSERT INTO Prosjektdeltagelse (ansatt_id, prosjekt_id, rolle, timer)
VALUES (1, 1, 'Frontend utvikler', 120), (2, 2, 'Prosjektleder', 80);
