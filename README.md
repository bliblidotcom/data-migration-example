# Blibli Database Migration

This project used by blibli.com as database migration for every microservices in blibli.com

This project support :

- PostgreSQL, using JOOQ
- MongoDB, using Mongodb Java Driver
- Elasticsearch, using OkHTTP
- Apache Cassandra, using Datastax Java Driver

Currently blibli.com only using those datastores.

## Create Migration Script

To create new migration script, we can using generator with command :

```mvn spring-boot:run -Dmode=create```

This will create new `Migration_yyyyMMddHHmmss.java` file in `src/main/java/migrations` directory

## Multi Profiles

Blibli data migration project support multi profiles, you can add your config profile in `src/main/resources/application-YOURPROFILE.properties`. For example you can create profile, local, development, production, uat, etc.

Every time you running the migration, you need to tell your active profile with parameter `-Drun.profiles=YOURPROFILE`

## Run Migration

After you finish create your migration script, you can start migration process using command :

```mvn spring-boot:run -Drun.profiles=your-profile -Dmode=migrate```

## Rollback Migration

If you want to rollback the migration to specific VERSION, you can using command :

```mvn spring-boot:run -Drun.profiles=your-profile -Dmode=rollback -Dversion=VERSION```

Where version is `yyyyMMddHHmmss` in your migration class. If you want to rollback all, just use version `0`

## Build Jar

After you finish create migration, you can package as single jar using :

```mvn package```

## Run in Production

If you want to run in production, first, you need to create configuration file, and run the jar using this command :

```java -Dmode=(migrate/rollback) -jar your-data-migration.jar --spring.config.location=/path/to/application.properties```
