CREATE TABLE IF NOT EXISTS `users` (
    `id`              CHAR(36) NOT NULL PRIMARY KEY,
    `email`           VARCHAR(191) NOT NULL UNIQUE,
    `hashed_password` VARCHAR(191) NOT NULL,
    `user_name`       VARCHAR(191) NOT NULL UNIQUE,
    `display_name`    VARCHAR(191) NOT NULL,
    `created_at`      DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`      DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    INDEX idx_users_email (email),
    INDEX idx_users_user_name (user_name)
) DEFAULT CHARSET=utf8mb4;
