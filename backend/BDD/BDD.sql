-- Tabla de Usuarios
CREATE TABLE users (
    ID SERIAL PRIMARY KEY,
    nombre_usuario VARCHAR(255) NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    correo_electronico VARCHAR(255)
);

-- Tabla de Etiquetas
CREATE TABLE labels (
    ID SERIAL PRIMARY KEY,
    ID_usuario INT NOT NULL,
    nombre_etiqueta VARCHAR(255) NOT NULL,
    FOREIGN KEY (ID_usuario) REFERENCES users(ID)
);

-- Tabla de Tareas
CREATE TABLE tasks (
    ID SERIAL PRIMARY KEY,
    ID_etiqueta INT NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    descripcion TEXT,
    estado VARCHAR(50) NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_limite TIMESTAMP,
    FOREIGN KEY (ID_etiqueta) REFERENCES labels(ID)
);

-- Inserción de usuarios reales con contraseñas aleatorias
INSERT INTO users (nombre_usuario, contrasena, correo_electronico)
VALUES
    ('Marco Reynolds', '12345', 'marco.reynolds@example.com'),
    ('Omar Campohermoso', '98765', 'omar.Campohermoso@example.com');

-- Inserción de etiquetas reales en inglés
INSERT INTO labels (ID_usuario, nombre_etiqueta)
VALUES
    (1, 'Work'),
    (1, 'Personal'),
    (2, 'Important Project'),
    (1, 'Meeting'),
    (1, 'Vacation'),
    (2, 'Family'),
    (2, 'Shopping');

-- Inserción de tareas reales para usuarios reales
INSERT INTO tasks (ID_etiqueta, titulo, descripcion, estado, fecha_limite)
VALUES
    (1, 'Review Report', 'Review the quarterly sales report', 'Pending', '2023-09-30 23:59:59'),
    (2, 'Call Client', 'Call the client to discuss the contract', 'Completed', '2023-09-30 23:59:59'),
    (3, 'Prepare Presentation', 'Prepare a presentation for the board meeting', 'Pending', '2023-09-30 23:59:59'),
    (1, 'Send Emails', 'Send follow-up emails to clients', 'Pending', '2023-10-05 18:00:00'),
    (2, 'Meeting with Team', 'Schedule and conduct a team meeting', 'In Progress', '2023-10-02 15:30:00'),
    (3, 'Project Research', 'Conduct research for the upcoming project', 'Pending', '2023-10-07 12:00:00'),
    (4, 'Update Website Content', 'Update content on the company website', 'Pending', '2023-10-10 09:00:00');
