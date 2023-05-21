CREATE TABLE "User_Position"(
    "id" BIGINT NOT NULL,
    "user_id" BIGINT NOT NULL,
    "position_id" BIGINT NOT NULL
);
ALTER TABLE
    "User_Position" ADD PRIMARY KEY("id");
CREATE TABLE "Position"(
    "id" BIGINT NOT NULL,
    "name_position" TEXT NOT NULL,
    "description" TEXT NOT NULL
);
ALTER TABLE
    "Position" ADD PRIMARY KEY("id");
CREATE TABLE "MyUser"(
    "id" BIGINT NOT NULL,
    "surname" TEXT NOT NULL,
    "name" TEXT NOT NULL,
    "middle_name" TEXT NULL,
    "birthdate" DATE NOT NULL,
    "email" TEXT NOT NULL,
    "phone_number" TEXT NOT NULL,
    "position_id" BIGINT NOT NULL
);
ALTER TABLE
    "MyUser" ADD PRIMARY KEY("id");
ALTER TABLE
    "User_Position" ADD CONSTRAINT "user_position_position_id_foreign" FOREIGN KEY("position_id") REFERENCES "Position"("id");
ALTER TABLE
    "MyUser" ADD CONSTRAINT "myuser_position_id_foreign" FOREIGN KEY("position_id") REFERENCES "Position"("id");
ALTER TABLE
    "User_Position" ADD CONSTRAINT "user_position_user_id_foreign" FOREIGN KEY("user_id") REFERENCES "MyUser"("id");