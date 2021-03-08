-- 创建permission表
CREATE TABLE IF NOT EXISTS `permission`
(
    `ID`
    INT
    UNSIGNED
    AUTO_INCREMENT
    NOT
    NULL,
    `NAME`
    VARCHAR
(
    50
) UNIQUE NOT NULL,
    `URI` VARCHAR
(
    50
) NOT NULL,
    `C` TINYINT,
    `R` TINYINT,
    `U` TINYINT,
    `D` TINYINT,
    PRIMARY KEY
(
    `ID`
)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
--  为Account表创建外键  可以用Spring事务来在代码层面做管理 减少数据库的外键约束 多表的时候尽量减少这种外键约束
ALTER TABLE `my_program`.`account_role`
    ADD CONSTRAINT `AcountId` FOREIGN KEY (`ACCOUNT_ID`) REFERENCES `my_program`.`my_user` (`ID`) ON DELETE NO ACTION;