package server.main;

import org.hibernate.cfg.Configuration;
import server.entities.RoleEntity;
import server.entities.UserEntity;

/**
 * Created by Артем on 28.12.2015.
 */
public class Configurations {
    private static final String hibernate_show_sql = "false";
    private static final String hibernate_hbm2ddl_auto = "validate";

    public static Configuration getPostGresConfigurationRemote() {
        Configuration configuration = getBaseConfiguration();

        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.connection.url",
                "jdbc:postgresql://ec2-54-195-252-202.eu-west-1.compute.amazonaws.com:5432/da7cn3tiopjdff?" +
                        "ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory");
        configuration.setProperty("hibernate.connection.username", "jgneczrdeubazg");
        configuration.setProperty("hibernate.connection.password", "6CpgQ4Teo4bnlICe3OzCZu4iOB");
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);

        return configuration;
    }

    public static Configuration getPostGresConfigurationLocal() {
        Configuration configuration = getBaseConfiguration();

        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/postgres");
        configuration.setProperty("hibernate.connection.username", "postgres");
        configuration.setProperty("hibernate.connection.password", "q");
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);

        return configuration;
    }

    private static Configuration getBaseConfiguration() {
        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(UserEntity.class);
        configuration.addAnnotatedClass(RoleEntity.class);

        return configuration;
    }

}
