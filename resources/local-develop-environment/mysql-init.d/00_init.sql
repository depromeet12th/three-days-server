CREATE
USER 'three-days-local'@'localhost' IDENTIFIED BY 'three-days-local';
CREATE
USER 'three-days-local'@'%' IDENTIFIED BY 'three-days-local';

GRANT ALL PRIVILEGES ON *.* TO
'three-days-local'@'localhost';
GRANT ALL PRIVILEGES ON *.* TO
'three-days-local'@'%';

CREATE
    DATABASE threedays DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE
    DATABASE batch DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
