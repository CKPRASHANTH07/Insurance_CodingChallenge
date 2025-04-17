CREATE DATABASE insurance_management;
USE insurance_management;

-- User Table
CREATE TABLE User (
    userId INT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    role VARCHAR(20) NOT NULL
);

-- Policy Table
CREATE TABLE Policy (
    policyId INT PRIMARY KEY,
    policyNumber VARCHAR(50) NOT NULL,
    policyType VARCHAR(50),
    coverageAmount DECIMAL(10, 2)
);

-- Client Table
CREATE TABLE Client (
    clientId INT PRIMARY KEY,
    clientName VARCHAR(100) NOT NULL,
    contactInfo VARCHAR(100),
    policyId INT,
    FOREIGN KEY (policyId) REFERENCES Policy(policyId)
);

-- Claim Table
CREATE TABLE Claim (
    claimId INT PRIMARY KEY,
    claimNumber VARCHAR(50) NOT NULL,
    dateFiled DATE,
    claimAmount DECIMAL(10, 2),
    status VARCHAR(20),
    policyId INT,
    clientId INT,
    FOREIGN KEY (policyId) REFERENCES Policy(policyId),
    FOREIGN KEY (clientId) REFERENCES Client(clientId)
);

-- Payment Table
CREATE TABLE Payment (
    paymentId INT PRIMARY KEY,
    paymentDate DATE,
    paymentAmount DECIMAL(10, 2),
    clientId INT,
    FOREIGN KEY (clientId) REFERENCES Client(clientId)
);

-- Sample Data
INSERT INTO User VALUES (1, 'admin', 'admin123', 'ADMIN');
INSERT INTO Policy VALUES (1, 'POL001', 'Health', 50000.00);
INSERT INTO Client VALUES (1, 'John Doe', 'john@example.com', 1);
INSERT INTO Claim VALUES (1, 'CLM001', '2025-04-01', 10000.00, 'PENDING', 1, 1);
INSERT INTO Payment VALUES (1, '2025-04-02', 5000.00, 1);