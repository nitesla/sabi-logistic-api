CREATE TABLE shedlock (
  name VARCHAR(64),
  lock_until TIMESTAMP(3) NULL,
  locked_at TIMESTAMP(3) NULL,
  locked_by VARCHAR(255),
  PRIMARY KEY (name)
)



CREATE TABLE UsersRole(
  	userId BIGINT NOT NULL REFERENCES user (id),
  	roleId BIGINT NOT NULL REFERENCES role (id),
    createdOn            DATETIME     NULL ,
    createdBy            BIGINT NULL  ,
    lastUpdatedOn        DATETIME     NULL,
    lastUpdatedBy        BIGINT     NULL,
    CONSTRAINT PK_UsersRole PRIMARY KEY (userId,roleId)

 );


