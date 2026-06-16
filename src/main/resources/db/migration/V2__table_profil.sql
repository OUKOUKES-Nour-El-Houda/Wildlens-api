CREATE TABLE profil (
                        id BIGSERIAL PRIMARY KEY,          -- Clé primaire auto-incrémentée
                        name_user VARCHAR(255) NOT NULL,    -- Nom de l'utilisateur
                        username_user VARCHAR(255) UNIQUE NOT NULL,  -- Nom d'utilisateur unique
                        mail_user VARCHAR(255) UNIQUE NOT NULL,     -- Adresse email unique
                        password VARCHAR(255) NOT NULL,     -- Mot de passe (à stocker en format sécurisé)
                        registration_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    );
